package weomucat.doko.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import weomucat.doko.exception.InvalidIndexException;

/**
 * A TaskList contains Tasks.
 * Stores the state of Tasks and can be saved.
 */
public class TaskList implements Serializable {

  private ArrayList<Task> tasks;
  private HashSet<RecurringTask> recurringTasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
    this.recurringTasks = new HashSet<>();
  }

  /**
   * Adds a Task to the TaskList.
   *
   * @param task the task to add
   */
  public void add(Task task) {
    this.tasks.add(task);
    if (task instanceof RecurringTask) {
      RecurringTask recurringTask = (RecurringTask) task;
      if (recurringTask.getNextRecurrence() != null) {
        this.recurringTasks.add(recurringTask);
      }
    }
  }

  /**
   * Removes a Task from the TaskList.
   *
   * @param i index of the task in the tasklist
   */
  void remove(int i) {
    Task task = this.tasks.remove(i);
    if (task instanceof RecurringTask) {
      this.recurringTasks.remove(task);
    }
  }

  /**
   * Gets a Task from the TaskList.
   *
   * @param i index of the task in the tasklist
   * @return the Task
   */
  public Task get(int i) {
    try {
      // Get task from tasks
      return this.tasks.get(i);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException("That is not a valid index of a task.");
    }
  }

  void updateRecurringTasks() {
    for (RecurringTask task : new HashSet<>(this.recurringTasks)) {
      RecurringTask current = task;
      while (current.isDone() || current.isOverDue()) {
        RecurringTask next = current.getNextRecurrence();
        current.removeRecurrence();
        this.tasks.add(next);

        current = next;
      }

      this.recurringTasks.remove(task);
      this.recurringTasks.add(current);
    }
  }

  public int size() {
    return this.tasks.size();
  }
}
