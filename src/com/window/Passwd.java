package com.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Passwd
{
    static JTextField id;
    static JTextField passwd;
    static Vector titleVector=new Vector();
    static Vector departVector=new Vector();
    static JTable departTable;
    static JScrollPane departJScrollPane;
    public static JComponent change()
    {
        BackgroundPanel centre = new BackgroundPanel((new ImageIcon("src/img/4.png")).getImage());
        centre.setBorder(new EmptyBorder(5, 5, 5, 5));
        centre.setLayout(null);
        centre.setSize(800, 800);

        JPanel panel_1 =new JPanel();
        JPanel panel_2 =new JPanel();
        JPanel panel_3 =new JPanel();
        JPanel panel_4 =new JPanel();
        panel_3.setLayout(null);
        panel_3.add(new Label("修改密码"));
        centre.add(panel_3);
        panel_3.add(panel_1);
        panel_3.add(panel_2);

        //centre.add(panel_1);
        //centre.add(panel_2);
        panel_3.setBounds(0,0,800,800);
        panel_1.setBounds(410,175,200,50);
        panel_2.setBounds(410,232,200,50);
        panel_4.setBounds(390,290,200,50);

        JButton button =new JButton("修改");
        button.addActionListener(e->{
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "确认修改用户的密码？",
                    "提示",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );
            System.out.println("选择结果: " + result);
            if(result==0) upDadaProcess();
        });
        panel_4.add(button);
        panel_3.add(panel_4);


        id=new JTextField(15);
        passwd=new JTextField(15);

//        JPanel pan=new JPanel();
//        pan.setBounds(15,360,300,500);
//        pan.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
//        titleVector.add("部门编号");
//        titleVector.add("部门名称");
//        titleVector.add("部门经理");
//        titleVector.add("备注");
//
//        departTable=new JTable(departVector,titleVector);
//        departTable.setPreferredScrollableViewportSize(new Dimension(280,300));
//        departJScrollPane = new JScrollPane(departTable);
//        //分别设置水平和垂直滚动条自动出现
//        departJScrollPane.setHorizontalScrollBarPolicy(
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        departJScrollPane.setVerticalScrollBarPolicy(
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        //监听器
//        departTable.addMouseListener(new MouseAdapter()
//        {
//            public void mouseClicked(MouseEvent e)
//            {
//                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
//                System.out.println("mouseClicked(). row = " + row);
//            }
//        });
//
//        JButton bnt =new JButton("查看所有部门");
//        bnt.addActionListener(e->{
//
//            QueryAllprocess();
//        });

        //pan.add(new Label("部门查询表"));
        panel_1.add(id);
        panel_2.add(passwd);
//        panel_3.add(pan);
//        pan.add(departJScrollPane);
//        pan.add(bnt);

        panel_1.setOpaque(false);
        panel_2.setOpaque(false);
        panel_3.setOpaque(false);
        panel_4.setOpaque(false);
//        pan.setOpaque(false);

        return centre;
    }

//    private static void QueryAllprocess() {
//        try{
//            // 建立查询条件
//            String sql = "select * from depart ";
//            sql+=" order by id asc;";
//            System.out.println("queryAllProcess(). sql = " + sql);
//
//            DbProcess.connect();
//            ResultSet rs = DbProcess.executeQuery(sql);
//
//            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
//            departVector.clear();
//            while(rs.next()){
//                Vector v = new Vector();
//                v.add(Integer.valueOf(rs.getInt("id")));
//                v.add(rs.getString("name"));
//                v.add(rs.getString("manager"));
//                v.add(rs.getString("intro"));
//                departVector.add(v);
//            }
//            departTable.updateUI();
//            DbProcess.disconnect();
//        }catch(SQLException sqle){
//            System.out.println("sqle = " + sqle);
//            JOptionPane.showMessageDialog(null,
//                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private static void upDadaProcess() {
        try{
            String sId=id.getText().trim();
            String sPasswd=MD5.encrypt(passwd.getText().trim());
            String sql="update person set password='"+sPasswd+"' where id="+sId+";";
            if (DbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "密码修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("updateProcess(). update database successful.");
                id.setText("");
                passwd.setText("");
            }
        }catch(Exception even){
            System.out.println("e = " + even);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}
