import java.util.List;
import java.util.ArrayList;

public class Student{
	private String name;
	public int roll;
	private String email;
	public double gpa = 0;
	private List<Course> list_of_courses = new ArrayList<>();

	public Student(String name, int roll, String email){
		this.name = name;
		this.roll = roll;
		this.email = email;
	}

	void addCourse(String courseName, int ff, int mm, int oo){
		Course new_course;
		if(list_of_courses.size() < 3)
			new_course = new Course(courseName, 3);
		else
			new_course = new Course(courseName, 1.5);
		new_course.setMarkScheme(ff, mm, oo);
		list_of_courses.add(new_course);
	}

	boolean addCourseGrade(String courseName, int ff, int mm, int oo){
		for(Course element : this.list_of_courses){
			if(element.courseName.equals(courseName)){
				boolean place_holder = element.setStudentMark(ff, mm, oo);
				calculateGPA();
				return place_holder;
			}
		}
		return false;
	}

	int checkCourseEnrollment(String courseName){
		for(Course element : this.list_of_courses){
			if(element.courseName.equals(courseName)){
				return this.roll;
			}
		}
		return -1;
	}

	double getCourseGrade(String courseName){
		for(Course element : this.list_of_courses){
			if(element.courseName.equals(courseName)){
				return element.courseGrade;
			}
		}
		return -1;
	}

	void calculateGPA(){
		this.gpa = Evaluate.totalGPA(this.list_of_courses);
	}

	String getName(){
		return this.name;
	}

	void displayDetails(){
		System.out.println("Student Name: " + this.name);
		System.out.println("Student Roll: " + this.roll);
		System.out.println("Student Email: " + this.email);
		System.out.println("\nCurrent GPA: " + this.gpa);
		System.out.println("**Courses Enrolled**");
		for(Course element : this.list_of_courses){
			if(element != null)
				System.out.println(element.courseName + " (Grade: " + element.courseGrade + ")");
		}

	}
}