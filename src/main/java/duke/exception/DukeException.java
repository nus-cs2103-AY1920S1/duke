package duke.exception;

/**
 * DukeException class represents errors specific to Duke.
 *
 * @author scwaterbear
 */
public class DukeException extends Exception {

  private String errorDescription;

  /**
   * Class constructor.
   *
   * @param errorDescription description of the error.
   */
  public DukeException(String errorDescription) {
    super(errorDescription);
    this.errorDescription = errorDescription;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getMessage() {
    return errorDescription;
  }
}
