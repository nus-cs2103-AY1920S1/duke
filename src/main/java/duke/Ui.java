package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Duke.\nWhat can I do for you?\n"
                + "To input dates and times for deadlines and events, "
                + "please use the format: 29/03/2019, 6:05pm";
        System.out.println(welcomeMessage);
    }

    public void showExitMessage() {
        System.out.println("Bye! Hope to see you again soon :)");
    }

    public void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printTaskList(TaskList tasks) {

        System.out.println("Here are the task(s) in your list:");

        String task;
        for (int i = 1; i <= tasks.size(); i++) {
            task = String.format("%d.%s", i, tasks.get(i));
            System.out.println(task);
        }

        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list right now.");
        }
    }

    public void printTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", task));
    }

    public void printTaskDeleted(TaskList tasks, Task task) {
        System.out.println(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        ));
    }

    public void printTaskAdded(TaskList tasks, Task task) {
        System.out.println(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        ));
    }
}
