
public class Task {
  protected boolean completed;
  protected int index;
  protected String name;

  public Task(String n, int index) {
      this.name = n;
      this.index = index;
      completed = false;
  }

  public Task(String n, int index, boolean completed) {
      this.name = n;
      this.index = index;
      this.completed = completed;
  }

  public boolean isCompleted() { return this.completed; }

  public String getName() { return this.name; }

  public void complete() {
    this.completed = true;
  }
}
