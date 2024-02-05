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
		Connection con = DbConnection.createDbConnection();
		String query = "insert into student(sname,sphone,scity) values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, stu.getStudentName());
		ps.setString(2, stu.getStudentPhone());
		ps.setString(3, stu.getStudentCity());
		ps.executeUpdate();
		con.close();
		return true;
	}

	public static boolean insertStudentIntoDbV1(Student stu) {
		try {
			Connection con = DbConnection.createDbConnection();
			String query = String.format("insert into student(sname,sphone,scity) values('%s','%s','%s')",
					stu.getStudentName(), stu.getStudentPhone(), stu.getStudentCity());
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(query);
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static List<Student> queryStudents() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.createDbConnection();
		String query = "select * from student";
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery(query);
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String sname = rs.getString(2);
			String sphone = rs.getString(3);
			String scity = rs.getString(4);
			Student stu = new Student(id, sname, sphone, scity);
			list.add(stu);
		}
		con.close();
		return list;
	}

	public static List<Student> fetchStudentsInSortedOrder() {
		List<Student> students = new ArrayList();
		try {
			Connection con = DbConnection.createDbConnection();
			String query = "select * from student order by sname";
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt(1);
				String sname = rs.getString(2);
				String sphone = rs.getString(3);
				String scity = rs.getString(4);
				Student stu = new Student(id, sname, sphone, scity);
				students.add(stu);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;

	}

	public static void updateStudent() {
		try {
			Connection con = DbConnection.createDbConnection();
			String query = "update student set scity='ttp' where sid=12";
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(query);
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void deleteStudent(int id) {
		try {
			Connection con = DbConnection.createDbConnection();
			String query = "delete from student where sid=9";
			Statement stmnt = con.createStatement();
			boolean res = stmnt.execute(query);
			System.out.println("Result is: " + res);
			if (res == true) {
				stmnt.getResultSet();
			} else {
				int count = stmnt.getUpdateCount();
				System.out.println("No of rows affected= " + count);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public static int deleteStudentV1(int id) {
		int rowsAffected = 0;
		try {
			Connection con = DbConnection.createDbConnection();
			Statement stmnt = con.createStatement();
			String query = String.format("delete from student where sid=%d", id);
			System.out.println(query);
			rowsAffected = stmnt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}

}
