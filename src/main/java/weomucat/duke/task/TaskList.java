package weomucat.duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import weomucat.duke.exception.InvalidIndexException;

/**
 * A TaskList contains Tasks.
 * Stores the state of Tasks and can be saved.
 */
public class TaskList {

  private ArrayList<Task> tasks;
  private HashMap<UUID, DeadlineTask> recurringTasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
    this.recurringTasks = new HashMap<>();
  }

  public void add(Task task) {
    this.tasks.add(task);

    if (task instanceof DeadlineTask) {
      DeadlineTask t = (DeadlineTask) task;
      this.recurringTasks.put(t.getUuid(), t);
    }
  }

  public void remove(int i) {
    this.tasks.remove(i);
  }

  public Task get(int i) {
    return this.tasks.get(i);
  }

  public Task getUnsafe(int i) throws InvalidIndexException {
    try {
      // Get task from tasks
      return this.tasks.get(i);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException("That is not a valid index of a task.");
    }
  }

  public void populateRecurringTasks() {
    for (Map.Entry<UUID, DeadlineTask> entry : this.recurringTasks.entrySet()) {
      DeadlineTask next = entry.getValue();
      while (!next.isAfter(LocalDateTime.now())) {
        next = next.getNextRecurrence();
        tasks.add(next);
      }
      this.recurringTasks.put(entry.getKey(), next);
    }
  }

  public int size() {
    return this.tasks.size();
  }
}
