public class IncorrectStatementException extends InvalidCommandError {
    private String actualStatement;
    private String givenStatement;
    public IncorrectStatementException(String givenStatement, String actualStatement) {
        super(actualStatement);
        this.actualStatement = actualStatement;
        this.givenStatement = givenStatement;
    }

    @Override
    public String getMessage() {
        return "Sorry that statement is invalid. Try using \"/" + this.actualStatement + "\" instead of \"/"
                + this.givenStatement + "\".";
    }
}
