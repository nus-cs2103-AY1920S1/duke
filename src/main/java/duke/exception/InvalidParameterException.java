package duke.exception;

/**
 * This is a exception that occur when the user enters an invalid parameter.
 */
public class InvalidParameterException extends DukeException {

    /**
     * This is the parameter for each Command that will be executed.
     */
    private String parameter;

    /**
     * Constructs a new invalid parameter exception where the parameter is <code>null</code>.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * Constructs a new invalid parameter exception with a specified parameter entered by the user.
     * @param parameter the parameter of the command to be executed.
     */
    public InvalidParameterException(String parameter) {
        super();
        this.parameter = parameter;
    }

    /**
     * Gets the invalid parameter that is entered by the user.
     * @return the invalid parameter
     */
    public String getInvalidParameter() {
        return parameter;
    }

}
