package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDone(Task task) {
        String output = "Nice! I've marked this task as done:\n  " + task;
        System.out.println(output);
    }

    public void printDelete(Task task, int counter) {
        String output = "Noted. I've removed this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        System.out.println(output);
    }

    public void printAdd(Task task, int counter) {
        String output = "Got it. I've added this task:\n  " + task;
        output += "\nNow you have " + counter + " tasks in the list.";
        System.out.println(output);
    }

    public void showLoadingError() {
        System.out.println("An error occurred with loading the input file, using an empty one instead.");
    }
}