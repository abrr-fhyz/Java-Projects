import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

interface Menu{

	private static void showOptions(){
		System.out.println("----\tUSER INTERFACE\t----");
		System.out.println("\t1. Enter New Person\n\t2. Edit Person Address Info\n\t3. Query Person Info\n\t4. Show All\n");
	}

	private static void enterName(Person person, Scanner sc){
		System.out.print("Enter FirstName: ");
		String fn = sc.nextLine();
		System.out.print("Enter MiddleName: ");
		String mn = sc.nextLine();
		System.out.print("Enter LastName: ");
		String ln = sc.nextLine();
		person.setPersonName(fn, mn, ln);
	}

	private static void enterEmail(Person person, Scanner sc){
		System.out.print("Enter Email Address: ");
		String email = sc.nextLine();
		person.setPersonEmail(email);
	}

	private static void enterIdentity(Person person, Scanner sc){
		System.out.print("Enter Passport Number: ");
		String pass = sc.nextLine();
		System.out.print("Enter NID Number: ");
		String nid = sc.nextLine();
		person.setPersonIdentity(pass, nid);
	}

	private static void enterBirthday(Person person, Scanner sc){
		System.out.println("Enter your DateOfBirth:\n[eg:\t13\n\tJanuary\n\t2023]");
		int d = sc.nextInt();
		sc.nextLine();
		String m = sc.nextLine();
		int yr = sc.nextInt();
		sc.nextLine();
		person.setPersonBirthday(d, m, yr);
	}

	private static void enterAddress(Person person, Scanner sc){
		System.out.print("P1: ");
		String p1 = sc.nextLine();
		System.out.print("P2: ");
		String p2 = sc.nextLine();
		System.out.print("P3: ");
		String p3 = sc.nextLine();
		person.setPersonAddress(p1, p2, p3);
	}

	private static void showPeople(HashMap<Integer, Person> listOfPeople){
		for (Map.Entry<Integer, Person> entry : listOfPeople.entrySet()) {
        	Integer key = entry.getKey();
        	Person person = entry.getValue();
        	System.out.println("| " + key + ".\t" + person.getPersonName());
    	}
	}

	private static Person getPerson(HashMap<Integer, Person> listOfPeople, int index) throws ExceptionHandling{
		if (index > listOfPeople.size() || index < 0){
			throw new ExceptionHandling("null");
		}
		return listOfPeople.get(index);
	}

	static void menuLoop(HashMap<Integer, Person> listOfPeople, int count){
		showOptions();
		Scanner sc = new Scanner(System.in);
		int cmd = sc.nextInt();
		sc.nextLine();
		switch(cmd){
			case 1:
				boolean flag = false;
				System.out.println("----\tNew Person\t----");
				Person person = new Person();
				while(!flag){
					enterName(person, sc);
					flag = Validator.validate("Name", person);
				}
				flag = false;
				while(!flag){
					enterEmail(person, sc);
					flag = Validator.validate("Email", person);
				}
				flag = false;
				System.out.println("----\tEnter any ONE of the following:");
				while(!flag){
					enterIdentity(person, sc);
					flag = Validator.validate("Iden", person);
				}
				flag = false;
				while(!flag){
					enterBirthday(person, sc);
					flag = Validator.validate("Birth", person);
				}
				flag = false;
				System.out.println("Enter your Address");
				enterAddress(person, sc);
				flag = Validator.checkAddress(person);
				if(!flag){
					System.out.println("Issue(s) detected with address. Please fix.");
				}
				listOfPeople.put(count, person);
				count += 1;
				System.out.println("----\tNew Person Added!!\n\n");
				break;
			case 2:
				System.out.println("----\tEdit Person Address\t----");
				int index = sc.nextInt();
				sc.nextLine();
				Person newPerson = new Person();
				try{
			 		newPerson = getPerson(listOfPeople, index);
				} catch(ExceptionHandling e){
					System.err.println(e.getMessage());
					break;
				}
				flag = false;
				while(!flag){
					System.out.println("----\tNew Address:");
					enterAddress(newPerson, sc);
					flag = Validator.validate("Loc", newPerson);
				}
				System.out.println("----\tErrors Fixed!!\n\n");
				break;
			case 3:
				System.out.println("----\tSearch Person\t----");
				index = sc.nextInt();
				newPerson = new Person();
				try{
			 		newPerson = getPerson(listOfPeople, index);
				} catch(ExceptionHandling e){
					System.err.println(e.getMessage());
					break;
				}
				newPerson.showPersonDetails();
				System.out.println("\n\n");
				break;
			case 4:
				System.out.println("----\tShow All People\t----");
				showPeople(listOfPeople);
				System.out.println("\n");
				break;
			default:
				System.out.println("Noooooo!\n\n");
		}
		menuLoop(listOfPeople, count);
	}
}