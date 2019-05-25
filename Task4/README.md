# Task 4

Task4 实现了:
- 用两个spring boot项目，分别作为服务的producer和consumer，并将producer和consumer分别docker化
- 在consumer中，利用hystrix实现timeout功能
- 在producer中，保存收到的每一个request
  - 如果producer中待处理的request数目小于(timeout时间/response平均响应时间),那么就使用queue策略处理每一个request
  - 如果producer中待处理的request数目等于(timeout时间/response平均响应时间),那么将queue中的每个request放入到一个新的Stack中,并在之后都使用Stack策略处理每一个request
  - 如果使用Stack策略的时候发现Stack中代处理request数目不再大于(timeout时间/response平均响应时间),那么将重新使用queue策略
- 在处理每一个request之前，判断接受该request时的时间与当前系统时间的时间差，如果这个时间差大于timeout时间，那么就直接将这个request丢弃。
- consumer在timeout后会显示timeout的页面
- 使用javaTest.py模拟同一时间发送100个request，测试server。

## docker hub 链接
[https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118](https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118)

## usage
Pull my docker image to local.
**consumer:**
```
$ docker pull hsc10705581/se418_517021910118:consumer
```
**producer:**
```
$ docker pull hsc10705581/se418_517021910118:producer
```
Run my docker image.
```
$ docker run -p 9090:9090 -t hsc10705581/se418_517021910118:producer
$ docker run -p 8080:8080 -t hsc10705581/se418_517021910118:consumer
```
After running, use following command to test my server:
```
$ pip install requests
$ pip install threading
$ pip install time
$ python javaText.py
```
You can see information at [http://localhost:8080/hystrix](http://localhost:8080/hystrix) like following:
