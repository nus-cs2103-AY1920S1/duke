
public class Todo extends Task {
  public Todo(String n, int index) {
    super(n, index);
  }
  @Override
  public String toString() {
    String result = "[T][";
    result = this.completed ? result + "✓]" : result + "✗]";
    result += " " + this.name;
    return result;
  }
}
