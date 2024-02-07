package com.student.manage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteMethodOperations {

	public static void updateUsage(String query) {
		try {
			Connection con = DbConnection.createDbConnection();
			Statement stmnt = con.createStatement();
			boolean result = stmnt.execute(query);
			if (result == true) {
				ResultSet rs = stmnt.getResultSet();
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println("Name of Student is: " + name);
				}
			} else {
				int noOfRowsAffected = stmnt.getUpdateCount();
				System.out.println("Rows Affected= " + noOfRowsAffected);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
