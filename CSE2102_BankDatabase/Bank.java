public class Bank{
	String bankName;
	String address;
	int bankID;

	public void setUpBank(String name, String location, int bankid){
		this.bankName = name;
		this.address = location;
		this.bankID = bankid;
	}

	public void print(){
		System.out.println("Name: " + this.bankName + " (" + this.bankID + ")");
		System.out.println("Address: " + this.address);
	}
	
}
