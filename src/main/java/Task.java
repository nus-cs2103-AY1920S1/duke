class Task {
  private String task;
  private boolean completed;

  public Task(String task) {
    this.task = task;
    this.completed = false;
  }

  public String getTask() {
    return this.task;
  }

  public boolean isComplete() {
    return this.completed;
  }

  public void complete() {
    this.completed = true;
  }

  private String tickOrCross() {
    if(this.completed) {
      return TaskManager.TICK;
    } else {
      return TaskManager.CROSS;
    }
  }

  @Override
  public String toString() {
    return "[" + tickOrCross() + "] " + this.task;
  }
}