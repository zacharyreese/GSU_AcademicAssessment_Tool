package persist_layer;


public abstract class DbAccessConfiguration {
	private static String DB_CONNECTION_URL = "jdbc:mysql://aadb.cloepmhco6if.us-east-1.rds.amazonaws.com:3306/aadb?useSSL=false";
	private static String DB_CONNECTION_USERNAME = "admin";
	private static String DB_CONNECTION_PASSWORD = "password";


	public static String getURL() {
		return DB_CONNECTION_URL;
	}

	public static String getUsername() {
		return DB_CONNECTION_USERNAME;
	}

	public static String getPassword() {
		return DB_CONNECTION_PASSWORD;
	}

}
