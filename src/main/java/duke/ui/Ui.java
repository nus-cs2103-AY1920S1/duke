package duke.ui;

import duke.task.Task;
import duke.exception.DukeException;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String error) {
        System.err.println(error);
    }

    public void showWelcome(List<Task> tasks) {
        showLine();
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?\n");

        try {
            if (!tasks.isEmpty()) {
                printTasks(tasks);
            } else {
                System.out.println("     There are no tasks in the list right now.");
            }
        } catch (DukeException e) {
            showError(e.getMessage());
        } finally {
            showLine();
        }
    }

    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void printTasks(List<Task> tasks) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("     ☹ OOPS!!! The list is empty.");
        }
        System.out.println("     Here are the tasks in your list:");
        int count = 0;
        for (Task task: tasks) {
            count++;
            System.out.println("     " + count + "." + task);
        }
    }

    public void showLoadingError() {
        System.out.println("     No existing task list available!");
    }

    public void addedTask(Task task, int size) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    public void deletedTask(Task task, int size) {
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    public void doneTask(List<Task> tasks, int number) {
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + tasks.get(number - 1));
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
