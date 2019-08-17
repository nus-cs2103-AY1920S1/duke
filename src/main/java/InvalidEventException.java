public class InvalidEventException extends Exception {

    public String message;

    public InvalidEventException(String message) {
        this.message = message;
    }

    public String toString() {
        return "☹ OOPS!!! " + message;
    }

}