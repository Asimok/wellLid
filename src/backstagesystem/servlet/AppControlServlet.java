package backstagesystem.servlet;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstagesystem.service.WifiServerSocket;
import backstagesystem.util.ToolUtils;

public class AppControlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -582634537189366787L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 让doGet请求也归类为doPost请求
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入 AppControlServlet");
		response.setContentType("text/html;charset=UTF-8");
		// 获取doPost请求中的传过来的数据
		String username = request.getParameter("username");
		String sessionId = request.getParameter("sessionId");
		String data = request.getParameter("data");

		// 将字符串的数据转化成byte数组
		byte[] msg = ToolUtils.stringToByte(data);

		JSONObject jObject = new JSONObject();
		if (sessionId != null) {
			// TODO 将获取的数据打印出来
			System.out.println("username:" + username);
			System.out.println("sessionId:" + sessionId);
			System.out.println("data:" + data);

			// 这里的sessionId是CONN_9527，通过这个索引取出相对应的socket对象，然后将APP发送过来的数据，再发送到8266
			WifiServerSocket.ProcessSocketData psd = WifiServerSocket.getSocketMap().get(
					new String(sessionId));
			if (psd != null) {
				// TODO 8266在线状态
				psd.send(msg);
				System.out.println("数据已发送到8266");
				try {
					JSONObject record = new JSONObject();
					record.put("username", username);

					jObject.put("reason", "SUCCESSED");
					jObject.put("resultCode", 200);
					jObject.put("totalNum", 1);
					jObject.put("data", record);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// TODO 继电器离线状态
				System.out.println("socket连接为空，8266未连接服务器");
				try {
					JSONObject record = new JSONObject();
					record.put("username", username);

					jObject.put("reason", "SUCCESSED");
					jObject.put("resultCode", 202);
					jObject.put("totalNum", 0);
					jObject.put("data", record);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			// 未接收到该设备的id
			try {
				jObject.put("resultCode", 204);
				jObject.put("reason", "NULL");
				jObject.put("data", "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					jObject.put("resultCode", 400);
					jObject.put("reason", "ERROR");
					jObject.put("data", "");

				} catch (JSONException ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.print(jObject);
		out.flush();
		out.close();

	}
}
