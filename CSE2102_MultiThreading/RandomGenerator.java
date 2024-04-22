import java.util.Random;

interface RandomGenerator{
	static String accountHolder() {
		String[] firstNames = {"Sadman", "Nafis", "Mohammad", "Mohammed", "Asif", "Sadia", "Masha", "Nabila", "Ahnaf", "Abrar", "Nafisa", "Tahsina", "Tahsin", "Asfak", "Sakib", "Azmain"};
		String[] lastNames = {"Khan", "Ahmed", "Rahim", "Kabir", "Tasneem", "Akhter", "Hossain", "Rahman", "Yasir", "Malik", "Reeham", "Shoeb", "Islam"};
		Random rd = new Random();
		String name1 = firstNames[rd.nextInt(firstNames.length)];
		String name2 = lastNames[rd.nextInt(lastNames.length)];
		return name1 + " " + name2;
	}

	static String accountNumber() {
		String acc = "";
		Random rd = new Random();
		for(int i = 0; i<2; i++) {
			char x = (char)(rd.nextInt(122-97+1) + 97);
			acc += x;
		}
		for(int i = 0; i<10; i++) {
			int x = (char)(rd.nextInt(10));
			acc += x;
		}
		return acc;
	}

	static int transactionLimit() {
		Random rd = new Random();
		return rd.nextInt(10000) + 1;
	}

	static int depositAmount() {
		Random rd = new Random();
		return rd.nextInt(50000) + 1;
	}

	static int withdrawAmount() {
		Random rd = new Random();
		return rd.nextInt(100000) + 1;
	}

	static int getNumber(int limit) {
		Random rd = new Random();
		return rd.nextInt(limit + 1);
	}
}