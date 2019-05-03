# Homework3

## homework3实现了
将homework2的project分解成应用模块和验证模块。  
两个新的模块各自做成一个小的spring项目，并打包成docker image。  
最后在docker容器中运行两个微服务，实现两个微服务之间通信，来实现由一个微服务验证登录、另一个微服务运行实际应用的效果。  

## docker hub 链接
[https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118](https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118)

## usage
Pull my docker image to local.
```
$ docker pull hsc10705581/se418_517021910118:adj
$ docker pull hsc10705581/se418_517021910118:security
```
Run my docker image.
```
$ docker run -p 8080:8080 -t hsc10705581/se418_517021910118:security
$ docker run -p 3030:8080 -t hsc10705581/se418_517021910118:adj
```
After running, use the project from [http://localhost:3030](http://localhost:3030).  
At the first time you request for http://localhost:3030, you will be sent to http://localhost:8080 for login.  
