import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public interface Evaluate{

	static String getName(int id){
		if(id == 1)
			return "ArtificialIntelligence";
		if(id == 2)
			return "Security";
		if(id == 3)
			return "OperationResearch";
		if(id == 4)
			return "Networking";
		if(id == 5)
			return "EmbeddedSystems";
		return "N/A";
	}

	static boolean ceiling(int a, int b, int c){
		if(a + b + c == 100)
			return true;
		else
			return false;
	}

	static double courseGrade(int f, int m, int o){
		int total = f + m + o;
		if(total >= 80)
			return 4;
		if(total >= 75)
			return 3.75;
		if(total >= 70)
			return 3.5;
		if(total >= 65)
			return 3.25;
		if(total >= 60)
			return 3;
		if(total >= 55)
			return 2.75;
		if(total >= 50)
			return 2.5;
		if(total >= 45)
			return 2.25;
		if(total >= 40)
			return 2;
		else
			return 0;
	}

	static double totalGPA(List<Course> list_of_courses){
		double numerator = 0;
		double denominator = 0;
		for(Course element : list_of_courses){
			numerator += (element.courseGrade) * (element.credits);
			denominator += element.credits;
		}
		return (numerator / denominator);
	}

	static int totalMark(List<Course> list_of_courses){
		int marks = 0;
		for(Course element : list_of_courses){
			marks += element.totalMark;
		}
		return marks;
	}

	static void printMarkScheme(Course course){
		course.showMarkScheme();
	}

	static void displayCourseEnrollment(int courseID, HashMap<Integer, Student> list_of_students){
		int cnt = 1;
		String courseName = getName(courseID);
		System.out.println("Enrollment in Course - " + courseName);
		for(Student student : list_of_students.values()){
			if(student.checkCourseEnrollment(courseName) != -1){
				if(cnt < 10)
					System.out.print("0" + cnt + ". " + student.getName() + ", Roll: " + student.roll +" || ");
				else
					System.out.print(cnt + ". " + student.getName() + ", Roll: " + student.roll +" || ");
				student.showCourseData(courseName);
				cnt += 1;
			}
		}
		System.out.println("\n");
	}
}
