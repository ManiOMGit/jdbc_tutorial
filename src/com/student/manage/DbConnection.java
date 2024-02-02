package com.student.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection createDbConnection() throws ClassNotFoundException, SQLException {
		String user="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/student_manage";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,user, password);
		return con;
	}

}
