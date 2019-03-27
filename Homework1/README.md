# Port a exercise of Data Structure to Java
## 使用方法
执行UI.java的main方法，可以使用可视化前端来生成图以及调用prim算法。执行adjMatrixGraph.java的main方法，可以在后端生成图以及调用prim算法。
### 注意
- 输入的结点数目必须为正整数，不能为负数、浮点数、其他字符，否则会报错(暂时没有写检测代码)。
- 输入的结点名必须为一个字符，不能为多个字符，否则会报错。输入的多个结点名可以用空格隔开，或者用回车隔开。
- 由于前端的输入框是动态生成的，因此在输入结点信息的时候需要用户手动输入边的数目。而后端可以由用户自己决定输入若干边，直到退出。
- 每条边的权值必须为整数，不然会报错。
## 详细介绍
该项目实现了将数据结构练习中的最小生成树用Java重写。Java类存放于[./src/Graph](https://github.com/hsc10705581/SE418_517021910118/tree/master/src/Graph)中。
同时为该Java类编写了JUnit测试，测试代码存放于[./Test/Graph](https://github.com/hsc10705581/SE418_517021910118/tree/master/Test/Graph)中。
### adjMatrixGraph.java
最小生成树的实现代码，其中要求图的结点用一个char变量表示，图的边的权值用int变量表示，图没有重边。
**该Java类包含以下方法:**
- int find(char v)，用于获取结点v对应的存储该结点的数组的下标。
- adjMatrixGraph(int vSize, char d[], int noEdgeFlag)，构造函数，生成大小为vSize，结点集为d，默认没有边（每条边的权值为无限大）的图。
- void insert(char x, char y, int w)，用于在结点x和结点y之间插入权值为w的边。
- void remove(char x, char y)，用于删除结点x和结点y之间的边。
- boolean exist(char x, char y)，用于判断结点x和结点y之间是否存在边。
- void prim()，最小生成树的prim算法。
- static void main(String[] args)，main函数用于该Java类的使用测试。
### adjMatrixGraphTest.java
adjMatrixGraph.java的基于JUnit的测试代码。该测试代码测试了adjMatrixGraph.java的insert方法、remove方法和exist方法。
### TestRunner.java
仅由main函数组成，用于运行adjMatrixGraphTest.java测试代码。
### UI.java
使用Swing库编写的adjMatrixGraph.java的前端。
