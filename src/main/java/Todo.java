
public class Todo extends Task {
  public Todo(String name, int index) {
      super(name, index);
  }

  public Todo (String name, int index, boolean completed) {
      super(name, index, completed);
  }
  @Override
  public String toString() {
      String result = "[T][";
      result = this.completed ? result + "\u2713" + "]" : result + "\u2718" + "]";
      result += " " + this.name;
      return result;
  }
}
