import java.util.HashMap;

public class Database{
	public static void main(String [] args){
		boolean dummy;
		HashMap<Integer, Student> list_of_students = new HashMap<>();
		Student student = new Student("Abrar", 1, "abrar@gmail.com");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("Security", 60, 30, 10);
		student.addCourse("Networking", 60, 30, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 61, 15, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 80, 15, 5);
		dummy = student.addCourseGrade("Security", 43, 12, 10);
		dummy = student.addCourseGrade("Networking", 51, 21, 7);
		list_of_students.put(1, student);
		student = new Student("Obama", 2, "brac@obamna.usa");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("OperationResearch", 60, 30, 10);
		student.addCourse("Networking", 70, 20, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 70, 15, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 80, 15, 4);
		dummy = student.addCourseGrade("Networking", 63, 18, 9);
		dummy = student.addCourseGrade("OperationResearch", 55, 20, 9);
		list_of_students.put(2, student);
		student = new Student("Fahyaz", 3, "fahyaz@gmail.com");
		student.addCourse("EmbeddedSystems", 70, 25, 5);
		student.addCourse("ArtificialIntelligence", 80, 15, 5);
		student.addCourse("Security", 60, 30, 10);
		student.addCourse("OperationResearch", 60, 30, 10);
		dummy = student.addCourseGrade("EmbeddedSystems", 51, 15, 3);
		dummy = student.addCourseGrade("ArtificialIntelligence", 60, 15, 4);
		dummy = student.addCourseGrade("Security", 45, 22, 5);
		dummy = student.addCourseGrade("OperationResearch", 55, 20, 9);
		list_of_students.put(3, student);
		Menu.mainMenu(list_of_students);
	}
}