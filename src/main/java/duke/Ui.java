package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 *
 * @author scwaterbear
 */
public class Ui {

  /**
   * Scanner to receive user input.
   */
  private Scanner scanner;

  /**
   * Class constructor.
   */
  Ui() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Prints Duke welcome message.
   */
  void showWelcome() {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke");
    System.out.println("What can I do for you?");
  }

  /**
   * Returns trimmed user input.
   *
   * @return String trimmed user input.
   */
  String readCommand() {
    return scanner.nextLine().trim();
  }

  /**
   * Prints a loading error message.
   */
  void showLoadingError() {
    System.out.println("Error loading data from file");
  }

  /**
   * Prints an error message.
   *
   * @param errorMessage errorMessage to print.
   */
  void showError(String errorMessage) {
    System.out.println(errorMessage);
  }

  /**
   * Prints the task list it is supplied with.
   *
   * @param list task list to print.
   */
  public void printList(List<Task> list) {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1) + "." + list.get(i).toString());
    }
  }
}
