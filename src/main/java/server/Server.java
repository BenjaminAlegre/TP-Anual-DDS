package server;


import org.apache.log4j.BasicConfigurator;
import spark.Spark;
import spark.debug.DebugScreen;


public class Server {

	public static void main(String[] args) throws Exception {

		BasicConfigurator.configure();

		Spark.port(3000);
		Router.init();
		DebugScreen.enableDebugScreen();
		//iniciar cron



	}
}
