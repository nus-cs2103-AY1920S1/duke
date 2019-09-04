package duke.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.command.Parser;
import duke.task.Task;
import duke.DukeException;

/**
 * Represents the User-Interface that prints the messages from various
 * inputs from the User.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints message that shows that the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error: File not found. Empty File has been created.");
    }

    /**
     * Takes input from the user line by line and process the input
     * using the Parser object given in the parameter.
     * @param parser Parser object that is responsible for executing
     *               the user input.
     */
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

    /**
     * prints the message when a task is done.
     * @param task task that has just been marked as done.
     */
    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[\u2713] " + task.getDescription());
    }

    /**
     * prints the message when a task is deleted.
     * @param task The task that is removed from the list.
     * @param list The list of Task where the task was from.
     */
    public void printDeleteTask(Task task, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    /**
     * prints the message when a todo task is added to the list.
     * @param task Todo task that is added to the list of Task.
     * @param list The list of task to which the todo task is added.
     */
    public void printToDoTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    /**
     * prints the message when an event task is added to the list.
     * @param task Event task that is added to the list of Task.
     * @param list The list of task to which the event task is added.
     */
    public void printEvenTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * prints the message when a deadline task is added to the list.
     * @param task Deadline task that is added to the list of Task.
     * @param list The list of task to which the deadline task is added.
     */
    public void printDeadlineTask(Task task, ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * prints all the task that is in the list of Task.
     * @param list The list of task in which all the tasks are to be printed.
     */
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

    /**
     * prints a "bye" message when the programme terminates.
     */
    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * prints a "welcome" message when the programme starts.
     */
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
