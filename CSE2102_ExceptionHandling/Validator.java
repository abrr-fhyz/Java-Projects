interface Validator{
	static boolean validate(String type, Person person){
		boolean output = false;
		if(type.equals("Name")){
			try {
            	output = person.validateNames();
        	} catch (ExceptionHandling e) {
            	System.err.println(e.getMessage());
        	}
        	return output;
		}
		if(type.equals("Email")){
			try {
            	output = person.validateEmail();
        	} catch (ExceptionHandling e) {
            	System.err.println(e.getMessage());
        	}
        	return output;
		} 
		if(type.equals("Iden")){
			try {
            	output = person.validateIdentity();
        	} catch (ExceptionHandling e) {
            	System.err.println(e.getMessage());
        	}
        	return output;
		} 
		if(type.equals("Birth")){
            output = person.validateBirthday();
        	return output;
		} 
		if(type.equals("Loc")){
			boolean flag = true;
			for(int i = 1; i < 4; i++){
            	output = person.validateAddress(i, 1);
            	if(!output){
            		flag = output;
            	}
			}
        	return flag;
		} 
		return output;
	}

    static boolean checkAddress(Person person){
        boolean flag = true;
        for(int i = 1; i < 4; i++){
            boolean output = person.validateAddress(i, 0);
            if(!output){
                    flag = output;
                }
            }
        return flag;
    }
}
