package weomucat.doko.exception;

/**
 * Thrown when an invalid parameter was passed in from user input.
 */
public class InvalidParameterException extends DokoException {

  public InvalidParameterException(String message) {
    super(message);
  }
}
