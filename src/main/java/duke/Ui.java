package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Encapsulates a Ui object to handle user interaction.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /*public String showWelcome() {
        String finalMsg;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        finalMsg = "Hello from\n" + logo;
        finalMsg += "Hello! I'm Duke\n";
        finalMsg += "What can I do for you?\n";
        return finalMsg;
    }*/

    public String showLoadingError() {
        return "File cannot be loaded. A new task list is created.\n";
    }

    public String showTask(Task task, TaskList tasks, String message) {
        String finalMsg = message;
        finalMsg += task;
        finalMsg += tasks.printNumber();
        return finalMsg;
    }

    public String showExit() {
        String finalMsg = "Bye. Hope to see you again soon!\n";
        return finalMsg;
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
