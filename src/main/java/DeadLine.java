public class DeadLine extends Task {

  protected String by;

  public DeadLine(String description, String by) {
    super(description);
    this.by = by;
  }

  public String getDeadLine() {
    return this.by;
  }

  @Override
  public String toString() {
    return "[D] " + super.toString() + " (by:" + by + ")";
  }
}