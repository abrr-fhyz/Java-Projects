class ExceptionHandling extends Exception {

    @Override
    public String getMessage(){
        return "---\tERR:" + super.getMessage();
    }

    public ExceptionHandling(String errorCat, int errorType) {
        super(getExceptionCategory(errorCat, errorType));
    }

    public ExceptionHandling(String stuff){
        super("NullPointerException: No such Person exists\n");
    }

    private static String getExceptionCategory(String errorCat, int errorType){
        switch(errorCat){
            case "name": 
                return getNameException(errorType);
            case "email": 
                return getEmailException(errorType);
            case "iden":
                return getIdentityException(errorType);
            case "birthday":
                return getBirthdayException(errorType);
            case "address":
                return getAddressException(errorType);
            default:
                return "Unknown Exception Category";
        }
    }

    private static String getNameException(int errorType) {
        switch(errorType) {
            case 1:
                return "FirstNameAbsence";
            case 2:
                return "LastNameAbsence";
            case 3:
                return "NoNamePresent";
            default:
                return "Unknown Name Exception";
        }
    }

    private static String getEmailException(int errorType) {
        switch(errorType) {
            case 1:
                return "BlankEmailField";
            case 2:
                return "AbsenceOfGmailSuffix";
            case 3:
                return "NotProperlyFormattedEmailPrefix";
            default:
                return "Unknown Email Format Exception";
        }
    }

    private static String getIdentityException(int errorType) {
        switch(errorType) {
            case 1:
                return "BlankNIDPassportField";
            case 2:
                return "UnexpectedNIDFormat";
            case 3:
                return "UnexpectedPassportFormat";
            default:
                return "Unknown NID/Passport Format Exception";
        }
    }

    private static String getBirthdayException(int errorType) {
        switch(errorType) {
            case 1:
                return "WrongBirthdateFormat";
            case 2:
                return "BirthdateBlank";
            default:
                return "Unknown Birthday Format Exception";
        }
    }

    private static String getAddressException(int errorType) {
        switch(errorType) {
            case 1:
                return "IllegalSymbolInAddressField1";
            case 2:
                return "IllegalSymbolInAddressField2";
            case 3:
                return "InvalidAdministrativeDivision";
            default:
                return "Unknown Address Exception";
        }
    }
}
