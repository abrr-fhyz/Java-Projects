class Data{
	private String accountNumber;
	private int transactionAmount;
	private Account account;
	private boolean beingProcessed = false;

	Data(Account account, int integer){

		this.account = account;
		this.transactionAmount = integer;
		this.accountNumber = account.getAccountNumber();
	}

	boolean beingProcessed() {
		return this.beingProcessed;
	}

	void processing() {
		this.beingProcessed = true;
	}

	String getString() {
		return this.accountNumber;
	}

	int getTransactionAmount() {
		return this.transactionAmount;
	}

	Account getAccount() {
		return this.account;
	}

	void printDetails(String action) {
		System.out.println(getString() + "\t\t|| " + action + ": " + getTransactionAmount() +" BDT");
	}
}