package wellLid.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wellLid.service.AppServiceSocket;


/**
 * Mina客户端
 */
public class MinaClient  {

    public static void main(String[] args) {


        MinaClientHandler mimnListener= new MinaClientHandler();
        // 创建TCP/IP连接
        NioSocketConnector connection = new NioSocketConnector();

        // 创建接受收据的过滤器
        DefaultIoFilterChainBuilder chain = connection.getFilterChain();

        // 往过滤器链的最后加入一个一行一行读取数据的过滤器
        // 添加日志过滤器

        connection.getSessionConfig().setReadBufferSize(1024*1024);//发送缓冲区1M

        connection.getSessionConfig().setReceiveBufferSize(1024*1024);//接收缓冲区1M
        connection.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        chain.addLast("logging", new LoggingFilter());
        chain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        // 客户端消息处理器
        connection.setHandler(mimnListener);

        // 设置超时时间
        connection.setConnectTimeout(10000);

        // 连接到服务器
        ConnectFuture cf = connection.connect(new InetSocketAddress("192.168.0.110", 10011));

        // 等待连接完成
        cf.awaitUninterruptibly();

        cf.getSession().getCloseFuture().awaitUninterruptibly();

        connection.dispose();

    }


}
