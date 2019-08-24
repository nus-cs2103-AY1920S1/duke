package jermi.exception;

public abstract class JermiException extends Exception {

    JermiException(String message) {
        super("\u2639 OOPS! " + message);
    }
}
