class Task {
  private String event;
  private boolean completed;

  public Task(String event) {
    this.event = event;
    this.completed = false;
  }

  public String getEvent() {
    return this.event;
  }

  public boolean isComplete() {
    return this.completed;
  }

  public void complete() {
    this.completed = true;
  }

  @Override
  public String toString() {
    return this.event;
  }
}