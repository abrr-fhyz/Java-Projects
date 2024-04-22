import java.util.HashMap;

public class Main{
	public static void main(String [] args) {
		HashMap<Integer, Account> accountList = new HashMap<>();
		HashMap<Integer, Data> depositTransactions = new HashMap<>();
		HashMap<Integer, Data> withdrawTransactions = new HashMap<>();
		GenerationThread accountGenerationThread = new GenerationThread(accountList);
		GenerationThread depositGenerationThread = new GenerationThread(accountList, depositTransactions, true);
		GenerationThread withdrawGenerationThread = new GenerationThread(accountList, withdrawTransactions, false);
		ProcessingThread depositProcessThread1 = new ProcessingThread(depositTransactions, true, 1000);
		ProcessingThread depositProcessThread2 = new ProcessingThread(depositTransactions, true, 800);
		ProcessingThread depositProcessThread3 = new ProcessingThread(depositTransactions, true, 750);
		ProcessingThread withdrawalProccessThread1 = new ProcessingThread(withdrawTransactions, false, 1000);
		ProcessingThread withdrawalProccessThread2 = new ProcessingThread(withdrawTransactions, false, 800);
		ProcessingThread withdrawalProccessThread3 = new ProcessingThread(withdrawTransactions, false, 750);
		accountGenerationThread.start();
		depositGenerationThread.start();
		withdrawGenerationThread.start();
		//wait 1 second for the first entry to be created
		try {
            Thread.sleep(1000);
       	} catch (InterruptedException e) {}
		depositProcessThread1.start();
		depositProcessThread2.start();
		depositProcessThread3.start();
		withdrawalProccessThread1.start();
		withdrawalProccessThread2.start();
		withdrawalProccessThread3.start();
	}
}