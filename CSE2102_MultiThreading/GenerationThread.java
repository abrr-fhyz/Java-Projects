import java.util.HashMap;

class GenerationThread extends Thread {
	private HashMap<Integer, Account> accountList;
	private HashMap<Integer, Data> transactions;
	private boolean isDeposit;

	GenerationThread(HashMap<Integer, Account> accountList) {
		this.accountList = accountList;
	}

	GenerationThread(HashMap<Integer, Account> accountList, HashMap<Integer, Data> transactions, boolean isDeposit) {
		this.accountList = accountList;
		this.transactions = transactions;
		this.isDeposit = isDeposit;
	}

	void accountGeneration() {
		for(int i = 0; i < 30; i++){
			Account account = new Account();
			account.setAccountHolder(RandomGenerator.accountHolder());
			account.setAccountNumber(RandomGenerator.accountNumber());
			account.setTransactionLimit(RandomGenerator.transactionLimit());
			this.accountList.put(i, account);
			accountList.get(i).printDetails();
			try {
            	Thread.sleep(1000);
        	} catch (InterruptedException e) {}
		}
	}

	void accountTransactions() {
		for(int i = 0; i < 501; i++){

			int accountID = RandomGenerator.getNumber(this.accountList.size());
			while(this.accountList.get(accountID) == null) {
				accountID = RandomGenerator.getNumber(this.accountList.size());
			}
			//String accountNumber = this.accountList.get(accountID).getAccountNumber();
			int transactionAmount;
			if(isDeposit){
				transactionAmount = RandomGenerator.depositAmount();
			}
			else{
				transactionAmount = RandomGenerator.withdrawAmount();
			}
			Data data = new Data(this.accountList.get(accountID), transactionAmount);
			this.transactions.put(i, data);
			try {
            	Thread.sleep(250);
        	} catch (InterruptedException e) {}
        	if(isDeposit){
        		this.transactions.get(i).printDetails("Deposit Request " + i);
        	}
        	else{
        		this.transactions.get(i).printDetails("Withdraw Request " + i);
        	}
		}
	}

	@Override
	public void run() {
		if(this.transactions == null){
			accountGeneration();
		}
		else{
			accountTransactions();
		}
	}

}