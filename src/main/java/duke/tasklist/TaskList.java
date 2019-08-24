package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
  private List<Task> list;

  public TaskList(List<Task> list) {
    this.list = list;
  }

  public TaskList() {
    this(new ArrayList<>());
  }

  public void add(Task t) {
    list.add(t);
  }

  public boolean isEmpty() {
    return this.list.isEmpty();
  }

  public Task remove(int index) {
    return this.list.remove(index - 1);
  }

  public int size() {
    return this.list.size();
  }

  public Task get(int index) {
    return this.list.get(index - 1);
  }

  public String printAllTasks() {
    if (this.size() != 0) {
      StringBuilder res = new StringBuilder();
      int count = 1;
      for (Task task : list) {
        res.append("").append(count).append(".").append(task).append("\n");
        count++;
      }
      return res.toString().trim();
    } else {
      return "There are no tasks on your list!";
    }
  }
}
