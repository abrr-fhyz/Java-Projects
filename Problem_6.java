import java.util.Scanner;

class Processor{
	static String processName(String name){
		String finalName = toLowerCase(name);
		finalName = removeSpaces(finalName);
		return finalName;
	}

	static String processNumber(String unformattedNumber){
		String formattedNumber = "";
		int idx = 0, hyphen = 5, gap = 8;
		if(unformattedNumber.charAt(idx) == '+'){
			idx += 4;
			hyphen += 4;
			gap += 4;
		}
		formattedNumber += "+880 ";
		for(int i = idx; i < unformattedNumber.length(); i++){
			if(i == hyphen){
				formattedNumber += '-';
			}
			if(i == gap){
				formattedNumber += ' ';
			}
			formattedNumber += unformattedNumber.charAt(i);
		}
		return formattedNumber;
	}

	static String toLowerCase(String name){
		String lowerCase = "";
		for(int i = 0; i < name.length(); i++){
			int x = (int) name.charAt(i);
			if(x >= 65 && x <= 90)
				x += 32;
			lowerCase += (char) x;
		}
		return lowerCase;
	}

	private static String removeSpaces(String unformatted){
		String formatted = "";
		if(unformatted.charAt(0) != ' '){
			formatted += unformatted.charAt(0);
		}
		for(int i = 1; i < unformatted.length(); i++){
			if(unformatted.charAt(i) != ' '){
				formatted += unformatted.charAt(i);
			}
			else{
				if(unformatted.charAt(i-1) != ' '){
					formatted += unformatted.charAt(i);
				}
			}
		}
		return formatted;
	}

}

class PersonData{
	private String phoneUser;
	private String forSearching;
	private String phoneNumber;

	PersonData(String phoneUser, String phoneNumber){
		this.phoneNumber = phoneNumber;
		this.forSearching = phoneUser;
		setPhoneUserName();
	}

	boolean isStringPresent(String searchString) {
    int len = searchString.length(), idx = 0, lenBIG = this.forSearching.length();
    for (int i = 0; i <= lenBIG - len; i++) {
        if (this.forSearching.charAt(i) == searchString.charAt(idx)) {
            for (idx = 1; idx < len; idx++) {
                if (this.forSearching.charAt(i + idx) != searchString.charAt(idx)) {
                    break;
                }
            }
            if (idx == len) {
                return true;
            }
            	idx = 0;
        	}
    	}
    	return false;
	}

	String getPersonName(){
		return this.forSearching;
	}

	void printDetails(){
		System.out.println(this.phoneUser + " " + this.phoneNumber);
	}

	private void setPhoneUserName(){
		String personName = "";
		for(int i = 0; i < this.forSearching.length(); i++){
			if(i == 0){
				int x = (int) this.forSearching.charAt(i);
				personName += (char) (x - 32);
			}
			else if(this.forSearching.charAt(i - 1) == ' '){
				int x = (int) this.forSearching.charAt(i);
				personName += (char) (x - 32);
			}
			else{
				personName += this.forSearching.charAt(i);
			}
		}
		this.phoneUser = personName;
	}

}

class Lisan{
	private static PersonData[] paulAtreides;

	static void alGaib(PersonData[] phoneBook, String searchString){
		paulAtreides = new PersonData[1000];
		int fremen = 0;
		for(PersonData person: phoneBook){
			if(person != null){
				if(person.isStringPresent(searchString)){
					paulAtreides[fremen] = person;
					fremen += 1;
				}
			}
		}
		orderLexographically(paulAtreides);
		dukeOfArakis(paulAtreides);

	}

	private static void orderLexographically(PersonData[] information){
        int len = information.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = 0; j < len-i-1; j++) {
            	if(information[j] == null || information[j + 1] == null){
                	continue; 
            	}
                if(isSwapNecessary(information[j].getPersonName(), information[j+1].getPersonName())){
                    PersonData temp = information[j];
                    information[j] = information[j+1];
                    information[j+1] = temp;
                }
            }
        }
    }

    private static boolean isSwapNecessary(String string1, String string2){
    	int string1Len = string1.length();
    	int string2Len = string2.length();
    	int smallerLen;
    	if(string1Len > string2Len)
    		smallerLen = string2Len;
    	else
    		smallerLen = string1Len;

    	for(int i = 0; i < smallerLen; i++){
    		if(string1.charAt(i) != string2.charAt(i)){
    			int x = (int) string1.charAt(i);
    			int y = (int) string2.charAt(i);
    			if(x - y > 0)
    				return true;
    			else
    				return false;
    		}
    	}

    	if(string1Len - string2Len > 0)
    		return true;
    	else
    		return false;

    }

    private static void dukeOfArakis(PersonData[] paulAtreides){
    	for(PersonData muad_dib: paulAtreides){
			if(muad_dib != null){
				muad_dib.printDetails();
			}
		}
    }

}

public class Problem_6{
	public static void main(String [] args){
		PersonData[] phoneBook = new PersonData[1000];
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		sc.nextLine();
		for(int i=0; i<k; i++){
			String phoneUser = Processor.processName(sc.nextLine());
			String phoneNumber = Processor.processNumber(sc.nextLine());
			phoneBook[i] = new PersonData(phoneUser, phoneNumber);
		}
		String searchString = Processor.toLowerCase(sc.nextLine());
		Lisan.alGaib(phoneBook, searchString);
	}
}
