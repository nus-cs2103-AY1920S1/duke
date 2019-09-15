package weomucat.duke.ui;

import java.util.ArrayList;
import weomucat.duke.command.Command;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.listener.DisplayCommandListener;
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
public class UiManager implements ByeCommandListener, DisplayCommandListener,
    ListTaskListener, ModifyTaskListener, TaskListSizeListener,
    UserInputListener {

  private ArrayList<Ui> uis;
  private ArrayList<UserInputListener> userInputListeners;

  /**
   * Default constructor.
   */
  public UiManager() {
    this.uis = new ArrayList<>();
    this.userInputListeners = new ArrayList<>();
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
  }

  @Override
  public void displayMessage(Message... messages) {
    for (Ui ui : this.uis) {
      for (Message message : messages) {
        ui.displayMessage(message);
      }
    }
  }

  @Override
  public void displayError(Message... messages) {
    for (Ui ui : this.uis) {
      for (Message message : messages) {
        ui.displayError(message);
      }
    }
  }

  @Override
  public void byeCommandUpdate() {
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
  public void userInputUpdate(String userInput) {
    for (UserInputListener listener : this.userInputListeners) {
      listener.userInputUpdate(userInput);
    }
  }

  @Override
  public void commandUpdate(Command<?> command) {
    for (UserInputListener listener : this.userInputListeners) {
      listener.commandUpdate(command);
    }
  }
}
