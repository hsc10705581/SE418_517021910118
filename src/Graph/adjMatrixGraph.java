package Graph;

import java.io.IOException;
import java.util.Scanner;

public class adjMatrixGraph {
    private int[][] edge;
    private char[] ver;
    private int noEdge;
    private int Vers, Edges;
    private int find(char v)
    {
        for(int i = 0; i < Vers; i++)
            if(ver[i] == v) return i;
        return -1;
    }
    //构造函数
    private adjMatrixGraph(int vSize, char[] d, int noEdgeFlag)
    {
        int i, j;

        //在基类中定义的变量
        Vers = vSize;
        Edges = 0;
        noEdge = noEdgeFlag;

        //存放节点的数组初始化
        ver = new char[vSize];
        for(i = 0; i < vSize; i++) ver[i] = d[i];

        //邻接矩阵初始化
        edge = new int[vSize][];
        for(i = 0; i < vSize; i++)
        {
            edge[i] = new int[vSize];
            for(j = 0; j < vSize; j++) edge[i][j] = noEdge;
            edge[i][i] = noEdge;
        }
    }

    private void insert(char x, char y, int w)
    {
        int u = find(x), v = find(y);
        edge[u][v] = w;
        edge[v][u] = w;
        Edges++;
    }

    void remove(char x, char y)
    {
        int u = find(x), v = find(y);
        edge[u][v] = noEdge;
        edge[v][u] = noEdge;
        --Edges;
    }

    boolean exist(char x, char y)
    {
        int u = find(x), v = find(y);
        return edge[u][v] != noEdge;
    }

    private void prim() throws IOException {
        boolean[] flag = new boolean[Vers];
        int[] lowCost = new int[Vers];
        int[] startNode = new int[Vers];

        int min;
        int start, i, j;

        for(i = 0; i < Vers; i++)
        {
            flag[i] = false;
            lowCost[i] = noEdge;
        }

        char startPoint;
        System.out.print("请输入起始顶点的标记:");
        startPoint = (char)System.in.read();
        start = find(startPoint);
        System.out.print(start);

        System.out.print("最小生成树的边为:");
        for(i = 0; i < Vers - 1; i++)
        {
            for(j = 0; j < Vers; j++)
            {
                if(!flag[j] && lowCost[j] > edge[start][j])
                {
                    lowCost[j] = edge[start][j];
                    startNode[j] = start;
                }
            }
            flag[start] = true;
            min = noEdge;
            for(j = 0; j < Vers; j++)
                if(lowCost[j] < min){min = lowCost[j]; start = j;}
            System.out.printf("(%c,%c,%d)\t", ver[startNode[start]], ver[start], edge[startNode[start]][start]);
            lowCost[start] = noEdge;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char[] ver;
        char begin;
        char end;
        int weight, numOfPoint;
        System.out.print("请输入顶点的数目:");
        numOfPoint = sc.nextInt();
        ver = new char [numOfPoint];
        System.out.printf("请逐个输入%d个顶点的标记:", numOfPoint);
        for(int i = 0; i < numOfPoint; i++)
            ver[i] = sc.next().charAt(0);
        adjMatrixGraph graph = new adjMatrixGraph(numOfPoint, ver, 100); //网中边的权值设成小于100的整数

        while(true)
        {
            System.out.print("请输入边的起点(输入q退出):");
            begin = sc.next().charAt(0);
            if(begin == 'Q' || begin == 'q') break;
            System.out.print("请输入边的终点:");
            end = sc.next().charAt(0);
            System.out.print("请输入该边的权值:");
            weight = sc.nextInt();
            graph.insert(begin, end, weight);
        }
        graph.prim();
    }
}
