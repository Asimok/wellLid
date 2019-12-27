package wellLid.util;

public class test2 {
    private final static String IP = "192.168.31.114";
    private final static int PORT = 10011;

    public static void main(String[] args) {
        String msg = "<root><check>testEquityInsertRev</check><method>equityinsertrev</method><param><usernumber>13632599010</usernumber><equityid>1</equityid><statedate>20130620</statedate></param></root>";
        System.out.println("testEquityInsertRev request msg:" + msg);
       //package com.potter;
        //
        //import java.net.InetSocketAddress;
        //import java.nio.charset.Charset;
        //
        //import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
        //import org.apache.mina.core.future.ConnectFuture;
        //import org.apache.mina.filter.codec.ProtocolCodecFilter;
        //import org.apache.mina.transport.socket.nio.NioSocketConnector;
        //
        //public class MinaClient {
        //	private static final int bindPort=8899;
        //	public static void main(String[] args){
        //		// 创建一个socket连接
        //		NioSocketConnector connector=new NioSocketConnector();
        //		// 获取过滤器链
        //		DefaultIoFilterChainBuilder chain=connector.getFilterChain();
        //
        //		ProtocolCodecFilter filter= new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8")));
        //		// 添加编码过滤器 处理乱码、编码问题
        //		chain.addLast("objectFilter",filter);
        //		// 消息核心处理器
        //		connector.setHandler(new MinaClientHanlder());
        //		// 设置链接超时时间
        //		connector.setConnectTimeoutCheckInterval(30);
        //		// 连接服务器，知道端口、地址
        //		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",bindPort));
        //		// 等待连接创建完成
        //		cf.awaitUninterruptibly();
        //		cf.getSession().getCloseFuture().awaitUninterruptibly();
        //		connector.dispose();
        //	}
        //} System.out.println("testEquityInsertRev response msg:" + testApi(msg));
    }
}