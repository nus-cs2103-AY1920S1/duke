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

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        showLine();
    }

    public void showLoadingError() {
        System.out.println("File cannot be loaded. A new task list is created.");
    }

    public void showError(String msg) {
        showLine();
        System.out.println("     " + msg);
        //showLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showTask(Task task, TaskList tasks, String msg) {
        showLine();
        System.out.println(msg);
        System.out.println("       " + task);
        tasks.printNumber();
        //showLine();
    }

    public void showExit() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        //showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
