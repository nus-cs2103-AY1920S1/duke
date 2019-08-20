
public class Event extends Task {
  private String date;

  public Event(String n, int index, String date) {
    super(n, index);
    this.date = date;
  }
  @Override
  public String toString() {
    String result = "[E][";
    result = this.completed ? result + "✓]" : result + "✗]";
    result += " " + this.name;
    result += "(at:" + this.date + ")";
    return result;
  }
}
