import java.util.HashMap;

public class Main{
	public static void main(String[] args){
		HashMap<Integer, Person> listOfPeople = new HashMap<>();
		Person randPerson = new Person();
		randPerson.setPersonName("Sheikh", "Mujibur", "Rahman");
		randPerson.setPersonEmail("mujib1971@gmail.com");
		randPerson.setPersonIdentity("AB1234567", "");
		randPerson.setPersonBirthday(17, "March", 1920);
		randPerson.setPersonAddress("32/A", "Dhandmondi", "Dhaka");
		listOfPeople.put(1, randPerson);
		Person newPerson = new Person();
		newPerson.setPersonName("Hussain", "Md.", "Ershad");
		newPerson.setPersonEmail("idid21coups@gmail.com");
		newPerson.setPersonIdentity("", "12345678901");
		newPerson.setPersonBirthday(1, "February", 1930);
		newPerson.setPersonAddress("32/b", "Blip12", "Dhaa");
		listOfPeople.put(2, newPerson);
		Menu.menuLoop(listOfPeople, 3);
	}
}