package Graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.*;

public class UI {
    public static void main(String[] args)
    {
        new UI();    //调用构造方法
    }

    private JPanel panel=new JPanel();
    private JTextField amountJTF=new JTextField(16);    //用于输入顶点数目
    private int nodeAmount = 0; //用于记录用户输入的结点数
    private int edgeAmount = 0; //用于记录边的数目
    JTextField[] node; //用于输入各个结点名
    //用于输入各个结点的信息
    JTextField[] begin;
    JTextField[] end;
    JTextField[] weight;
    JTextField beginningNode;
    JTextField edgeJTF;
    adjMatrixGraph graph;

    private JButton amountButton = new JButton("确定数目");
    private JButton nodeButton = new JButton("确定结点名");
    private JButton infoButton = new JButton("确定结点信息");

    private UI()
    {
        JFrame frame = new JFrame("最小生成树的prim算法");
        frame.add(panel);
        JLabel amountLabel = new JLabel("请输入结点的数目:");
        panel.add(amountLabel);
        panel.add(amountJTF);
        panel.add(amountButton);
        amountButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel info1 = new JLabel();
                String command=e.getActionCommand();
                if(command.equals("确定数目"))
                {
                    if(amountJTF.getText().length()!=0 && Integer.parseInt(amountJTF.getText()) > 0)
                    {
                        nodeAmount = Integer.parseInt(amountJTF.getText());
                        node = new JTextField[nodeAmount];
                        panel.add(info1);
                        info1.setText("请逐个输入结点名称。");
                        for(int i = 0; i < nodeAmount; i++)
                        {
                            node[i] = new JTextField(8);
                            panel.add(node[i]);
                        }
                        JLabel edgeInfo = new JLabel("请输入边的数目:");
                        panel.add(edgeInfo);
                        edgeJTF = new JTextField(8);
                        panel.add(edgeJTF);
                        amountButton.setEnabled(false);
                        panel.add(nodeButton);
                    }
                    else
                    {
                        panel.add(info1);
                        info1.setText("请输入正确的结点的数目!");
                    }
                }
            }
        });
        nodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel info2 = new JLabel();
                String command=e.getActionCommand();
                if(command.equals("确定结点名"))
                {
                    //  首先读取各个结点名字，生成一棵没有边的树
                    //  暂时没写检测代码
                    char[] ver = new char[nodeAmount];
                    for(int i = 0; i < nodeAmount; i++)
                    {
                        ver[i] = node[i].getText().charAt(0);
                    }
                    graph = new adjMatrixGraph(nodeAmount, ver, 100);
                    //  再提示输入各个结点的信息
                    panel.add(info2);
                    info2.setText("请输入"+amountJTF.getText()+"个结点的信息（依次为起点、重点、权值）。");
                    edgeAmount = Integer.parseInt(edgeJTF.getText());
                    begin = new JTextField[edgeAmount];
                    end = new JTextField[edgeAmount];
                    weight = new JTextField[edgeAmount];
                    JLabel[] info = new JLabel[edgeAmount+1];
                    for(int i = 0; i < edgeAmount; i++)
                    {
                        info[i] = new JLabel("第"+ Integer.toString((i+1)) +"个结点");
                        panel.add(info[i]);
                        begin[i] = new JTextField(8);
                        end[i] = new JTextField(8);
                        weight[i] = new JTextField(8);
                        panel.add(begin[i]);
                        panel.add(end[i]);
                        panel.add(weight[i]);
                    }
                    info[edgeAmount] = new JLabel("请输入Prim算法起始结点的名字");
                    panel.add(info[edgeAmount]);
                    beginningNode = new JTextField(8);
                    panel.add(beginningNode);
                    nodeButton.setEnabled(false);
                    panel.add(infoButton);
                }
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel result;
                String command=e.getActionCommand();
                if(command.equals("确定结点信息"))
                {
                    for(int i = 0; i < edgeAmount; i++)
                    {
                        graph.insert(begin[i].getText().charAt(0), end[i].getText().charAt(0), Integer.parseInt(weight[i].getText()));
                    }
                    String output = graph.prim(beginningNode.getText().charAt(0));
                    System.out.print(output);
                    JOptionPane.showMessageDialog(panel, output, "输出", 1);
                    nodeButton.setEnabled(false);
                }
            }
        });
        panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
        frame.setBounds(300,200,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

