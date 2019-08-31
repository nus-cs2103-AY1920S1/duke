package duke.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.command.Parser;
import duke.task.Task;
import duke.DukeException;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("Error: File not found. Empty File has been created.");
    }

    public void run(Parser parser) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                parser.parseLineInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }
        printByeMessage();
    }

    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[\u2713] " + task.getDescription());
    }

    public void printDeleteTask(Task task, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public void printToDoTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public void printEvenTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void printDeadlineTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void printList(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("Nothing added yet");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("" + i + "." + list.get(i - 1));
            }
        }
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
