package ui;

import java.util.Scanner;

public class Ui {
  private Scanner reader = new Scanner(System.in);

  public static void showWelcome() {
    System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
  }

  public String readCommand() {
    return reader.nextLine();
  }

  public void showLine() {

  }

  public static void showError(String err) {
    System.out.println(err);
  }
}
