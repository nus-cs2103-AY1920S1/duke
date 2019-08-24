package duke.task;

public abstract class Task {
  private String name;
  private boolean done;

  Task(String name, boolean done) {
    this.name = name;
    this.done = done;
  }

  Task(String name) {
    this(name, false);
  }

  String getName() {
    return this.name;
  }

  boolean getDone() {
    return this.done;
  }

  public void setDone() {
    this.done = true;
    System.out.println("Nice! I've marked this task as done: ");
    System.out.println("  " + this.toString());
  }

  public abstract String storageString();

  @Override
  public String toString() {
    return ("[" + (this.done ? "✓" : "✗") + "]" + " " + this.name);
  }

  public static Task taskMaker(String[] arr) {
    String type = arr[0];
    boolean done = arr[1].equals("1");
    String name = arr[2];
    Task task = null;
    switch (type) {
    case "T":
      task = new ToDo(name, done);
      break;
    case "D":
      task = new Deadlines(name, arr[3], done);
      break;
    case "E":
      task = new Event(name, arr[3], done);
      break;
    default:
      break;
    }
    return task;
  }
}
