package wellLid.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class mainInterface extends JFrame implements ActionListener, Runnable {
//    JPanel jp1;
private JLabel valueLabel0,valueLabel2,valueLabel1,valueState0,valueState1,valueState2,imag0Lable,imag1Lable,imag2Lable;
//    JButton btnreload;
//    ImageIcon img0, img1, img2;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
    private String value0, value1, value2;

    public void mainFace() {

//布局
        JFrame jf=new JFrame();
        //                                                                                                                                                    jf.setSize(1920,1080);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setTitle("井盖管理系统");


         imag0Lable = new JLabel();
         imag1Lable = new JLabel();
         imag2Lable = new JLabel();
        imag0Lable.setIcon(new ImageIcon("images/down.png"));
        imag1Lable.setIcon(new ImageIcon("images/down.png"));
        imag2Lable.setIcon(new ImageIcon("images/down.png"));



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
        gridBagConstraints.fill=GridBagConstraints.NONE;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void run() {
        separateData(getValue());
        System.out.println("收到数据 "+getValue());
    }
    public void changeview(int thisvalue0,int thisvalue1,int thisvalue2)
    {
        if(thisvalue0<1000)
        {
            valueLabel0.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel0.setForeground(Color.RED);
            valueState0.setText("状态异常");
            valueState0.setFont(new Font("微软雅黑", Font.BOLD, 30));
            valueState0.setForeground(Color.RED);
            imag0Lable.setIcon(new ImageIcon("images/up.png"));
        }
        else
        {
            valueLabel0.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel0.setForeground(Color.magenta);
            valueState0.setText("状态正常");
            valueState0.setFont(new Font("微软雅黑", Font.PLAIN, 30));
            valueState0.setForeground(Color.green);
            imag0Lable.setIcon(new ImageIcon("images/down.png"));
        }

        if(thisvalue1<1000)
        {
            valueLabel1.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel1.setForeground(Color.RED);
            valueState1.setText("状态异常");
            valueState1.setFont(new Font("微软雅黑", Font.BOLD, 30));
            valueState1.setForeground(Color.RED);
            imag1Lable.setIcon(new ImageIcon("images/up.png"));
        }
        else
        {
            valueLabel1.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel1.setForeground(Color.magenta);
            valueState1.setText("状态正常");
            valueState1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
            valueState1.setForeground(Color.green);
            imag1Lable.setIcon(new ImageIcon("images/down.png"));
        }
        if(thisvalue2<1000)
        {
            valueLabel2.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel2.setForeground(Color.RED);
            valueState2.setText("状态异常");
            valueState2.setFont(new Font("微软雅黑", Font.BOLD, 30));
            valueState2.setForeground(Color.RED);
            imag2Lable.setIcon(new ImageIcon("images/up.png"));
        }
        else
        {
            valueLabel2.setFont(new Font("微软雅黑", Font.BOLD, 26));
            valueLabel2.setForeground(Color.magenta);
            valueState2.setText("状态正常");
            valueState2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
            valueState2.setForeground(Color.green);
            imag2Lable.setIcon(new ImageIcon("images/down.png"));
        }
    }

    public void separateData(String data) {
        if (data.contains("a")&&data.contains("b")&&data.contains("c")&&data.contains("d")) {
            value0 = data.substring(data.indexOf("a") + 1, data.indexOf("b"));
            value1 = data.substring(data.indexOf("b") + 1, data.indexOf("c"));
            value2 = data.substring(data.indexOf("c") + 1, data.indexOf("d"));
            changeview(Integer.parseInt(value0),Integer.parseInt(value1),Integer.parseInt(value2));
            valueLabel0.setText(value0 );
            valueLabel1.setText(value1 );
            valueLabel2.setText(value2 );
        }

    }
}

