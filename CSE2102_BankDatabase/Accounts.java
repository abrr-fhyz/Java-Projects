public class Accounts{
	int client_id;
	public double balance = 0;

	public Accounts(int id)
	{
		this.client_id = id;
	}


	void cheque(double cash, String name){
		this.deductMoney(cash);
		System.out.println("\n\nTHIS IS A CHEQUE OF AMOUNT " + cash + " FROM " + name + "(CLIENT ID. " + this.client_id + ")\n\n");
	}
	void creditcard(double cash, String credit){
		this.deductMoney(cash);
		System.out.println("\n\nTRANSACTION OF AMOUNT " + cash + " TO CREDITCARD NO. " + credit.charAt(0) + "*** (CLIENT ID. " + this.client_id + ")\n\n");
	}
	void bankReceipt(String date, double cash, String name)
	{
		this.deductMoney(cash);
		System.out.println("\n\nTHIS IS A BANK RECEIPT OF AMOUNT " + cash + ", SIGNED UNDER THE NAME " + name + "(CLIENT ID. " + this.client_id + ") ON " + date + ".\n\n");
	}
	void addMoney(double cash)
	{
		this.balance += cash;
	}
	void deductMoney(double cash)
	{
		this.balance -= cash;
	}
	void printBalance()
	{
		System.out.println("Current Balance: " + this.balance + "\n");
	}
	void Savings_Account(double money, double time){
		double interest = 0.025;
		money += money * interest * time;
		this.balance = money;
	}
	void Salary_Account(double money, double time){
		double interest = 0.02;
		money += money * interest * time;
		this.balance = money;
	}

}
