package wellLid.util;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class UI {
    private JLabel valueLabel0,valueLabel2,valueLabel1,valueState0,valueState1,valueState2;
    public static void main(String args[]){
        UI ui=new UI();
        ui.mainFace();
    }
    public void mainFace() {

//布局
        JFrame jf=new JFrame();
        //                                                                                                                                                    jf.setSize(1920,1080);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setTitle("井盖管理系统");


        JLabel imag0Lable = new JLabel();
        JLabel imag1Lable = new JLabel();
        JLabel imag2Lable = new JLabel();
        ImageIcon img0 = new ImageIcon("images/down.png");
        imag0Lable.setIcon(img0);
        ImageIcon img1 = new ImageIcon("images/down.png");
        imag1Lable.setIcon(img1);
        ImageIcon img2 = new ImageIcon("images/down.png");
        imag2Lable.setIcon(img2);
        //设置label的位置、大小，label大小为图片的大小
        imag0Lable.setBounds(0, 0, img0.getIconWidth(), img0.getIconHeight());
        imag1Lable.setBounds(0, 0, img0.getIconWidth(), img0.getIconHeight());
        imag2Lable.setBounds(0, 0, img0.getIconWidth(), img0.getIconHeight());



        valueLabel1 = new JLabel("未监测", JLabel.CENTER);
        valueLabel0 = new JLabel("未监测", JLabel.CENTER);
        valueLabel2 = new JLabel("未监测", JLabel.CENTER);


        valueState0 = new JLabel("状态正常", JLabel.CENTER);
        valueState1 = new JLabel("状态正常", JLabel.CENTER);
        valueState2 = new JLabel("状态正常", JLabel.CENTER);

        //占位
        JLabel z1=new JLabel("           ", JLabel.CENTER);
        JLabel z2=new JLabel("           ", JLabel.CENTER);
        JLabel z3=new JLabel("           ", JLabel.CENTER);
        GridBagLayout gridBagLayout=new GridBagLayout(); //实例化布局对象
        jf.setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        GridBagConstraints gridBagConstraints=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill=GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        /*
         * 分别对组件进行设置
         */
        //组件1(gridx,gridx)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        //井盖图标1
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=2;
        gridBagLayout.setConstraints(imag0Lable, gridBagConstraints);
        //
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueLabel0, gridBagConstraints);
        //
        gridBagConstraints.gridy=8;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueState0, gridBagConstraints);



//
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridx=8;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=2;
        gridBagLayout.setConstraints(imag1Lable, gridBagConstraints);
        //
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridx=8;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueLabel1, gridBagConstraints);
        //
        gridBagConstraints.gridy=8;
        gridBagConstraints.gridx=8;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueState1, gridBagConstraints);


//
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridx=16;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=2;
        gridBagLayout.setConstraints(imag2Lable, gridBagConstraints);
        //
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridx=16;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueLabel2, gridBagConstraints);
        //
        gridBagConstraints.gridy=8;
        gridBagConstraints.gridx=16;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(valueState2, gridBagConstraints);


        jf.add(imag0Lable);
        jf.add(imag1Lable);
        jf.add(imag2Lable);
        jf.add(valueLabel0);
        jf.add(valueLabel1);
        jf.add(valueLabel2);
        jf.add(valueState0);
        jf.add(valueState1);
        jf.add(valueState2);

        jf.setVisible(true);


//        JButton btnreload = new JButton("刷新");
//        JButton button2 = new JButton("刷新");
//        JButton button3 = new JButton("刷新");
       // btnreload.addActionListener(this);


    }
    public void show(){
        /*
         * 窗体的基本设置
         */
        JFrame jf=new JFrame();
        //                                                                                                                                                    jf.setSize(1920,1080);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
       // jf.setResizable(false);

        /*
         * 生成窗体中的各种组件
         */
        ImageIcon imageQQ =new ImageIcon("images/down.png");
        JLabel component1=new JLabel(imageQQ);
        //组件1 是界面上的QQ蓝色面板图像，图像我们把它放在JLabel类对象上
        ImageIcon imageqq =new ImageIcon("images/down.png");
        JLabel component2=new JLabel(imageqq);
        //组件2 是界面上的QQ企鹅图像，同理图像我们把它放在JLabel类对象上
        JTextField component3=new JTextField();
        //组件3是用户的账号输入框
        JLabel component4=new JLabel("用户账号");
        //组件4是用户的账号输入框右边的提示标签
        JTextField component5=new JTextField();
        //组件5是用户的密码输入框
        JLabel component6=new JLabel("用户密码");
        //组件6是用户的密码输入框右边的提示标签
        JCheckBox component7=new JCheckBox("记住密码");
        //组件7是用户的“记住密码”的勾选键
        JCheckBox component8=new JCheckBox("自动登录");
        //组件8是用户的“自动登录”的勾选键
        JButton component9=new JButton("安全登录");
        //组件8是用户的“安全登录”的按键
        /*
         * 对窗体进行布局
         */
        GridBagLayout gridBagLayout=new GridBagLayout(); //实例化布局对象
        jf.setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        GridBagConstraints gridBagConstraints=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill=GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。 
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。 
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。 
        //BOTH：使组件完全填满其显示区域。 
        /*
         * 分别对组件进行设置
         */
        //组件1(gridx,gridx)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridwidth=4;                                             
        gridBagConstraints.gridheight=4;            
        gridBagLayout.setConstraints(component1, gridBagConstraints);
        //组件2
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridx=4;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=4;            
        gridBagLayout.setConstraints(component2, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridx=8;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component3, gridBagConstraints);

        gridBagConstraints.gridx=3;
        gridBagConstraints.gridx=4;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component4, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridx=5;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component5, gridBagConstraints);

        gridBagConstraints.gridx=3;
        gridBagConstraints.gridx=5;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component6, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component7, gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component8, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridx=7;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;            
        gridBagLayout.setConstraints(component9, gridBagConstraints);
        /*
         * 把所有组件加入jf窗体对象中去
         */
        jf.add(component1);
        jf.add(component2);
        jf.add(component3);
        jf.add(component4);
        jf.add(component5);
        jf.add(component6);
        jf.add(component7);
        jf.add(component8);
        jf.add(component9);

        jf.setVisible(true);
    }
}
