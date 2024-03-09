public class Client{
	//CLient Variables
	public String name;
	public String rank;
	public String type;
	public String email;
	public String accountNumber;
	public String bankName;
	public String phoneNumber;
	int bin = -1;
	int tin = -1;

	
	//classes within client
    class Employee extends Client{
    	public Employee(String name, String rank, String bankName){
    		super("Employee");
    		Client.this.type = "Employee";
    		Client.this.name = name;
    		Client.this.rank = rank;
    		Client.this.bankName = bankName;
    	}

    	boolean validate_rank(String rank){
    		if(rank.equals("Manager") || rank.equals("Officer") || rank.equals("Trainee"))
    			return true;
    		else
    			return false;
    	}
    }

	class Customer extends Client{
		class Organization{
			int idTIN;
			public Organization(int id)
			{
				this.idTIN = id;
			}
		}
		class SinglePerson{
			int idBIN;
			public SinglePerson(int id)
			{
				this.idBIN = id;
			}
		}

		public Customer(String name, String email, String accountNumber, String bankName, String phoneNumber){
			super("Customer");
            Client.this.name = name;
            Client.this.email = email;
            Client.this.accountNumber = accountNumber;
            Client.this.bankName = bankName;
            Client.this.phoneNumber = phoneNumber;
		}
		void setTypeStatus(String status, int id)
		{
			if("Organization".equals(status)){
				Organization org = new Organization(id);
				Client.this.tin = org.idTIN;
			}
			else{
				SinglePerson pers = new SinglePerson(id);
				Client.this.bin = pers.idBIN;
			}
		}
	}


	//functions and constructors
	public Client(String type)
	{
		this.type = type;
	}

	public Customer createCustomer(String name, String email, String accountNumber, String bankName, String phoneNumber) {
        return new Customer(name, email, accountNumber, bankName, phoneNumber);
    }

    public Employee createEmployee(String name, String rank, String bankName) {
        return new Employee(name, rank, bankName);
    }

    boolean validate()
    {
    	if(tin == -1 && bin == -1)
    		return true;
    	else
    		return false;
    }

    void printID()
    {
    	if(bin == -1){
    		System.out.println("Organization TIN: " + this.tin);
    	}
    	else{
    		System.out.println("Personal BIN: " + this.bin);
    	}

    }
	void printCustomer()
	{
		System.out.println("Client type: " + this.type);
		System.out.println("Name: " + this.name);
		System.out.println("Email: " + this.email);
		System.out.println("Name of Bank: " + this.bankName);
		System.out.println("Account Number: " + this.accountNumber);
		System.out.println("Contact Number: " + this.phoneNumber);
		printID();
		System.out.println("");
	}
	void printEmployee()
	{
		System.out.println("Client type: " + this.type);
		System.out.println("Bank: " + this.bankName);
		System.out.println("Name: " + this.name);
		System.out.println("Rank: " + this.rank);
		System.out.println("\n\n");
	}

}