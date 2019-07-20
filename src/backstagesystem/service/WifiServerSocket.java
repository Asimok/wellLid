package backstagesystem.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

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
				// 死循环，无线读取8266发送过来的数据
				while (play) {
					byte[] msg = new byte[10];
					in.read(msg);// 读取流数据
					//System.out.println("WiFi发过来的数据：" + Arrays.toString(msg)+"\n");
					String str = new String(msg).trim();
					i++;
					System.out.println("WiFi发过来的数据转字符串  第"+ i +"次       "+str);
					if(str.equals(""))
					{

						System.out.println("-------------------------------WiFi发过来的数据为空------------------------------");
						play = false;
						continue;

					}
					else if (str.contains("CONN")) {
						mStrName = str.trim();
						/*
						 * 判断发过的是CONN_9527,那么就将此socket对象添加到这个类的静态集合里面，以CONN_9527为索引。
						 *  很多人这里可能不太懂，APP与服务端的通信在AppControlServlet类中触发，想要实现APP与8266通信，只能将这个socket对象通过类的静态变量暴露出去。
						 * 等到AppControlServlet收到APP的信息，就立马通过CONN_9527作为索引取出socket，和8266进行通讯
						 */
						WifiServerSocket.socketMap.put(mStrName, this);

					} else {
						if(mStrName!=null)
						{
							sendToAPP(mStrName, msg);
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
			System.out.println("进入给APP发送数据sessionId this:" + strName);
			System.out.println("转发数据: " + new String(msg));

			if (AppServiceSocket.getAcceptorSessions().get(strName) != null) {
				AppServiceSocket.getAcceptorSessions().get(strName).write(new String(msg));

				System.out.println("已发送给客户端");

		} else {
				System.out.println("客户端没上线");
			}
		}

		// 这是服务器发送数据到8266的函数
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
				System.out.println("该客户端已退出！");
			}
		}

	}

}
