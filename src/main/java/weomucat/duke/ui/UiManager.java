package weomucat.duke.ui;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.util.ArrayList;
import java.util.LinkedList;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.NumberedTaskList;
import weomucat.duke.task.Task;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.task.listener.ModifyTaskListener;
import weomucat.duke.task.listener.TaskListSizeListener;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * UiManager is responsible for managing many uis.
 * Whenever any user input is received from any ui, all ui listeners will be notified.
 * Whenever a task update is received, all uis will be notified.
 */
public class UiManager implements ByeCommandListener,
    ListTaskListener, ModifyTaskListener, TaskListSizeListener,
    UserInputListener {

  private ArrayList<Ui> uis;
  private ArrayList<UserInputListener> userInputListeners;
  private LinkedList<String> userInputQueue;

  private boolean running;

  /**
   * Default constructor.
   */
  public UiManager() {
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
    assert ui != null;

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
          Thread.sleep(THREAD_POLL_SLEEP_DURATION);
        } else {
          String userInput = userInputQueue.pollFirst();
          for (UserInputListener listener : this.userInputListeners) {
            listener.userInputUpdate(userInput);
          }
        }
      } catch (DukeException e) {
        displayError(new Message("☹ OOPS!!! " + e.getMessage()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Displays a nice message to the user.
   *
   * @param message message to display
   */
  public void displayMessage(Message message) {
    for (Ui ui : this.uis) {
      ui.displayMessage(message);
    }
  }

  /**
   * Displays an error message to the user.
   *
   * @param message error message to display
   */
  public void displayError(Message message) {
    for (Ui ui : this.uis) {
      ui.displayError(message);
    }
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;

    // Farewell user
    displayMessage(new Message("Bye. Hope to see you again soon!"));

    for (Ui ui : this.uis) {
      ui.byeCommandUpdate();
    }
  }

  @Override
  public void listTaskUpdate(Message message, NumberedTaskList tasks) {
    for (Ui ui : this.uis) {
      ui.listTaskUpdate(message, tasks);
    }
  }

  @Override
  public void modifyTaskUpdate(Message message, Task task) {
    for (Ui ui : this.uis) {
      ui.modifyTaskUpdate(message, task);
    }
  }

  @Override
  public void taskListSizeUpdate(int size) {
    for (Ui ui : this.uis) {
      ui.taskListSizeUpdate(size);
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
