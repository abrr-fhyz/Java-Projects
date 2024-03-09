import java.util.Scanner;
//Main class
public class Main{

	void main_menu(Bank[] list_of_banks, Client[] list_of_clients, Accounts[] list_of_accounts, int bankID, int clientID, Scanner sc)
	{
		System.out.println("### MENU ###");
		System.out.println("1. Enter new bank\n2. Show Bank Details\n3. Register Client\n4. Cash-in (Deposit money)\n5. Send Money\n6. Cash-out (Withdraw Money)\n");
		int command = sc.nextInt();
		sc.nextLine();
		switch(command){
			case 1:
				System.out.println("Enter bank name:");
				String name = sc.nextLine();
				System.out.println("Enter bank address:");
				String location = sc.nextLine();
				list_of_banks[bankID] = new Bank();
				list_of_banks[bankID].setUpBank(name, location, bankID);
				System.out.println("Bank logged! The bankID is " + bankID + "\n\n");
				bankID += 1;
				break;
			case 2:
				System.out.println("Enter bankID:");
				int id = sc.nextInt();
				list_of_banks[id].print();
				System.out.println("\n\n");
				break;
			case 3:
				System.out.println("Client type:");
				String type = sc.nextLine();
				System.out.println("Enter Bank ID:");
				int bank_id = sc.nextInt();
				if(bank_id > bankID){
					System.out.println("BANK DOES NOT EXIST\n");
					break;
				}
				sc.nextLine();
				list_of_clients[clientID] = new Client(type);
				if ("Employee".equals(type))
				{
					System.out.println("Name:");
					String nme = sc.nextLine();
					System.out.println("Rank:");
					String rank = sc.nextLine();
					Client.Employee employee = list_of_clients[clientID].createEmployee(nme, rank, list_of_banks[bank_id].bankName);
					if(employee.validate_rank(rank)){
						list_of_clients[clientID].printEmployee();
					}
					else{
						System.out.println("INVALID RANK\n");
						break;
					}
				}
				else
				{
					System.out.println("Individual or Organization?");
					String cat = sc.nextLine();
					System.out.println("Customer Name:");
					String nam = sc.nextLine();
					System.out.println("Email Address:");
					String ema = sc.nextLine();
					System.out.println("Account Number:");
					String act = sc.nextLine();
					System.out.println("Phone Number:");
					String phn = sc.nextLine();
					String ban = list_of_banks[bank_id].bankName;
					Client.Customer customer = list_of_clients[clientID].createCustomer(nam, ema, act, ban, phn);
					int num;
					if ("Organization".equals(cat))
					{
						System.out.println("TIN Number:");
						num = sc.nextInt();
					}
					else
					{
						System.out.println("BIN Number:");
						num = sc.nextInt();
					}
					customer.setTypeStatus(cat, num);
					list_of_clients[clientID].printCustomer();
				}
				list_of_accounts[clientID] = new Accounts(clientID);
				if(!type.equals("Employee")){
					list_of_accounts[clientID].printBalance();
				}
				System.out.println("Client registered!! ClientID is " + (clientID) + "\n\n\n");
				clientID += 1;
				break;
			case 4:
				System.out.println("Enter ClientID:");
				int id_num = sc.nextInt();
				sc.nextLine();
				if(list_of_clients[id_num].validate()){
					System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
					break;
				}
				System.out.println("Enter Account type: (Savings/Salary)");
				String acc_type = sc.nextLine();
				System.out.println("Enter Amount to Deposit:");
				double cash = sc.nextDouble();
				System.out.println("Enter time (in years):");
				double time = sc.nextDouble();
				if("Savings".equals(acc_type)){
					list_of_accounts[id_num].Savings_Account(cash, time);
				}
				else{
					list_of_accounts[id_num].Salary_Account(cash, time);
				}
				System.out.println("Deposit Recorded!\n\n");
				list_of_clients[id_num].printCustomer();
				list_of_accounts[id_num].printBalance();
				break;
			case 5:
				System.out.println("Send Money Options:\n1. Bkash\n2. Electronic Fund Transfer\n3. Bank Receipt Manual Transfer\n");
				int choice = sc.nextInt();
				switch(choice){
					case 1:
						System.out.println("Enter ClientID: ");
						id_num = sc.nextInt();
						if(list_of_clients[id_num].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						sc.nextLine();
						System.out.println("Enter Recepient Bkash Number: ");
						String receiver = sc.nextLine();
						System.out.println("Enter Amount of Money:");
						cash = sc.nextDouble();
						if(cash > list_of_accounts[id_num].balance){
							System.out.println("YOU DO NOT HAVE SUFFICIENT BALANCE");
							break;
						}
						list_of_accounts[id_num].deductMoney(cash);
						System.out.println("Transaction Complete! " + cash + " sent to " + receiver + "\n");
						list_of_clients[id_num].printCustomer();
						list_of_accounts[id_num].printBalance();
						break;
					case 2:
						System.out.println("Enter ClientID (Sender): ");
						int id_num_sender = sc.nextInt();
						if(list_of_clients[id_num_sender].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						System.out.println("Enter ClientID (Receiver): ");
						int id_num_reciever = sc.nextInt();
						System.out.println("Enter Amount of Money:");
						cash = sc.nextDouble();
						if(cash > list_of_accounts[id_num_sender].balance){
							System.out.println("YOU DO NOT HAVE SUFFICIENT BALANCE");
							break;
						}
						list_of_accounts[id_num_sender].deductMoney(cash);
						list_of_accounts[id_num_reciever].addMoney(cash);
						System.out.println("Transaction Complete! " + cash + " sent to " + list_of_clients[id_num_reciever].name + "\n");
						System.out.println("SENDER: ");
						list_of_clients[id_num_sender].printCustomer();
						list_of_accounts[id_num_sender].printBalance();
						System.out.println("RECEIVER: ");
						list_of_clients[id_num_reciever].printCustomer();
						list_of_accounts[id_num_reciever].printBalance();
						break;
					case 3:
						System.out.println("Enter ClientID: ");
						id_num = sc.nextInt();
						if(list_of_clients[id_num].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						sc.nextLine();
						System.out.println("Sign your name: ");
						name = sc.nextLine();
						if(!name.equals(list_of_clients[id_num].name))
						{
							System.out.println("SIGNATURE NOT RECOGNISED.");
							break;
						}
						System.out.println("Enter Date: ");
						String date = sc.nextLine();
						System.out.println("Enter Amount of Money: ");
						cash = sc.nextDouble();
						list_of_accounts[id_num].bankReceipt(date, cash, name);
						System.out.println("Transaction Complete!\n");
						list_of_clients[id_num].printCustomer();
						list_of_accounts[id_num].printBalance();
						break;
					default:
						System.out.println("INVALID\n\n");
						break;	
				}
				break;
			case 6:
				System.out.println("Cash-out Options:\n1. Bkash\n2. DirectCheque\n3. CreditCard\n");
				choice = sc.nextInt();
				switch(choice){
					case 1:
						System.out.println("Enter ClientID: ");
						id_num = sc.nextInt();
						if(list_of_clients[id_num].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						System.out.println("Enter Amount of Money:");
						cash = sc.nextDouble();
						list_of_accounts[id_num].deductMoney(cash);
						if(cash > list_of_accounts[id_num].balance){
							System.out.println("YOU DO NOT HAVE SUFFICIENT BALANCE");
							break;
						}
						System.out.println("Transaction Complete! " + cash + " sent to " + list_of_clients[id_num].phoneNumber + "\n");
						list_of_clients[id_num].printCustomer();
						list_of_accounts[id_num].printBalance();
						break;
					case 2:
						System.out.println("Enter ClientID: ");
						id_num = sc.nextInt();
						if(list_of_clients[id_num].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						System.out.println("Enter Amount of Money:");
						cash = sc.nextDouble();
						if(cash > list_of_accounts[id_num].balance){
							System.out.println("YOU DO NOT HAVE SUFFICIENT BALANCE");
							break;
						}
						list_of_accounts[id_num].cheque(cash, list_of_clients[id_num].name);
						System.out.println("Transaction Complete!\n");
						list_of_clients[id_num].printCustomer();
						list_of_accounts[id_num].printBalance();
						break;
					case 3:
						System.out.println("Enter ClientID: ");
						id_num = sc.nextInt();
						if(list_of_clients[id_num].validate()){
							System.out.println("YOUR ACCOUNT IS INELLIGIBLE");
							break;
						}
						System.out.println("Enter Amount of Money:");
						cash = sc.nextDouble();
						sc.nextLine();
						if(cash > list_of_accounts[id_num].balance){
							System.out.println("YOU DO NOT HAVE SUFFICIENT BALANCE");
							break;
						}
						System.out.println("Enter CreditCard Number:");
						String credit = sc.nextLine();
						list_of_accounts[id_num].creditcard(cash, credit);
						System.out.println("Transaction Complete!\n");
						list_of_clients[id_num].printCustomer();
						list_of_accounts[id_num].printBalance();
						break;
					default:
						System.out.println("INVALID\n\n");
						break;	
				}
				break;
			default:
				System.out.println("INVALID\n\n");
				break;
		}
		main_menu(list_of_banks, list_of_clients, list_of_accounts, bankID, clientID, sc);

	}




	//Main Class Driver Code
	public static void main(String[] args){
		Main obj = new Main();
		Scanner sc = new Scanner(System.in);
		Bank[] list_of_banks = new Bank[50];
		Client[] list_of_clients = new Client[100];
		Accounts[] list_of_accounts = new Accounts[100];
		int bankID = 0;
		int clientID = 0;
		//MENU SCREEN
		while(true){
			obj.main_menu(list_of_banks, list_of_clients, list_of_accounts, bankID, clientID, sc);
		}
	}
}