package weomucat.duke.exception;

/**
 * Thrown when the command the user entered is unknown.
 */
public class UnknownCommandException extends DukeException {

  public UnknownCommandException() {
    super("I'm sorry, but I don't know that command :-(");
  }
}
