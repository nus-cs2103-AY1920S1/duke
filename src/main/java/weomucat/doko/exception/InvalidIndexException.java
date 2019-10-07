package weomucat.doko.exception;

/**
 * Thrown when an invalid task index was passed in to TaskList.
 */
public class InvalidIndexException extends DokoRuntimeException {

  public InvalidIndexException(String message) {
    super(message);
  }
}
