package duke;

import duke.task.Task;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println();
    }

    public void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showLoadingError() {
        System.out.println("Sorry, I am not able to load the saved file.");
    }

    public void showSaveError() {
        System.out.println("Sorry, I am not able to save your tasks.");
    }

    public void showInvalidCommandError() {
        System.out.println("Sorry, I do not understand what you mean.");
    }

    public void showInvalidDateError() {
        System.out.println("Sorry, I do not understand the date format.");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

}
