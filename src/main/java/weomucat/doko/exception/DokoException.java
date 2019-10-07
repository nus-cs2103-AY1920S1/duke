package weomucat.doko.exception;

/**
 * An exception that occurred within Doko.
 */
public class DokoException extends Exception {

  /**
   * Creates a DokoException.
   *
   * @param message error message to display
   */
  DokoException(String message) {
    super(message);
  }
}
