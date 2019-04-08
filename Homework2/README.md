# Homework2

## Usage
- 调用[/src/main/java/com/homework/demo/DemoApplication.java](https://github.com/hsc10705581/SE418_517021910118/blob/master/Homework2/src/main/java/com/homework/demo/DemoApplication.java)来启动web后端。
- 启动后端后，访问[localhost:8080](http://localhost:8080/)来访问前端页面
- 在前端填写表单后，按下**“调用prim算法”**，可以在浏览器的开发者工具中读取response的内容
- 在postman中，向[localhost:8080/graph/prim](http://localhost:8080/graph/prim)，设置body的键值对，发送post请求，可以获取response的值

## Test
- 调用[/src/test/java/com/homework/demo/DemoApplicationTests.java](https://github.com/hsc10705581/SE418_517021910118/blob/master/Homework2/src/test/java/com/homework/demo/DemoApplicationTests.java)来测试controller
- 调用[/src/test/java/com/homework/demo/adjMatrixGraphTest.java](https://github.com/hsc10705581/SE418_517021910118/blob/master/Homework2/src/test/java/com/homework/demo/adjMatrixGraphTest.java)来测试adjMatrixGraph

## API
request的body需要有一对键值对，其中key为“values”，value为转化为字符串类型的Json对象。
Json对象包含以下key：
- **amountOfNode**：结点的数目
- **amountOfLine**：边的数目
- **nodes**：存储结点标志的String的数组
- **starts**：存储各边起始结点标志的String的数组
- **ends**：存储各边终止结点标志的String的数组
- **weights**：存储各边权值Int的数组
- **startPoint**：prim算法的起始结点标志的String
