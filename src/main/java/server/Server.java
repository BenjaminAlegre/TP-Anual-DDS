package server;


import spark.Spark;
import spark.debug.DebugScreen;


public class Server {

	public static void main(String[] args) throws Exception {
		Spark.port(8080);
		Router.init();
		DebugScreen.enableDebugScreen();


	}
}
