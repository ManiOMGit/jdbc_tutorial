package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	
	public static boolean insertStudentInToDb(Student stu) throws ClassNotFoundException, SQLException {
		Connection con=DbConnection.createDbConnection();
		String query="insert into student(sname,sphone,scity) values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, stu.getStudentName());
		ps.setString(2, stu.getStudentPhone());
		ps.setString(3, stu.getStudentCity());
		ps.executeUpdate();
		con.close();
		return true;
	}
	
	public static List<Student> queryStudents() throws ClassNotFoundException, SQLException {
		Connection con=DbConnection.createDbConnection();
		String query="select * from student";
		Statement stmnt=con.createStatement();
		ResultSet rs=stmnt.executeQuery(query);
		List<Student> list=new ArrayList<>();
		while(rs.next()) {
			int id=rs.getInt(1);
			String sname=rs.getString(2);
			String sphone=rs.getString(3);
			String scity=rs.getString(4);
			Student stu=new Student(id, sname, sphone, scity);
			list.add(stu);
		}
		con.close();
		return list;
	}
	public static void deleteStudent(int id) {
		try {
			Connection con=DbConnection.createDbConnection();
			String query="delete from student where sid=9";
			Statement stmnt=con.createStatement();
			boolean res=stmnt.execute(query);
			System.out.println("Result is: " +res);
			if(res==true) {
				stmnt.getResultSet();
			}
			else {
				int count=stmnt.getUpdateCount();
				System.out.println("No of rows affected= "+count);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
