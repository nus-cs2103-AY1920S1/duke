public class InvalidParameterException extends DukeException {
    String parameter;

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String parameter) {
        super();
        this.parameter = parameter;
    }

    public String getInvalidParameter() {
        return parameter;
    }
}
