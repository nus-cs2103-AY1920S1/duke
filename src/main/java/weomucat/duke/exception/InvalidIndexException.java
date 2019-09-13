package weomucat.duke.exception;

/**
 * Thrown when an invalid task index was passed in to TaskList.
 */
public class InvalidIndexException extends DukeRuntimeException {

  public InvalidIndexException(String message) {
    super(message);
  }
}
