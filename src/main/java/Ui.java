import java.util.Scanner;

public class Ui {

    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {
    }

    public void showWelcome() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n");
    }

    public String readCommand() {
        String command = null;
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        return command;
    }

    public void showLoadingError() {
        System.out.println("     ☹ OOPS!!! No saved list detected. Creating new list!");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void sayGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void showError(Exception e) {
        if (e instanceof InvalidInputDukeException) {
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e instanceof EmptyTaskDukeException) {
            System.out.println(String.format("     ☹ OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
        } else if (e instanceof InvalidTaskDukeException) {
            System.out.println(String.format("     ☹ OOPS!!! Invalid input! Make sure your %s has a description and /at or /by.", e.getMessage()));
        } else {
            System.out.println(e.getMessage()); // for undeclared exceptions
        }
    }


}
