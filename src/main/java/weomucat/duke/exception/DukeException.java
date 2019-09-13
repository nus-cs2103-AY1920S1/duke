package weomucat.duke.exception;

/**
 * An exception that occurred within Duke.
 */
public class DukeException extends Exception {

  /**
   * Creates a DukeException.
   *
   * @param message error message to display
   */
  DukeException(String message) {
    super(message);
  }
}
