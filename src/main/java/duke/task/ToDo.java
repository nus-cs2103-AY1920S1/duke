package duke.task;

public class ToDo extends Task {
  ToDo(String name, boolean done) {
    super(name, done);
  }

  public ToDo(String name) {
    this(name, false);
  }

  @Override
  public String storageString() {
    return "T," + (super.getDone() ? "1," : "0,") + super.getName();
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (obj instanceof ToDo) {
      ToDo other = (ToDo) obj;
      return this.getDone() == other.getDone() && this.getName().equals(other.getName());
    }
    return false;
  }
}
