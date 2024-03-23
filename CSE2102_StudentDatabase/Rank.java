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
		Arrays.sort(pairs, Comparator.comparingDouble(Pair::getGPA));
		int rank = 1;
		for(int i = cnt - 1; i >= 0; i--){
			Student temp = list_of_students.get(pairs[i].roll);
			if(temp != null){
				System.out.println(rank + ". " + temp.getName() + " (" + temp.getCourseGrade(courseName) + ")");
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
		Arrays.sort(pairs, Comparator.comparingDouble(Pair::getGPA));
		int rank = 1;
		System.out.println("\nCOMBINED RANK LIST:\n");
		for(int i = list_of_students.size() - 1; i >= 0; i--){
			Student temp = list_of_students.get(pairs[i].roll);
			if(temp != null){
				System.out.println(rank + ". " + temp.getName() + " (" + temp.gpa + ")");
				rank += 1;
			}
		}
		System.out.println("\n");
	}

}