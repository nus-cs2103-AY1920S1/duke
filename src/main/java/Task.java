public class Task {

  private String description;
  private boolean isDone;

  // Constructor
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void setAsDone() {
    this.isDone = true;
  }

  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

  public String getStatusIcon() {
    // X to represent done, nothing to represent not done
    return (isDone ? "X" : " ");
  }

  public boolean getBoolean() {
    return isDone;
  }
}