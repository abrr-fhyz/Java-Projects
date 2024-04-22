interface Validate {
    static boolean deposit(Data data, int key) {
        boolean output = false;
        try {
            output = depositAmount(data.getTransactionAmount(), data.getAccount().getTransactionLimit(), key);
        } catch (HandleException e) {
            System.err.println(e.getMessage());
        }
        return output;
    }

    static boolean withdraw(Data data, int key) {
        boolean output = false;
        try {
            output = withdrawAmount(data.getTransactionAmount(), data.getAccount(), key);
        } catch (HandleException e) {
            System.err.println(e.getMessage());
        }
        return output;
    }

    private static boolean depositAmount(int transactionAmount, int transactionLimit, int key) throws HandleException {
        if (transactionAmount > transactionLimit) {
            throw new HandleException("dep", key);
        }
        return true;
    }

    private static boolean withdrawAmount(int transactionAmount, Account account, int key) throws HandleException {
        if (transactionAmount > account.getTransactionLimit()) {
            throw new HandleException("with", key);
        }
        if (transactionAmount > account.getBalance()) {
            throw new HandleException("~", key);
        }
        return true;
    }
}
