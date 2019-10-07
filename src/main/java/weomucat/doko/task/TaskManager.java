package weomucat.doko.task;

import static weomucat.doko.Doko.LOCALE;

import java.util.function.Predicate;
import weomucat.doko.HeterogeneousContainers;
import weomucat.doko.Pair;
import weomucat.doko.command.listener.AddTaskCommandListener;
import weomucat.doko.command.listener.DeleteTaskCommandListener;
import weomucat.doko.command.listener.DoneTaskCommandListener;
import weomucat.doko.command.listener.EventAtCommandListener;
import weomucat.doko.command.listener.FindTaskCommandListener;
import weomucat.doko.command.listener.ListTaskCommandListener;
import weomucat.doko.command.listener.LoadTasksCommandListener;
import weomucat.doko.command.listener.SnoozeTaskCommandListener;
import weomucat.doko.date.Interval;
import weomucat.doko.exception.DokoException;
import weomucat.doko.exception.InvalidIndexException;
import weomucat.doko.exception.StorageException;
import weomucat.doko.task.listener.ListTaskListener;
import weomucat.doko.task.listener.ModifyTaskListener;
import weomucat.doko.task.listener.TaskListSizeListener;
import weomucat.doko.task.listener.TaskListStorageListener;
import weomucat.doko.task.listener.TaskListener;
import weomucat.doko.ui.message.Message;

/**
 * A TaskManager manages tasks.
 * Example features:
 * - addition
 * - deletion
 * - marking as done
 * - listing of tasks
 */
public class TaskManager implements AddTaskCommandListener, DeleteTaskCommandListener,
    DoneTaskCommandListener, EventAtCommandListener, FindTaskCommandListener,
    ListTaskCommandListener, LoadTasksCommandListener, SnoozeTaskCommandListener {

  private TaskList tasks;
  private HeterogeneousContainers<TaskListener> taskListeners;

  /**
   * Creates a TaskManager.
   */
  public TaskManager() {
    this.tasks = new TaskList();
    this.taskListeners = new HeterogeneousContainers<>();
  }

  /**
   * Adds a TaskListener.
   * During the relevant task event, this listener will be notified.
   *
   * @param listener task listener
   */
  public <T extends TaskListener> void addListener(Class<T> c, T listener) {
    this.taskListeners.add(c, listener);
  }

  /**
   * Adds a task to the task list.
   *
   * @param task the task to add
   */
  void addTask(Task task) throws StorageException {
    // Add task to Tasks
    this.tasks.add(task);

    // Update listeners
    modifyTaskUpdate(new Message().addBody("Got it. I've added this task:"), task);
    taskListSizeUpdate();
  }

  /**
   * Deletes a task from the task list.
   *
   * @throws DokoException If the index is invalid or any listeners throw a DokoException.
   */
  void deleteTask(int i) throws DokoException {
    Task task = this.tasks.get(i);

    // Remove task
    this.tasks.remove(i);

    // Update listeners
    modifyTaskUpdate(new Message().addBody("Noted. I've removed this task:"), task);
    taskListSizeUpdate();
  }

  /**
   * Marks a task as done in the task list.
   *
   * @throws DokoException If the index is invalid or any listeners throw a DokoException.
   */
  void doneTask(int i) throws DokoException {
    Task task = this.tasks.get(i);

    // Set task to done
    task.setDone(true);

    this.tasks.updateRecurringTasks();

    // Update listeners
    modifyTaskUpdate(new Message().addBody("Nice! I've marked this task as done:"), task);
  }

  /**
   * Confirms to pick a tentative schedule in an event.
   *
   * @param taskIndex the index of the event in the task list
   * @param atIndex   the index of the schedule in the tentative schedule list
   * @throws DokoException If the index is invalid or any listeners throw a DokoException.
   */
  private void eventAt(int taskIndex, int atIndex) throws DokoException {
    Task task = this.tasks.get(taskIndex);

    if (!(task instanceof EventTask)) {
      throw new InvalidIndexException("The task selected is not an event.");
    }

    EventTask event = (EventTask) task;
    event.setAt(atIndex);

    // Update listeners
    modifyTaskUpdate(new Message().addBody("Got it. I've set the schedule for this event:"), task);
  }

  /**
   * Searches for a keyword in all tasks.
   * Notify listeners tasks which description matches the keyword.
   */
  void findTask(String keyword) {
    // Case insensitive search
    // Use a single locale to prevent issues with case conversion.
    String key = keyword.toLowerCase(LOCALE);
    listFilteredTasks(task -> {
      String description = task.getDescription().toLowerCase(LOCALE);
      return description.contains(key);
    }, new Message().addBody("Here are the matching tasks in your list:"));
  }

  /**
   * Notify listeners to list ongoing tasks.
   * Ongoing: Tasks that are not done & is not due yet.
   */
  void listOngoingTasks() {
    this.tasks.updateRecurringTasks();
    listFilteredTasks(task -> !task.isDone() && !task.isOverDue(),
        new Message().addBody("Here are your ongoing tasks:"));
  }

  /**
   * Notify listeners to list all tasks.
   */
  private void listAllTasks() {
    this.tasks.updateRecurringTasks();
    listFilteredTasks(task -> !task.isDone() && task.isOverDue(),
        new Message().addBody("Here are your overdue tasks:"));
    listFilteredTasks(task -> !task.isDone() && !task.isOverDue(),
        new Message().addBody("Here are your ongoing tasks:"));
    listFilteredTasks(Task::isDone, new Message().addBody("Here are your done tasks:"));
  }

  /**
   * Snoozes a task by a certain duration.
   *
   * @param taskIndex the index of the task in the task list
   * @param interval  the duration to snooze a task
   * @throws DokoException If the index is invalid or any listeners throw a DokoException.
   */
  private void snoozeTask(int taskIndex, Interval interval) throws DokoException {
    Task task = this.tasks.get(taskIndex);

    if (!(task instanceof SnoozableTask)) {
      throw new InvalidIndexException("The task selected cannot be snoozed!");
    }

    SnoozableTask snoozableTask = (SnoozableTask) task;
    snoozableTask.snooze(interval);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message().addBody("Got it. I've snoozed this task:"), task);
  }

  private NumberedTaskList filterTasks(Predicate<Task> predicate) {
    NumberedTaskList result = new NumberedTaskList();
    for (int i = 0; i < this.tasks.size(); i++) {
      Task task = this.tasks.get(i);
      if (predicate.test(task)) {
        result.add(new Pair<>(i + 1, task));
      }
    }
    return result;
  }

  private void listFilteredTasks(Predicate<Task> predicate, Message message) {
    NumberedTaskList filtered = filterTasks(predicate);

    // Update ListTaskListeners
    for (ListTaskListener storage : this.taskListeners.getAll(ListTaskListener.class)) {
      storage.listTaskUpdate(message, filtered);
    }
  }

  private void modifyTaskUpdate(Message message, Task task) throws StorageException {
    for (ModifyTaskListener storage : this.taskListeners.getAll(ModifyTaskListener.class)) {
      storage.modifyTaskUpdate(message, task);
    }

    for (TaskListStorageListener storage : this.taskListeners
        .getAll(TaskListStorageListener.class)) {
      storage.saveTaskListUpdate(this.tasks);
    }
  }

  private void taskListSizeUpdate() {
    for (TaskListSizeListener storage : this.taskListeners.getAll(TaskListSizeListener.class)) {
      storage.taskListSizeUpdate(this.tasks.size());
    }
  }

  @Override
  public void addTaskCommandUpdate(Task task) throws DokoException {
    addTask(task);
  }

  @Override
  public void deleteTaskCommandUpdate(int i) throws DokoException {
    deleteTask(i);
  }

  @Override
  public void doneTaskCommandUpdate(int i) throws DokoException {
    doneTask(i);
  }

  @Override
  public void eventAtCommandUpdate(int taskIndex, int atIndex) throws DokoException {
    eventAt(taskIndex, atIndex);
  }

  @Override
  public void findTaskCommandUpdate(String keyword) {
    findTask(keyword);
  }

  @Override
  public void listTaskCommandUpdate(boolean all) {
    if (all) {
      listAllTasks();
    } else {
      listOngoingTasks();
    }
  }

  @Override
  public void loadTasksCommandUpdate() throws StorageException {
    for (TaskListStorageListener storage : this.taskListeners
        .getAll(TaskListStorageListener.class)) {
      this.tasks = storage.loadTaskListUpdate();
    }
  }

  @Override
  public void snoozeTaskCommandUpdate(int taskIndex, Interval interval) throws DokoException {
    snoozeTask(taskIndex, interval);
  }
}
