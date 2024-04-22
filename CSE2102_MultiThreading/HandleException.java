class HandleException extends Exception {

    @Override
    public String getMessage(){
        return "----\t\tEXCEPTION:" + super.getMessage();
    }

    public HandleException(String type, int key) {
        super(classifyError(type, key));
    }

    private static String classifyError(String type, int key) {
        if (type.equals("dep")) {
            return "Maximum DepositTransactionLimit Violated\t --> DepositTransaction " + key;
        }
        if (type.equals("with")) {
            return "Maximum WithdrawTransactionLimit Violated\t --> WithdrawTransaction " + key;
        }
        return "Insufficient Balance for Transaction   \t --> WithdrawTransaction " + key;
    }
}
