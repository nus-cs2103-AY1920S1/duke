public class InvalidFormatException extends Exception {
    public String message;
    public InvalidFormatException() {}
    public InvalidFormatException(String s) {
        message = s;
    }
}
