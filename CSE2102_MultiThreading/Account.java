class Account{
	private String accountNumber;
	private String accountHolder;
	private int maxTransaction;
	private int accountBalance = 0;

	void setAccountHolder(String name) {
		this.accountHolder = name;
	}

	void setAccountNumber(String number) {
		this.accountNumber = number;
	}

	void setTransactionLimit(int limit) {
		this.maxTransaction = limit;
	}

	void deposit(int value) {
		this.accountBalance += value;
	}

	void withdraw(int value) {
		this.accountBalance -= value;
	}

	String getAccountNumber() {
		return this.accountNumber;
	}

	String getAccountHolder() {
		return this.accountHolder;
	}

	int getTransactionLimit() {
		return this.maxTransaction;
	}

	int getBalance() {
		return this.accountBalance;
	}

	void printDetails() {
		System.out.printf("%s\t%-20s\t%d\t|| %d BDT%n", getAccountNumber(), getAccountHolder(), getTransactionLimit(), getBalance());
	}

}