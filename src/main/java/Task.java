
public class Task {
  private boolean completed;
  private int index;
  private String name;

  public Task(String n, int index) {
    this.name = n;
    this.index = index;
    completed = false;
  }

  public void complete() {
    this.completed = true;
  }

  @Override
  public String toString() {
    String result = "["; 
    result = this.completed ? result + "✓]" : result + "✗]";
    result += " " + this.name;
    return result;
  }
}
