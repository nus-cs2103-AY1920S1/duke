public class DukeException extends Exception {
    String errorMessage;

    /**
     * Custom Exception class
     * @param errorMessage customized error messages for each error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }


}
