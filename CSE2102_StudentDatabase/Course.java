public class Course{
	public String courseName;
	public double credits;
	private int finalCeiling;
	private int midCeiling;
	private int otherCeiling;
	private int finalScore;
	private int midScore;
	private int otherScore;
	public double courseGrade;
	public int totalMark = 0;

	public Course(String courseName, double credits){
		this.courseName = courseName;
		this.credits = credits;
	}

	public void setMarkScheme(int ff, int mm, int oo){
		this.finalCeiling = ff;
		this.midCeiling = mm;
		this.otherCeiling = oo;
	}

	public void showMarkScheme(){
		System.out.println("Mark Scheme:: F: " + this.finalCeiling + ", M: " + this.midCeiling + ", A: " + this.otherCeiling);
	}

	public boolean setStudentMark(int ff, int mm, int oo){
		if(ff > finalCeiling || mm > midCeiling || oo > otherCeiling)
			return false;
		this.finalScore = ff;
		this.midScore = mm;
		this.otherScore = oo;
		this.totalMark = ff + mm + oo;
		this.courseGrade = Evaluate.courseGrade(finalScore, midScore, otherScore);
		return true;
	}
}
