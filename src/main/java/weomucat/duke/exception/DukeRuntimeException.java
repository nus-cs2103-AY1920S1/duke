package weomucat.duke.exception;

/**
 * An runtime exception that occurred within Duke.
 */
public class DukeRuntimeException extends RuntimeException {

  /**
   * Creates a DukeRuntimeException.
   *
   * @param message error message to display
   */
  public DukeRuntimeException(String message) {
    super(message);
  }
}
