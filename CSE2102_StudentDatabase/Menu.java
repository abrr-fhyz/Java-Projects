import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu{
	static int no_students = 4;
	static void showCourses(){
		System.out.println("1. ArtificialIntelligence");
		System.out.println("2. Security");
		System.out.println("3. OperationResearch");
		System.out.println("4. Networking");
		System.out.println("5. EmbeddedSystems");
		System.out.println("\n");
	}

	static void createRandomStudents(HashMap<Integer, Student> list_of_students, int n){
		Randomiser rd = new Randomiser();
		for(int i = no_students; i <= n + no_students; i++){
			rd.createIdentity();
			Student student = new Student(rd.getName(), i, rd.getEmail());
			rd.handleCourses(student);
			list_of_students.put(i, student);
		}
		no_students += n;
	}

	static void mainMenu(HashMap<Integer, Student> list_of_students){
		Scanner sc = new Scanner(System.in);
		System.out.println("-----MENU-----");
		System.out.println("1. Enter New Student\n2. Show Student Details\n3. Select Courses\n4. Enter Scores\n5. Display Course Enrollment Data\n6. Course Rank List\n7. Combined Rank List\n8. Create new students, I guess?\n");
		int cmd = sc.nextInt();
		sc.nextLine();
		switch(cmd){
			case 1:
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("Email: ");
				String email = sc.nextLine();
				System.out.print("Roll: ");
				int roll = sc.nextInt();
				Student student = new Student(name, roll, email);
				list_of_students.put(roll, student);
				System.out.println("\n");
				break;
			case 2:
				System.out.print("Student Roll: ");
				roll = sc.nextInt();
				Student temp = list_of_students.get(roll);
				temp.displayDetails();
				System.out.println("\n");
				break;
			case 3:
				System.out.print("Student Roll: ");
				roll = sc.nextInt();
				System.out.println("Select Course: ");
				showCourses();
				int courseID = sc.nextInt();
				String courseName = Evaluate.getName(courseID);
				if(!courseName.equals("N/A")){
					System.out.println("Enter Mark Scheme >> Final :: Midterm :: RegularAssessment");
					int fin = sc.nextInt();
					int mid = sc.nextInt();
					int oth = sc.nextInt();
					boolean flag = Evaluate.ceiling(fin, mid, oth);
					if(flag){
						temp = list_of_students.get(roll);
						temp.addCourse(courseName, fin, mid, oth);
						break;
					}
				}
				System.out.println("ERR. Try again.");
				break;
			case 4: 
				System.out.print("Student Roll: ");
				roll = sc.nextInt();
				System.out.println("Select Course: ");
				showCourses();
				courseID = sc.nextInt();
				courseName = Evaluate.getName(courseID);
				if(!courseName.equals("N/A")){
					System.out.println("Enter Scores >> Final :: Midterm :: RegularAssessment");
					int fin = sc.nextInt();
					int mid = sc.nextInt();
					int oth = sc.nextInt();
					temp = list_of_students.get(roll);
					boolean flag = temp.addCourseGrade(courseName, fin, mid, oth);
					if(flag)
						break;
				}
				System.out.println("ERR. Try again.");
				break;
			case 5:
				System.out.println("Select Course: ");
				showCourses();
				courseID = sc.nextInt();
				Evaluate.displayCourseEnrollment(courseID, list_of_students);
				break;
			case 6:
				System.out.println("Select Course: ");
				showCourses();
				courseID = sc.nextInt();
				courseName = Evaluate.getName(courseID);
				Rank.courseRankList(courseName, list_of_students);
				break;
			case 7:
				Rank.totalRankList(list_of_students);
				break;
			case 8:
				System.out.println("How many students do you want to integrate?");
				int n = sc.nextInt();
				createRandomStudents(list_of_students, n);
				System.out.println("New Students added!!");
				break;
			default:
				System.out.println("UNRECOGNISED");
				break;
		}
		mainMenu(list_of_students);
	}

}
