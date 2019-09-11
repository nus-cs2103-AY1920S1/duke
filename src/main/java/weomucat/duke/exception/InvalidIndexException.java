package weomucat.duke.exception;

/**
 * Thrown when an invalid task index was passed in to TaskList.
 */
public class InvalidIndexException extends DukeException {

  public InvalidIndexException(String message) {
    super(message);
  }
}
