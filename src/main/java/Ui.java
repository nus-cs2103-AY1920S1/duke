package duke.ui;

import java.util.Scanner;
import java.util.ArrayList;
import duke.task.Task;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String userInput() {
        return this.sc.nextLine();
    }

    public void printFormat(String message) {
        System.out.println(message);
    }

    public void showWelcome() {
        printFormat("Hello, I'm Duke\nWhat can I do for you?");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            counter++;
            System.out.println(counter + "." + task.toString());
        }
    }

    public void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    public void printDone(String task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    public void printRemoveMessage(String task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printAddTaskMessage(String task, int size) {
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}