import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import com.student.manage.Student;
import com.student.manage.StudentDao;

public class Start {
	public static void main(String[] args)
			throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		System.out.println("Hi from jdbc ");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Press 1 to ADD Student");
			System.out.println("Press 2 to Display Student");
			System.out.println("Press 3 to delete Students");
			System.out.println("Press 4 to exit app");
			System.out.println("Press 6 to search students based on city");
			System.out.println();
			int c = Integer.parseInt(bf.readLine());
			if (c == 1) {
				System.out.println("Enter Student name....");

				String name = bf.readLine();
				System.out.println("Enter Student Phone Number");
				String phone = bf.readLine();
				System.out.println("Enter Student city...");
				String city = bf.readLine();
				Student student = new Student(name, phone, city);
				System.out.println(student);
				StudentDao.insertStudentIntoDbV1(student);

			} else if (c == 2) {
				// List<Student> students = StudentDao.queryStudents();
				List<Student> students = StudentDao.fetchStudentsInSortedOrder();
				System.out.println(students);
				for (Student s : students) {
					System.out.println(s);
					System.out.println();
				}

			} else if (c == 3) {
				System.out.println("Enter student id to delete");
				int id = Integer.parseInt(bf.readLine());
				// StudentDao.deleteStudent(id);
				System.out.println("Rows affected= " + StudentDao.deleteStudentV1(id));

			} else if (c == 4) {
				System.out.println("Enter student details to update");
				StudentDao.updateStudent();
			} else if (c == 5) {
				break;
			} else if (c == 6) {
				System.out.println("Enter city name to fetch students");
				String city = bf.readLine();
				List<String> names = StudentDao.fetchStudentFromRequiredCity(city);
				for (String name : names) {
					System.out.println(name);
				}
			} else {

			}
		}
		System.out.println("App Exited");
	}

}
