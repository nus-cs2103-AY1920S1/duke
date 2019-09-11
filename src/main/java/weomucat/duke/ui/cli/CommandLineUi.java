package weomucat.duke.ui.cli;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.ui.Message;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;

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
    System.out.println(MESSAGE_INDENTATION + DOUBLE_HORIZONTAL_LINE);

    // Title
    String title = message.getTitle();
    if (!title.equals("")) {
      System.out.println(MESSAGE_INDENTATION + title);
      System.out.println(MESSAGE_INDENTATION + SINGLE_HORIZONTAL_LINE);
    }

    // Body
    String[] body = message.getBody().split("\n");
    if (!(body.length == 1 && body[0].equals(""))) {
      for (String line : body) {
        System.out.println(MESSAGE_INDENTATION + line);
      }
    }

    System.out.println(MESSAGE_INDENTATION + DOUBLE_HORIZONTAL_LINE);
  }

  @Override
  public void displayError(Message message) {
    // TODO: ANSI Colors might not work on all terminals
    System.out.print("\033[38;2;255;0;0m");
    System.out.println(MESSAGE_INDENTATION + DOUBLE_HORIZONTAL_LINE);
    for (String line : message.getBody().split("\n")) {
      System.out.println(MESSAGE_INDENTATION + line);
    }
    System.out.println(MESSAGE_INDENTATION + DOUBLE_HORIZONTAL_LINE);
    System.out.print("\033[38;2;0;0;0m");
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
  }

  @Override
  public void listTaskUpdate(Message message, TaskListTasks tasks) {
    displayMessage(message);
    for (int i = 0; i < tasks.size(); i++) {
      // Get task from tasks
      Task task = tasks.get(i);

      // Format task with no. in front
      Message m = task.toMessage();
      String title = String.format("%d. %s", i + 1, m.getTitle());
      displayMessage(m.setTitle(title));
    }
  }

  @Override
  public void modifyTaskUpdate(Message message, Task task) {
    displayMessage(message);
    displayMessage(task.toMessage());
  }

  @Override
  public void taskListSizeUpdate(int size) {
    displayMessage(new Message(
        String.format("Now you have %d task(s) in the list.", size)));
  }
}
