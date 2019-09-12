package weomucat.duke.task;

import static weomucat.duke.Duke.LOCALE;

import java.time.Duration;
import java.util.ArrayList;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidIndexException;
import weomucat.duke.exception.StorageException;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.task.listener.ModifyTaskListener;
import weomucat.duke.task.listener.SaveTaskListListener;
import weomucat.duke.task.listener.TaskListSizeListener;
import weomucat.duke.ui.Message;

/**
 * A TaskList manages the addition, deletion, marking as done and listing of tasks.
 */
public class TaskList implements AddTaskCommandListener, DeleteTaskCommandListener,
    DoneTaskCommandListener, EventAtCommandListener, FindTaskCommandListener,
    ListTaskCommandListener, SnoozeTaskCommandListener {

  private TaskListTasks tasks;
  private ArrayList<ListTaskListener> listTaskListeners;
  private ArrayList<ModifyTaskListener> modifyTaskListeners;
  private ArrayList<TaskListSizeListener> taskListSizeListeners;
  private ArrayList<SaveTaskListListener> saveTaskListListeners;

  public TaskList() {
    this.tasks = new TaskListTasks();
    init();
  }

  /**
   * Initialize a TaskList from an existing TaskListTasks.
   *
   * @param tasks an existing list of tasks
   */
  public TaskList(TaskListTasks tasks) {
    assert tasks != null;

    this.tasks = tasks;
    init();
  }

  private void init() {
    this.listTaskListeners = new ArrayList<>();
    this.modifyTaskListeners = new ArrayList<>();
    this.taskListSizeListeners = new ArrayList<>();
    this.saveTaskListListeners = new ArrayList<>();
  }

  /**
   * Adds a ListTaskListener.
   * When listTask is called, this listener will be notified.
   *
   * @param listener listTask listener
   */
  public void newListTaskListener(ListTaskListener listener) {
    this.listTaskListeners.add(listener);
  }

  /**
   * Adds a ModifyTaskListener.
   * When any task is modified, this listener will be notified.
   *
   * @param listener modifyTask listener
   */
  public void newModifyTaskListener(ModifyTaskListener listener) {
    this.modifyTaskListeners.add(listener);
  }

  /**
   * Adds a TaskListSizeListener.
   * When the size of the task list changes, this listener will be notified.
   *
   * @param listener taskListSize listener
   */
  public void newTaskListSizeListener(TaskListSizeListener listener) {
    this.taskListSizeListeners.add(listener);
  }

  /**
   * Adds a SaveTaskListListener.
   * Whenever the task list needs to be saved, this listener will be notified.
   *
   * @param listener saveTaskList listener
   */
  public void newSaveTaskListListener(SaveTaskListListener listener) {
    this.saveTaskListListeners.add(listener);
  }

  /**
   * Adds a task to the task list.
   *
   * @param task the task to add
   */
  void addTask(Task task) throws StorageException {
    // Add task to Tasks
    this.tasks.add(task);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message("Got it. I've added this task:"), task);

    // Update TaskListSizeListeners
    for (TaskListSizeListener listener : this.taskListSizeListeners) {
      listener.taskListSizeUpdate(this.tasks.size());
    }
  }

  /**
   * Deletes a task from the task list.
   *
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  void deleteTask(int i) throws DukeException {
    Task task = getTask(i);

    // Remove task
    this.tasks.remove(i);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message("Noted. I've removed this task:"), task);

    // Update TaskListSizeListeners
    for (TaskListSizeListener listener : this.taskListSizeListeners) {
      listener.taskListSizeUpdate(this.tasks.size());
    }
  }

  /**
   * Marks a task as done in the task list.
   *
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  void doneTask(int i) throws DukeException {
    Task task = getTask(i);

    // Set task to done
    task.setDone(true);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message("Nice! I've marked this task as done:"), task);
  }

  /**
   * Confirms to pick a tentative schedule in an event.
   *
   * @param taskIndex the index of the event in the task list
   * @param atIndex   the index of the schedule in the tentative schedule list
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  private void eventAt(int taskIndex, int atIndex) throws DukeException {
    Task task = getTask(taskIndex);

    if (!(task instanceof EventTask)) {
      throw new InvalidIndexException("The task selected is not an event.");
    }

    EventTask event = (EventTask) task;
    event.setAt(atIndex);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message("Got it. I've set the schedule for this event:"), task);
  }

  /**
   * Searches for a keyword in all tasks.
   * Notify listeners tasks which description matches the keyword.
   */
  void findTask(String keyword) {
    TaskListTasks result = new TaskListTasks();

    // Case insensitive search
    // Use a single locale to prevent issues with case conversion.
    keyword = keyword.toLowerCase(LOCALE);
    for (Task task : this.tasks) {
      String description = task.getDescription().toLowerCase(LOCALE);
      if (description.contains(keyword)) {
        result.add(task);
      }
    }

    // Update ListTaskListeners
    for (ListTaskListener listener : this.listTaskListeners) {
      listener.listTaskUpdate(new Message("Here are the matching tasks in your list:"), result);
    }
  }

  /**
   * Notify listeners to list tasks.
   */
  void listTask() {
    // Update ListTaskListeners
    for (ListTaskListener listener : this.listTaskListeners) {
      listener.listTaskUpdate(new Message("Here are the tasks in your list:"), this.tasks);
    }
  }

  /**
   * Snoozes a task by a certain duration.
   *
   * @param taskIndex the index of the task in the task list
   * @param duration  the duration to snooze a task
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  private void snoozeTask(int taskIndex, Duration duration) throws DukeException {
    Task task = getTask(taskIndex);

    if (!(task instanceof SnoozableTask)) {
      throw new InvalidIndexException("The task selected cannot be snoozed!");
    }

    SnoozableTask snoozableTask = (SnoozableTask) task;
    snoozableTask.snooze(duration);

    // Update ModifyTaskListeners
    modifyTaskUpdate(new Message("Got it. I've snoozed this task:"), task);
  }

  private Task getTask(int i) throws InvalidIndexException {
    try {
      // Get task from tasks
      return this.tasks.get(i);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException("That is not a valid index of a task.");
    }
  }

  private void modifyTaskUpdate(Message message, Task task) throws StorageException {
    for (ModifyTaskListener listener : this.modifyTaskListeners) {
      listener.modifyTaskUpdate(message, task);
    }

    for (SaveTaskListListener listener : this.saveTaskListListeners) {
      listener.saveTaskListUpdate(this.tasks);
    }
  }

  @Override
  public void addTaskCommandUpdate(Task task) throws DukeException {
    addTask(task);
  }

  @Override
  public void deleteTaskCommandUpdate(int i) throws DukeException {
    deleteTask(i);
  }

  @Override
  public void doneTaskCommandUpdate(int i) throws DukeException {
    doneTask(i);
  }

  @Override
  public void eventAtCommandUpdate(int taskIndex, int atIndex) throws DukeException {
    eventAt(taskIndex, atIndex);
  }

  @Override
  public void findTaskCommandUpdate(String keyword) {
    findTask(keyword);
  }

  @Override
  public void listTaskCommandUpdate() {
    listTask();
  }

  @Override
  public void snoozeTaskCommandUpdate(int taskIndex, Duration duration) throws DukeException {
    snoozeTask(taskIndex, duration);
  }
}
