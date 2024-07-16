package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase_Connection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://root@localhost/Selenium_users");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("Select top 1 * from table");
		System.out.println(result.getString(1));
		connection.close();
	}

}
