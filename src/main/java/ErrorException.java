/**
 * @throws ErrorException if the default path is invalid
 */
public class ErrorException extends Exception {
    public ErrorException(String msg) {
        super(msg);
    }
}