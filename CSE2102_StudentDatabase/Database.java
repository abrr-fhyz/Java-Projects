import java.util.HashMap;

public class Database{
	public static void main(String [] args){
		HashMap<Integer, Student> list_of_students = new HashMap<>();
		//custom students
		boolean dummy;
		Student student = new Student("Abrar Fahyaz", 1, "abrar@gmail.com");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("Security", 60, 30, 10);
		student.addCourse("Networking", 60, 30, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 61, 15, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 80, 15, 5);
		dummy = student.addCourseGrade("Security", 43, 12, 10);
		dummy = student.addCourseGrade("Networking", 51, 21, 7);
		list_of_students.put(1, student);
		student = new Student("Barack H. Obama", 2, "brac@obamna.usa");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("OperationResearch", 60, 30, 10);
		student.addCourse("Networking", 70, 20, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 70, 15, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 80, 15, 4);
		dummy = student.addCourseGrade("Networking", 63, 18, 9);
		dummy = student.addCourseGrade("OperationResearch", 55, 20, 9);
		list_of_students.put(2, student);
		student = new Student("Maisha Rahman", 3, "mmrrhm@gmail.com");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("Security", 60, 30, 10);
		student.addCourse("OperationResearch", 60, 30, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 70, 25, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 75, 15, 4);
		dummy = student.addCourseGrade("Security", 55, 28, 9);
		dummy = student.addCourseGrade("OperationResearch", 60, 30, 9);
		list_of_students.put(3, student);
		Menu.createRandomStudents(list_of_students, 20);
		Menu.mainMenu(list_of_students);
	}
}
