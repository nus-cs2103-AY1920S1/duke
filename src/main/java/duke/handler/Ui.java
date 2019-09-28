package duke.handler;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public void showFarewell() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showTaskAdded(Task addedTask, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", numberOfTasks));
    }

    public void showTaskDeleted(Task deletedTask, int numberOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", numberOfTasks));
    }

    public void showTaskDone(Task completedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completedTask);
    }

    public void showMatchingTasksMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showMatchingTasks(Task matchedTask, int taskIndex) {
        System.out.println(taskIndex + "." + matchedTask);
    }

    public void showLine() {
        System.out.println("_______________________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! File could not be found.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
