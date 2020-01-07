package wellLid.client;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wellLid.service.AppServiceSocket;

public class MinaStartListener implements ServletContextListener {

	private MinaClient minastart;

	public void contextDestroyed(ServletContextEvent e) {

	}

	public void contextInitialized(ServletContextEvent e) {
		

//			minastart = new MinaClient();
//			minastart.startmina();


	}

}
