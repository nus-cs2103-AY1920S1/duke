package weomucat.doko.exception;

/**
 * An runtime exception that occurred within Doko.
 */
public class DokoRuntimeException extends RuntimeException {

  /**
   * Creates a DokoRuntimeException.
   *
   * @param message error message to display
   */
  public DokoRuntimeException(String message) {
    super(message);
  }
}
