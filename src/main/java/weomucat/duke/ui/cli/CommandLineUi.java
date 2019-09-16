package weomucat.duke.ui.cli;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import weomucat.duke.Pair;
import weomucat.duke.task.NumberedTaskList;
import weomucat.duke.task.Task;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;
import weomucat.duke.ui.message.Message;
import weomucat.duke.ui.message.MessageText;

/**
 * Represents a Command Line User Interface of Duke.
 */
public class CommandLineUi implements Ui {

  private static final String MESSAGE_INDENTATION = "\t";
  private static final String SINGLE_HORIZONTAL_LINE =
      "------------------------------------------------------------";
  private static final String DOUBLE_HORIZONTAL_LINE =
      "============================================================";

  private ArrayList<UserInputListener> userInputListeners;
  private BufferedReader bufferedReader;
  private boolean running;

  /**
   * Default constructor.
   */
  public CommandLineUi() {
    this.userInputListeners = new ArrayList<>();
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    this.running = true;
  }

  @Override
  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @Override
  public void acceptUserInput() {
    while (this.running) {
      try {
        // Work around for non-blocking scanner.
        // https://www.javaspecialists.eu/archive/Issue153.html
        while (!this.bufferedReader.ready()) {

          // Terminate if not running.
          if (!this.running) {
            return;
          }

          Thread.sleep(THREAD_POLL_SLEEP_DURATION);
        }

        // Get next user input
        String userInput = this.bufferedReader.readLine().trim();

        // Ignore empty user input
        if (!userInput.equals("")) {
          for (UserInputListener listener : userInputListeners) {
            listener.userInputUpdate(userInput);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void displayMessage(Message message) {

    // Title
    System.out.println(DOUBLE_HORIZONTAL_LINE);
    for (MessageText text : message.getTitle()) {
      System.out.print(text.toCli());
    }
    System.out.println();
    if (message.getTitle().size() > 0) {
      System.out.println(SINGLE_HORIZONTAL_LINE);
    }

    // Body
    for (MessageText text : message.getBody()) {
      System.out.print(text.toCli());
    }
    System.out.println();
    System.out.println(DOUBLE_HORIZONTAL_LINE);
  }

  @Override
  public void displayError(Message message) {
    // TODO: ANSI Colors might not work on all terminals
    System.out.print("\033[38;2;255;0;0m");
    System.out.println(DOUBLE_HORIZONTAL_LINE);
    for (MessageText text : message.getBody()) {
      System.out.print(text.toCli());
    }
    System.out.println(DOUBLE_HORIZONTAL_LINE);
    System.out.print("\033[38;2;0;0;0m");
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
  }

  @Override
  public void listTaskUpdate(Message message, NumberedTaskList tasks) {
    displayMessage(message);
    for (Pair<Integer, Task> pair : tasks) {
      // Get task from tasks
      Task task = pair.value();

      // Format task with no. in front
      Message m = task.toMessage();
      String title = String.format("%d. %s", pair.key(), m.getTitle());
      displayMessage(m.addTitle(title));
    }
  }

  @Override
  public void modifyTaskUpdate(Message message, Task task) {
    displayMessage(message);
    displayMessage(task.toMessage());
  }

  @Override
  public void taskListSizeUpdate(int size) {
    displayMessage(new Message().addBody(
        String.format("Now you have %d task(s) in the list.", size)));
  }
}
