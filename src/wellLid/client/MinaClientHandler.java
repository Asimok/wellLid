package wellLid.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.io.IOException;

/**
 * 客户端业务处理逻辑
 * 
 * @author 黄宝康 2017年9月30日 下午2:48:24
 */
public class MinaClientHandler extends IoHandlerAdapter {

    // 当客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("incomming 客户端: " + session.getRemoteAddress());
        session.write("CONN_9527");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("客户端发送信息异常....");
        System.out.println(cause);
    }

    // 当客户端发送消息到达时
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        String str = message.toString().trim();
        System.out.println("接受到服务器数据 "+str);


    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("one Client Connection" + session.getRemoteAddress());
      //  session.write("我来了······");
    }

}
