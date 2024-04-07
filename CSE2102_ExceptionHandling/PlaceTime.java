import java.util.regex.*;

class PlaceTime{
	private String address1 = "";
	private String address2 = "";
	private String address3 = "";
	private int day = 0;
	private String month = "";
	private int year = 0;

	void showDetails(){
		System.out.println("| DoB:\t\t" + this.day + " " + this.month + " " + this.year);
        boolean firstFlag = true;
        System.out.println("| Address:\n");
        for(int i =1; i<4; i++){
            try{
                boolean dummy = validateAddress(i);
            } catch(ExceptionHandling e){
                System.err.println(e.getMessage());
                firstFlag = false;
            }
        }
        if(firstFlag){
            System.out.println("\t" + this.address1 + "\n\t" + this.address2 + "\n\t" + this.address3);
        }
	}

	void takeAddress(String p1, String p2, String p3){
		this.address1 = p1;
        this.address2 = p2;
        this.address3 = p3;
	}
	void takeBirthday(int d, String m, int y){
		this.day = d;
        this.month = m;
        this.year = y;
	}

	boolean validateBirthday() throws ExceptionHandling{
		if(day == 0 && month.equals("") && year == 0){
			throw new ExceptionHandling("birthday", 2);
		}
		if (day <= 0 || day > 31) {
            throw new ExceptionHandling("birthday", 1);
        }
        if (!isValidMonth(month) || year > 2024) {
            throw new ExceptionHandling("birthday", 1);
        }
        return true;
	}

	private boolean isValidMonth(String month) {
        String[] validMonths = {"January", "February", "March", "April", "May", "June",
                                "July", "August", "September", "October", "November", "December"};
        for (String validMonth : validMonths) {
            if (validMonth.equalsIgnoreCase(month)) {
                return true;
            }
        }
        return false;
    }

	boolean validateAddress(int field) throws ExceptionHandling{
		if (!isValidAddress(address1) && field == 1) {
            throw new ExceptionHandling("address", 1);
        }
        if (!isValidLocation(address2) && field == 2) {
            throw new ExceptionHandling("address", 2);
        }
        if (!isValidDivision(address3) && field == 3) {
            throw new ExceptionHandling("address", 3);
        }
		return true;
	}
	private boolean isValidAddress(String addressComponent) {
        String pattern = "^[0-9A-Z/, ]+$";
        return Pattern.matches(pattern, addressComponent);
    }
    private boolean isValidLocation(String addressComponent) {
        String pattern = "^[A-Za-z, ]+$";
        return Pattern.matches(pattern, addressComponent);
    }
	private boolean isValidDivision(String division) {
        String[] validDivisions = {"Dhaka", "Chattogram", "Barishal", "Khulna", "Sylhet", "Rajshahi", "Mymensingh", "Rangpur"};
        for (String validDivision : validDivisions) {
            if (validDivision.equalsIgnoreCase(division)) {
                return true;
            }
        }
        return false;
    }
}