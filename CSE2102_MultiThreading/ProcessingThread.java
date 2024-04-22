import java.util.HashMap;

class ProcessingThread extends Thread {
	private HashMap<Integer, Data> transactions;
	private boolean isDeposit;
	private int sleepingTime;

	ProcessingThread(HashMap<Integer, Data> transactions, boolean isDeposit, int time) {
		this.transactions = transactions;
		this.isDeposit = isDeposit;
		this.sleepingTime = time;
	}

	void processDeposit(){
		boolean continueLoop = true;
		while(continueLoop){
			int key;
			for(key=0; key<501; key++){
				if(this.transactions.get(key) != null){
					if(!this.transactions.get(key).beingProcessed()){
						this.transactions.get(key).processing();
						break;
					}
				}
				if(key == 499){
					continueLoop = false;
				}
			}
			if(!continueLoop)
			{
				break;
			}
			Data data = transactions.get(key);
			boolean validDeposit = Validate.deposit(data, key);
			if(validDeposit){
				data.getAccount().deposit(data.getTransactionAmount());
				System.out.println("---- \t\t SUCCESSFUL DEPOSIT TRANSACTION:\t" + data.getAccount().getAccountHolder() + "   \t|| " + data.getAccount().getBalance() + " BDT (+" + data.getTransactionAmount() +")");
				//data.getAccount().printDetails();
			}
			try {
            	Thread.sleep(sleepingTime);
       		} catch (InterruptedException e) {}
       		transactions.remove(key);
    	}
	}

	void processWithdrawal(){
		boolean continueLoop = true;
		while(continueLoop){
			int key;
			for(key=0; key<501; key++){
				if(this.transactions.get(key) != null){
					if(!this.transactions.get(key).beingProcessed()){
						this.transactions.get(key).processing();
						break;
					}
				}
				if(key == 499){
					continueLoop = false;
				}
			}
			if(!continueLoop)
			{
				break;
			}
			Data data = transactions.get(key);
			boolean validWithdrawal = Validate.withdraw(data, key);
			if(validWithdrawal){
				data.getAccount().withdraw(data.getTransactionAmount());
				System.out.println("---- \t\t SUCCESSFUL WITHDRAWAL TRANSACTION:\t" + data.getAccount().getAccountHolder() + "  \t|| " + data.getAccount().getBalance() + " BDT (-" + data.getTransactionAmount() +")");
				//data.getAccount().printDetails();
			}
			try {
            	Thread.sleep(sleepingTime);
       		} catch (InterruptedException e) {}
       		transactions.remove(key);
    	}
	}



	@Override
	public void run() {
		if(isDeposit){
			processDeposit();
		}
		else{
			processWithdrawal();
		}
	}

}