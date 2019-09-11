package weomucat.duke.task.listener;

public class StubTaskListSizeListener implements TaskListSizeListener {

  private int size;

  @Override
  public void taskListSizeUpdate(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }
}
