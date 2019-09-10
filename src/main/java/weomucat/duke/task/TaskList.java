package weomucat.duke.task;

import static weomucat.duke.Duke.LOCALE;

import java.util.ArrayList;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidTaskIndexException;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.FindTaskListener;
import weomucat.duke.task.listener.ListTaskListener;

/**
 * A TaskList manages the addition, deletion, marking as done and listing of tasks.
 */
public class TaskList implements AddTaskCommandListener, DeleteTaskCommandListener,
    DoneTaskCommandListener, FindTaskCommandListener, ListTaskCommandListener {

  private TaskListTasks tasks;
  private ArrayList<AddTaskListener> addTaskListeners;
  private ArrayList<DeleteTaskListener> deleteTaskListeners;
  private ArrayList<DoneTaskListener> doneTaskListeners;
  private ArrayList<FindTaskListener> findTaskListeners;
  private ArrayList<ListTaskListener> listTaskListeners;

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
    this.addTaskListeners = new ArrayList<>();
    this.deleteTaskListeners = new ArrayList<>();
    this.doneTaskListeners = new ArrayList<>();
    this.findTaskListeners = new ArrayList<>();
    this.listTaskListeners = new ArrayList<>();
  }

  /**
   * Adds a AddTaskListener.
   * When addTask is called, this listener will be notified.
   *
   * @param listener addTask listener
   */
  public void newAddTaskListener(AddTaskListener listener) {
    this.addTaskListeners.add(listener);
  }

  /**
   * Adds a DeleteTaskListener.
   * When deleteTask is called, this listener will be notified.
   *
   * @param listener deleteTask listener
   */
  public void newDeleteTaskListener(DeleteTaskListener listener) {
    this.deleteTaskListeners.add(listener);
  }

  /**
   * Adds a DoneTaskListener.
   * When doneTask is called, this listener will be notified.
   *
   * @param listener doneTask listener
   */
  public void newDoneTaskListener(DoneTaskListener listener) {
    this.doneTaskListeners.add(listener);
  }

  /**
   * Adds a FindTaskListener.
   * When findTask is called, this listener will be notified.
   *
   * @param listener findTask listener
   */
  public void newFindTaskListener(FindTaskListener listener) {
    this.findTaskListeners.add(listener);
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
   * Adds a task to the task list.
   *
   * @param task the task to add
   * @throws DukeException If any listeners throw a DukeException.
   */
  void addTask(Task task) throws DukeException {
    // Add task to Tasks
    this.tasks.add(task);

    // Update AddTaskListeners
    for (AddTaskListener listener : addTaskListeners) {
      listener.addTaskUpdate(this.tasks, task);
    }
  }

  /**
   * Deletes a task from the task list.
   *
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  void deleteTask(int i) throws DukeException {
    try {
      // Get task from tasks
      Task task = this.tasks.get(i);

      // Remove task
      this.tasks.remove(i);

      // Update DeleteTaskListeners
      for (DeleteTaskListener listener : deleteTaskListeners) {
        listener.deleteTaskUpdate(this.tasks, task);
      }
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskIndexException();
    }
  }

  /**
   * Marks a task as done in the task list.
   *
   * @throws DukeException If the index is invalid or any listeners throw a DukeException.
   */
  void doneTask(int i) throws DukeException {
    try {
      // Get task from tasks
      Task task = this.tasks.get(i);

      // Set task to done
      task.setDone(true);

      // Update DoneTaskListeners
      for (DoneTaskListener listener : doneTaskListeners) {
        listener.doneTaskUpdate(this.tasks, task);
      }
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskIndexException();
    }
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

    // Update FindTaskListeners
    for (FindTaskListener listener : findTaskListeners) {
      listener.findTaskUpdate(result);
    }
  }

  /**
   * Notify listeners to list tasks.
   */
  private void listTask() {
    // Update ListTaskListeners
    for (ListTaskListener listener : listTaskListeners) {
      listener.listTaskUpdate(this.tasks);
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
  public void findTaskCommandUpdate(String keyword) {
    findTask(keyword);
  }

  @Override
  public void listTaskCommandUpdate() {
    listTask();
  }
}
