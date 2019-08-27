/**
 * Represents a ToDo Task. A ToDo task is represented by a description.
 */
public class ToDo extends Task {

  protected String description;

  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T] " + super.toString();
  }
}