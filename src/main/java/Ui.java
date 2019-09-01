import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

  public void showWelcome() {
    String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    // Welcome message
    System.out.println("\t____________________________________________________________");
    System.out.println("\n\tHello! I'm Duke");
    System.out.println("\tWhat can I do for you?");
    System.out.println("\t____________________________________________________________\n");
  }

  public void showTopBorder() {
    System.out.println("\t____________________________________________________________");
  }

  public void showBottomBorder() {
    System.out.println("\t____________________________________________________________\n");
  }

  public void printTasks(ArrayList<Task> tasks) {
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println("\n\t" + (i + 1) + ". " + tasks.get(i).toString());
    }
  }

  public void showLoadingError() {
    showTopBorder();
    System.out.println("\n\tSorry! There was an error loading the files from the system.");
    showBottomBorder();
  }

  public String readCommand() {
    Scanner sc = new Scanner(System.in);
    String command = sc.nextLine();
    return command;
  }
}