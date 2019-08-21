public class DukeException extends RuntimeException{
    String message;
    public DukeException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String toString() {
        String output = "    ------------------------------------------------------------\n"
                        + "    \uD83D\uDE41 OOPS!! " + message + " \uD83D\uDE41 \n"
                        + "    ------------------------------------------------------------";
        return output;
    }
}
