package persist_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration {
	DatabaseQueries db = new DatabaseQueries();

	// Connection variables
	String query;
	static Connection con;

	static Statement stmt;
	static ResultSet RS;
	static PreparedStatement preparedStmt;

	// Connect to SQL database
	public static Connection connect() {
		String URL = DbAccessConfiguration.getURL();
		String username = DbAccessConfiguration.getUsername();
		String password = DbAccessConfiguration.getPassword();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Trying to connect");
			con = DriverManager.getConnection(URL, username, password);
			System.out.println("Connection Established Successfull and the DATABASE NAME IS: "
					+ con.getMetaData().getDatabaseProductName());
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to make connection with DB");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unable to make connection with DB");
			e.printStackTrace();
		}
		return con;
	}

	// Retrieve SQL
	public static ResultSet retrieve(String query) {
		System.out.println("Query: " + query);
		try {
			stmt = con.createStatement();
			RS = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return RS;
	}

	// Create SQL
	public static int create(String query) {
		System.out.println(query);
		int rowsUpdated = 0;
		try {
			preparedStmt = con.prepareStatement(query);
			rowsUpdated = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	// Update SQL
	public static int update(String query) {
		System.out.println(query);
		int rowsUpdated = 0;
		try {
			preparedStmt = con.prepareStatement(query);
			rowsUpdated = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	// Delete SQL
	public static int delete(String query) {
		System.out.println(query);
		int rowsUpdated = 0;
		try {
			preparedStmt = con.prepareStatement(query);
			rowsUpdated = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	// Disconnect from server
	public static void disconnect() {
		try {
			con.close();
			System.out.println("Disconnected from SQL server");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
