# Task3

task3实现了:用mvn将我homework2的spring boot项目打包成docker image，实现了在docker容器中运行这个image，并将image push到了docker hub上。

## docker hub 链接
[https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118](https://cloud.docker.com/repository/registry-1.docker.io/hsc10705581/se418_517021910118)

## usage
Pull my docker image to local.
```
$ docker pull hsc10705581/se418_517021910118:homework
```
Run my docker image.
```
$ docker run -p 8080:8080 -t hsc10705581/se418_517021910118:homework
```
After running, use the project from [http://localhost:8080](http://localhost:8080)
