package duke.ui;

import java.util.Scanner;

public class Ui {
  private Scanner reader;

  public Ui() {
    reader = new Scanner(System.in);
  }

  public void showWelcome() {
    System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
  }

  public String readCommand() {
    return reader.nextLine();
  }

  public static void printLine(String line) {
    System.out.println(line);
  }

  public void showError(String err) {
    System.out.println(err);
  }

  public void showLoadingError() {
    System.out.println("You have not stored any tasks!");
  }
}
