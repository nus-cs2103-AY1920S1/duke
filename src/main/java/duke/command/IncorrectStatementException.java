package duke.command;

public class IncorrectStatementException extends UnknownCommandException {
    private String actualStatement;
    private String givenStatement;
    
    /**
     * Constructor.
     * @param givenStatement - Given message input by user
     * @param actualStatement - Expected ouput
     */
    public IncorrectStatementException(String givenStatement, String actualStatement) {
        super(actualStatement);
        this.actualStatement = actualStatement;
        this.givenStatement = givenStatement;
    }

    @Override
    public String getMessage() {
        return "Sorry that statement is invalid. Try using \"/" + this.actualStatement
                + "\" instead of \"/" + this.givenStatement + "\".";
    }
}
