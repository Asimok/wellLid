package wellLid.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.websocket.Session;

/**
 * 硬件端与服务器端的长连接
 * 
 * @author Administrator
 * 
 */
public class WifiServerSocket extends Thread {
	private ServletContext servletContext;
	private ServerSocket serverSocket;

	private static Map<String, ProcessSocketData> socketMap = new HashMap<>();

	public WifiServerSocket(ServletContext servletContext) {
		this.servletContext = servletContext;

		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		if (serverSocket == null) {
			try {

				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();

			}

		}

	}

	public void run() {

		while (!this.isInterrupted()) { // 线程未中断执行循环

			try {
				// 开启服务器，线程阻塞，等待8266的连接
				Socket socket = serverSocket.accept();
				ProcessSocketData psd = new ProcessSocketData(socket);
				new Thread(psd).start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void closeServerSocket() {

		try {

			if (serverSocket != null && !serverSocket.isClosed())
				serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 将socket连接一静态集合变量的形式暴露出去
	public static Map<String, ProcessSocketData> getSocketMap() {
		return socketMap;
	}

	public class ProcessSocketData extends Thread {
		private Socket socket;
		private InputStream in = null;
		private DataOutputStream out = null;

		private String mStrName = null;
		private boolean play = false;

		// 构造方法，传入连接进来的socket
		public ProcessSocketData(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {

				e.printStackTrace();
			}
			play = true;
		}

		public void run() {
			try {
				int i=0;
                // 死循环，无线读取车锁 3G 端发送过来的数据
				while (play) {
					byte[] msg = new byte[128];
					in.read(msg);// 读取流数据
					//System.out.println("ESP8266端 发过来的数据：" + Arrays.toString(msg)+"\n");
					String str = new String(msg).trim();
					i++;
					System.out.println("ESP8266端 发过来的数据转字符串  第"+ i +"次       "+str);


					//sendmsg.start((Session) AppServiceSocket.getAcceptorSessions().get(mStrName));
					//sendmsg.sendAll(str);
					if(str.equals(""))
					{

                        System.out.println("////////////////////-----ESP8266端 发过来的数据为空-----/////////////////");
						play = false;
						continue;

					} else if (str.contains("CONN_9527")) {
						mStrName = str.trim();
						/*
						 * 判断发过的是CONN_9527,那么就将此socket对象添加到这个类的静态集合里面，以CONN_9527为索引。
                         * 等到AppControlServlet收到APP的信息，就立马通过CONN_9527作为索引取出socket，和客户端进行通讯
						 */
						WifiServerSocket.socketMap.put(mStrName, this);

					} else {
						if(mStrName!=null)
						{
							sendToAPP("CONN_9527", msg);
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();

			}finally {
				try {
					in.close();
					if (socket != null && !socket.isClosed()) {
						play = false;
						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 发送数据到APP的方法
		 * @param strName
		 * @param msg
		 */
		private void sendToAPP(String strName, byte[] msg) {
            //System.out.println("进入给APP发送数据sessionId this:" + strName);


			if (AppServiceSocket.getAcceptorSessions().get(strName) != null) {
				AppServiceSocket.getAcceptorSessions().get(strName).write(new String(msg));

				System.out.println("给客户端转发数据: " + new String(msg).trim());

		} else {
                System.out.println("客户端没上线");
			}
		}

		// 这是服务器发送数据到 ESP8266端 的函数
		public void send(String bytes) {
			try {
				out.writeUTF(bytes);
			} catch (IOException e) {
				try {
					// 移除集合里面的Socket
					WifiServerSocket.socketMap.remove(mStrName);
					out.close();
					play = false;
					in.close();
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                System.out.println("客户端 已退出！");
			}
		}

	}

}
