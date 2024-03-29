import java.util.HashMap;
import java.util.Arrays;
import java.util.Comparator;

public abstract class Rank{

	static class Pair extends Rank{
		double gpa = 0;
		int roll;
		Pair(int roll, double gpa){
			this.gpa = gpa;
			this.roll = roll;
		}

		double getGPA(){
			return this.gpa;
		}

	}

	static void courseRankList(String courseName, HashMap<Integer, Student> list_of_students){
		System.out.println("COURSE RANK LIST [" + courseName + "]:\n");
		Pair [] pairs = new Pair[list_of_students.size()];
		int cnt = 0;
		for(Student student : list_of_students.values()){
			if(student.checkCourseEnrollment(courseName) != -1){
				double gpa = student.getCourseGrade(courseName);
				pairs[cnt] = new Pair(student.roll, gpa);
				cnt++;
			}
		}
		pairs = Arrays.copyOf(pairs, cnt);
		Arrays.sort(pairs, (p1, p2) -> {
            if (p1.getGPA() == p2.getGPA()) {
                Student student1 = list_of_students.get(p1.roll);
                Student student2 = list_of_students.get(p2.roll);
                if (student1 != null && student2 != null) {
                    return Integer.compare(student2.getCourseMark(courseName), student1.getCourseMark(courseName));
                }
            }
            return Double.compare(p2.getGPA(), p1.getGPA());
        });
		int rank = 1;
		for(int i = 0; i < cnt; i++){
			Student temp = list_of_students.get(pairs[i].roll);
			if(temp != null){
				System.out.println(rank + ". " + temp.getName() + " [Grade: " + temp.getCourseGrade(courseName) + ", Mark: " + temp.getCourseMark(courseName) + "]");
				rank += 1;
			}
		}
		System.out.println("\n");
	}

	static void totalRankList(HashMap<Integer, Student> list_of_students){
		Pair [] pairs = new Pair[list_of_students.size()];
		int cnt = 0;
		for(Student student : list_of_students.values()){
			pairs[cnt] = new Pair(student.roll, student.gpa);
			cnt++;
		}
		pairs = Arrays.copyOf(pairs, cnt);
		Arrays.sort(pairs, (p1, p2) -> {
            if (p1.getGPA() == p2.getGPA()) {
                Student student1 = list_of_students.get(p1.roll);
                Student student2 = list_of_students.get(p2.roll);
                if (student1 != null && student2 != null) {
                    return Integer.compare(student2.totalStudentMark, student1.totalStudentMark);
                }
            }
            return Double.compare(p2.getGPA(), p1.getGPA());
        });
		int rank = 1;
		System.out.println("\nCOMBINED RANK LIST:\n");
		for(int i = 0; i <= list_of_students.size() - 1; i++){
			Student temp = list_of_students.get(pairs[i].roll);
			if(temp != null){
				System.out.println(rank + ". " + temp.getName() + " [CGPA: " + temp.gpa + ", Marks: " + temp.totalStudentMark + "]");
				rank += 1;
			}
		}
		System.out.println("\n");
	}

}
