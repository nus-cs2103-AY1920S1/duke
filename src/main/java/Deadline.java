
public class Deadline extends Task {
  private String date;

  public Deadline(String n, int index, String date) {
    super(n, index);
    this.date = date;
  }

  public Deadline(String n, int index, String date, boolean completed) {
    super(n, index, completed);
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  @Override
  public String toString() {
    String result = "[D][";
    result = this.completed ? result + "\u2713" + "]" : result + "\u2718" + "]";
    result += " " + this.name;
    result += " (by: " + this.date + ")";
    return result;
  }
}
