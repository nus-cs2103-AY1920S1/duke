package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    protected Scanner userInput = new Scanner(System.in);

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        drawLine();
    }

    public void drawLine() {
        System.out.println("\t-----------------------------------------------------------------------------");
    }

    public void printExitMessage() {
        printMessage("\t Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return userInput.nextLine();
    }

    public void closeScanner() {
        userInput.close();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printException(DukeException exception) {
        String message = exception.getMessage();
        System.out.println("\t " + message);
    }

    public void printDeleteMessage(Task task, int numberOfTasks) {
        printMessage("\t Noted. I've removed this task: ");
        printMessage("\t\t " + task);
        printMessage("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
    }

    public void printDoneMessage(Task task) {
        printMessage("\t Nice! I've marked this task as done: ");
        printMessage("\t\t " + task);
    }

    public void printAddedMessage(Task task, int numberOfTasks) {
        printMessage("\t Got it. I've added this task: ");
        printMessage("\t\t " + task);
        printMessage("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
    }

    public void showLoadingError() {
        printMessage("\t \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
