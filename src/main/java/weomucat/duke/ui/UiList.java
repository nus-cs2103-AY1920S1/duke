package weomucat.duke.ui;

import static weomucat.duke.Duke.THREAD_SLEEP_DURATION;

import java.util.ArrayList;
import java.util.LinkedList;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.FindTaskListener;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * UiList is responsible for managing many uis.
 * Whenever any user input is received from any ui, all ui listeners will be notified.
 * Whenever a task update is received, all uis will be notified.
 */
public class UiList implements AddTaskListener, ByeCommandListener, DeleteTaskListener,
    DoneTaskListener, FindTaskListener, ListTaskListener, UserInputListener {

  private ArrayList<Ui> uis;
  private ArrayList<UserInputListener> userInputListeners;
  private LinkedList<String> userInputQueue;

  private boolean running;

  /**
   * Default constructor.
   */
  public UiList() {
    this.uis = new ArrayList<>();
    this.userInputListeners = new ArrayList<>();
    this.userInputQueue = new LinkedList<>();
    this.running = true;
  }

  /**
   * Add a Ui to the UiList. Any task updates will update all uis.
   *
   * @param ui the ui to add
   */
  public void addUi(Ui ui) {
    this.uis.add(ui);
    ui.newUserInputListener(this);
  }

  /**
   * Add a UserInputListener to the UiList. When user input is received, this listener will be
   * notified.
   *
   * @param listener user input listener
   */
  public void newUserInputListener(UserInputListener listener) {
    userInputListeners.add(listener);
  }

  /**
   * Tell all uis to start listening for user input.
   */
  public void acceptUserInput() {
    for (Ui ui : this.uis) {
      // Allow uis to block their own threads for user input.
      // This is to allow the main thread to continue.
      new Thread(ui::acceptUserInput).start();
    }

    // Main loop to get user input.
    while (this.running) {
      try {
        if (userInputQueue.isEmpty()) {
          Thread.sleep(THREAD_SLEEP_DURATION);
        } else {
          String userInput = userInputQueue.pollFirst();
          for (UserInputListener listener : this.userInputListeners) {
            listener.userInputUpdate(userInput);
          }
        }
      } catch (DukeException e) {
        displayError(e.getMessage());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Displays a nice message to the user.
   *
   * @param lines use varargs for multiline messages.
   */
  public void displayMessage(String... lines) {
    for (Ui ui : this.uis) {
      ui.displayMessage(lines.clone());
    }
  }

  /**
   * Displays an error message to the user.
   *
   * @param lines use varargs for multiline messages.
   */
  public void displayError(String... lines) {
    for (Ui ui : this.uis) {
      ui.displayError(lines.clone());
    }
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    for (Ui ui : this.uis) {
      ui.addTaskUpdate(tasks, task);
    }
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;

    // Farewell user
    displayMessage("Bye. Hope to see you again soon!");

    for (Ui ui : this.uis) {
      ui.byeCommandUpdate();
    }
  }

  @Override
  public void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    for (Ui ui : this.uis) {
      ui.deleteTaskUpdate(tasks, task);
    }
  }

  @Override
  public void doneTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    for (Ui ui : this.uis) {
      ui.doneTaskUpdate(tasks, task);
    }
  }

  @Override
  public void findTaskUpdate(TaskListTasks tasks) {
    for (Ui ui : this.uis) {
      ui.findTaskUpdate(tasks);
    }
  }

  @Override
  public void listTaskUpdate(TaskListTasks tasks) {
    for (Ui ui : this.uis) {
      ui.listTaskUpdate(tasks);
    }
  }

  @Override
  public void byeUpdate() throws DukeException {
    for (UserInputListener listener : this.userInputListeners) {
      listener.byeUpdate();
    }
  }

  @Override
  public void userInputUpdate(String userInput) {
    userInputQueue.addLast(userInput);
  }
}
