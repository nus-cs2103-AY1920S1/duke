package weomucat.doko.exception;

/**
 * Thrown when the command the user entered is unknown.
 */
public class UnknownCommandException extends DokoException {

  public UnknownCommandException() {
    super("I'm sorry, but I don't know that command :-(");
  }
}
