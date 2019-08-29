package weomucat.duke.exception;

/**
 * Thrown when an invalid parameter was passed in from user input.
 */
public class InvalidParameterException extends DukeException {

  public InvalidParameterException(String message) {
    super(message);
  }
}
