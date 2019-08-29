package duke.command;

import duke.DukeException;
import duke.task.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public String readLine() {
        return sc.nextLine();
    }

    public void printWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.err.println("Error: Unable to load. File not found. Empty list is created.");
    }

    public void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        IntStream.rangeClosed(1, list.size()).forEach(x -> {
            Task task = list.get(x - 1);
            System.out.println(x + "." + task.toString());
        });
    }

    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void printDeleteTask(Task removed, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed.toString());
        System.out.println("Now you have " + list.size() + " in the list.");
    }

    public void printAddTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }
}
