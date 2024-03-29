import java.util.Random;

public class Randomiser{
	Random random = new Random();

	//Identity
	private String name, email;
	static int range = 122 - 97 + 1;
	private int len;

	private boolean checkVowel(char x){
		if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u')
			return true;
		else
			return false;
	}

	private void concat(char x){
		this.name += x;
		this.email += x;
		this.len += 1;
	}

	public void createIdentity(){
		this.name = "";
		this.email = "";
		int n1 = 6, n2 = 5;
		//first name
		this.len = 0;
		while(this.len < n1){
			char f = (char)(random.nextInt(range) + 97);
			if(this.len == 1 || this.len == 4){
				if(checkVowel(f))
					concat(f);
			}
			else{
				if((!checkVowel(f) && f != 'y') || f == 'a'){
					if(this.len == 0)
						concat((char)(f - 32));
					else if(f != 'x' && f != 'z' && f != 'a')
						concat(f);
				}
			}
		}
		//middle name
		char m = (char)(random.nextInt(range) + 65);
		this.name += " " + m + ". ";
		this.email += m;
		//last name
		this.len = 0;
		while(this.len < n2){
			char x = (char)(random.nextInt(range) + 97);
			if(this.len == 1 || this.len == 3){
				if(checkVowel(x))
					concat(x);
			}
			else{
				if(!checkVowel(x) && x != 'y'){
					if(len == 0)
						concat((char)(x - 32));
					else if(x != 'x' && x != 'z')
						concat(x);
				}
			}
		}
		this.email += "@gmail.com";
	}

	public String getName(){
		return this.name;
	}
	public String getEmail(){
		return this.email;
	}

	//courses
	private int[] createMarkScheme(){
		int[] finalMark = {60, 70};
		int[] incourseMark = {20, 30, 40};
		int[] otherMark = {10, 20};
		int[] output = new int[3];
		boolean flag = false;
		while(!flag){
			output[0] = finalMark[random.nextInt(2)];
			output[1] = incourseMark[random.nextInt(3)];
			output[2] = otherMark[random.nextInt(2)];
			if(Evaluate.ceiling(output[0], output[1], output[2]))
				flag = true;
		}
		return output;
	}

	private int[] createGradeScheme(int ff, int mm, int oo){
		int[] output = new int[3];
		output[0] = (random.nextInt(ff - 30) + 30);
		output[1] = (random.nextInt(mm - 10) + 10);
		output[2] = (random.nextInt(oo));
		return output;
	}

	//course handler
	public void handleCourses(Student student){
		int[] selectedCourses = new int[4];
        int index = 0;
        while (index < 4) {
            int newCourse = random.nextInt(5) + 1;
            if (!contains(selectedCourses, newCourse)) {
                selectedCourses[index] = newCourse;
                index += 1;
                String courseName = Evaluate.getName(newCourse);
                int[] markCeiling = createMarkScheme();
                student.addCourse(courseName, markCeiling[0], markCeiling[1], markCeiling[2]);
                int[] courseMarks = createGradeScheme(markCeiling[0], markCeiling[1], markCeiling[2]);
                boolean dummy = student.addCourseGrade(courseName, courseMarks[0], courseMarks[1], courseMarks[2]);
            }
        }
	}

	private static boolean contains(int[] arr, int value) {
        for (int num : arr) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}
