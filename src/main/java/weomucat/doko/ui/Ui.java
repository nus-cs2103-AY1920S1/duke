package weomucat.doko.ui;

import weomucat.doko.command.listener.ByeCommandListener;
import weomucat.doko.task.listener.ListTaskListener;
import weomucat.doko.task.listener.ModifyTaskListener;
import weomucat.doko.task.listener.TaskListSizeListener;
import weomucat.doko.ui.listener.UserInputListener;
import weomucat.doko.ui.message.Message;

/**
 * Ui is responsible for receiving input from the user, and displaying output to the user.
 */
public interface Ui extends ByeCommandListener,
    ListTaskListener, ModifyTaskListener, TaskListSizeListener {

  /**
   * Add a UserInputListener to the Ui. When user input is received, this listener will be
   * notified.
   *
   * @param listener user input listener
   */
  void newUserInputListener(UserInputListener listener);

  /**
   * Tell Ui to start listening for user input.
   */
  void acceptUserInput();

  /**
   * Displays a nice message to the user.
   *
   * @param message message to display.
   */
  void displayMessage(Message message);

  /**
   * Displays an error message to the user.
   *
   * @param message error message to display.
   */
  void displayError(Message message);
}
