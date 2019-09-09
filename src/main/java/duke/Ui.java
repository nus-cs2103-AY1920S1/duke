package duke;

import duke.TaskList;
import duke.task.Task;

import java.util.LinkedList;
import java.util.Scanner;

public class Ui {

    public void showLoadingError() {
        System.out.println("\u2639 OOPS!!! Unable to load file. Try again!");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        LinkedList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showChangedTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public void showDeletedTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
    }

    public void showNumTasks(TaskList taskList) {
        int size = taskList.getTasks().size();
        System.out.print("Now you have " + size);
        if (size == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }

    public void printAddedTask(Task task, TaskList tasks) {
        int taskListSize = tasks.getTasks().size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.print("Now you have " + taskListSize);
        if (taskListSize == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }

}
