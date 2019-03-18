package Graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.IOException;
import java.lang.*;

public class UI {
    public static void main(String[] args)
    {
        new UI();    //调用构造方法
    }

    private JPanel panel=new JPanel();
    private JTextField amountJTF=new JTextField(16);    //用于输入顶点数目
    private int amount = 0; //用于记录用户输入的结点数
    JTextField[] node; //用于输入各个结点名
    //用于输入各个结点的信息
    JTextField[] begin;
    JTextField[] end;
    JTextField[] weight;
    JTextField beginningNode;
    adjMatrixGraph graph;

    private JButton amountButton = new JButton("确定数目");
    private JButton nodeButton = new JButton("确定结点名");
    private JButton infoButton = new JButton("确定结点信息");
    String output;

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
                        amount = Integer.parseInt(amountJTF.getText());
                        node = new JTextField[amount];
                        panel.add(info1);
                        info1.setText("请逐个输入结点名称。");
                        for(int i = 0; i < amount; i++)
                        {
                            node[i] = new JTextField(8);
                            panel.add(node[i]);
                        }
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
                    char[] ver = new char[amount];
                    for(int i = 0; i < amount; i++)
                    {
                        ver[i] = node[i].getText().charAt(0);
                    }
                    graph = new adjMatrixGraph(amount, ver, 100);
                    //  再提示输入各个结点的信息
                    panel.add(info2);
                    info2.setText("请输入"+amountJTF.getText()+"个结点的信息（依次为起点、重点、权值）。");
                    amount = Integer.parseInt(amountJTF.getText());
                    begin = new JTextField[amount];
                    end = new JTextField[amount];
                    weight = new JTextField[amount];
                    JLabel[] info = new JLabel[amount+1];
                    for(int i = 0; i < amount; i++)
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
                    info[amount] = new JLabel("请输入Prim算法起始结点的名字");
                    panel.add(info[amount]);
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
                JLabel info3 = new JLabel();
                String command=e.getActionCommand();
                if(command.equals("确定结点信息"))
                {
                    for(int i = 0; i < amount; i++)
                    {
                        graph.insert(begin[i].getText().charAt(0), end[i].getText().charAt(0), Integer.parseInt(weight[i].getText()));
                    }
                    try {
                        output = graph.prim(beginningNode.getText().charAt(0));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JLabel result = new JLabel(output);
                    panel.add(result);
                }
            }
        });
        panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
        frame.setBounds(300,200,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

