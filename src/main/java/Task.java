
public class Task {
  protected boolean completed;
  protected int index;
  protected String name;

  public Task(String n, int index) {
    this.name = n;
    this.index = index;
    completed = false;
  }

  public void complete() {
    this.completed = true;
  }
}
