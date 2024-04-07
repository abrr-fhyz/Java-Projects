class Person{
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String email = "";
	private String passportNumber = "";
	private String nidNumber = "";
	private PlaceTime placetime;

	String getPersonName(){
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}

	void showPersonDetails(){
		System.out.println("| Name:\t\t" + this.firstName + " " + this.middleName + " " + this.lastName);
		System.out.println("| Email:\t" + this.email);
		if(passportNumber.isEmpty()){
			System.out.println("| NID:\t\t" + this.nidNumber);
		}
		else{
			System.out.println("| Passport ID:\t" + this.passportNumber);
		}
		this.placetime.showDetails();
		System.out.println("\n");
	}

	void setPersonName(String name1, String name2, String name3){
		this.firstName = name1;
		this.middleName = name2;
		this.lastName = name3;
		this.placetime = new PlaceTime();
	}

	void setPersonEmail(String email){
		this.email = email;
	}
	void setPersonIdentity(String pass, String nid){
		this.passportNumber = pass;
		this.nidNumber = nid;
	}
	void setPersonBirthday(int d, String m, int y){
		this.placetime.takeBirthday(d, m, y);
	}
	void setPersonAddress(String p1, String p2, String p3){
		this.placetime.takeAddress(p1, p2, p3);
	}

	boolean validateIdentity() throws ExceptionHandling{
		if (passportNumber.isEmpty() && nidNumber.isEmpty()) {
        	throw new ExceptionHandling("iden", 1);
    	}

    	if (!passportNumber.isEmpty()) {
    		if(passportNumber.length() != 9){
    			throw new ExceptionHandling("iden", 3);
    		}
    		String prefix = passportNumber.substring(0, 2);
    		String suffix = passportNumber.substring(2, 9);
        	if (!prefix.matches("[A-Z]+") || !suffix.matches("[0-9]+")) {
            	throw new ExceptionHandling("iden", 3);
        	} 
    	}

    	if (!nidNumber.isEmpty()) {
        	if (nidNumber.length() != 11 || !nidNumber.matches("[0-9]+")) {
            	throw new ExceptionHandling("iden", 2);
        	}
    	}

    	return true;
	}

	boolean validateEmail() throws ExceptionHandling{
		if(email.isEmpty()){
			throw new ExceptionHandling("email", 1);
		}
		String prefix = email.substring(0, email.indexOf('@'));
        if (!prefix.matches("^[a-z0-9]+$")){
        	throw new ExceptionHandling("email", 3);
        }
        if(!email.endsWith("@gmail.com")){
			throw new ExceptionHandling("email", 2);
		}

        return true;
	}

	boolean validateNames() throws ExceptionHandling {
		if ((firstName + middleName + lastName).isEmpty()) {
            throw new ExceptionHandling("name", 3);
        }
        if (firstName.isEmpty()) {
            throw new ExceptionHandling("name", 1);
        }
        if (lastName.isEmpty()) {
            throw new ExceptionHandling("name", 2);
        }

        return true;
    }

    boolean validateAddress(int field, int condition){
    	try{
    		return placetime.validateAddress(field);
    	} catch(ExceptionHandling e){
    		if(condition != 0){
    			System.err.println(e.getMessage());
    		}
    	}  
    	return false;  	
    }
    boolean validateBirthday(){
    	try{
    		return placetime.validateBirthday();
    	} catch(ExceptionHandling e){
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
}