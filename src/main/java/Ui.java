package duke.ui;

import java.util.Scanner;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.FindPair;

/**
 * Represent the Ui object that is used for the user interaction i.e. the input
 * and the output from the program.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a Ui object and initialize the scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
    }

    /**
     * Prints the welcome message between two horizontal lines.
     */
    public String welcome() {
        return ("\t Hello, I'm Duke!\n\t What can I do for you?");
    }

    /**
     * Prints the list of tasks within a tasklist.
     * 
     * @param db the tasklist which contains all the tasks
     */
    public String printList(TaskList db) {
        if (db.size() == 0) {
            return "\t You have no tasks in your list.";
        }
        StringBuilder result = new StringBuilder();
        result.append("\t Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : db.getList()) {
            String output = String.format("\t %d. %s\n", index, task.toString());
            result.append(output);
            index++;
        }
        return result.toString();
    }

    /**
     * Reads the command from the user input and return it to the parser.
     * 
     * @return a string containing all the user's command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if there is a next line for file inputs.
     * 
     * @return true if there is a next value, else false
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Print the message when a task is deleted from the task list.
     * 
     * @param task the task that is deleted
     * @param db   the tasklist where the task is deleted from
     */
    public String deleteMessage(Task task, TaskList db) {
        StringBuilder result = new StringBuilder();
        result.append("\t Noted. I've removed this task:\n");
        result.append("\t  " + task + "\n");
        result.append(String.format("\t Now you have %d tasks in your list\n", db.size()));
        return result.toString();
    }

    /**
     * Prints the message when a task is marked as done.
     * 
     * @param task the task that is to be mark as done
     */
    public String updateMessage(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("\t Nice! I've marked this task as done:\n");
        result.append("\t  " + task.toString());
        return result.toString();
    }

    /**
     * Prints the message when a task is added to the tasklist.
     * 
     * @param task the task that is to be added to the tasklist
     * @param db   the tasklist that contains all the tasks
     */
    public String addMessage(Task task, TaskList db) {
        StringBuilder result = new StringBuilder();
        result.append("\t Got it. I've added this task:\n");
        result.append("\t  " + task + "\n");
        result.append(String.format("\t Now you have %d tasks in your list\n", db.size()));
        return result.toString();
    }

    /**
     * Prints the error message when an exception is faced.
     * 
     * @param e the exception that is faced
     */
    public String errorMessage(DukeException e) {
        return ("\t " + e.toString() + "\n");
    }

    /**
     * Prints the bye message when the user leaves the program between two line.
     */
    public String byeMessage() {
        return ("\t Bye. Hope to see you again soon!");
    }

    /**
     * Prints the message when the tasks are found.
     * 
     * @param db the list which the tasks are to be searched from
     */
    public String searchMessage(ArrayList<FindPair> db) {
        StringBuilder result = new StringBuilder();
        result.append("\t Here are the matching tasks in your list:\n");
        int index = 1;
        for (FindPair temp : db) {
            if (temp.isFound()) {
                Task task = temp.getTask();
                String output = String.format("\t %d. %s\n", index, task.toString());
                result.append(output);
            }
            index++;
        }
        return result.toString();
    }

    /**
     * Prints the message when a task priority is updated.
     * 
     * @param task the task that has its priority updated
     * @return a string representation of the message to be printed
     */
    public String updatePriorityMessage(Task task) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("\t Nice! I've marked this task as %s priority:\n", task.getPriority()));
        result.append("\t  " + task.toString());
        return result.toString();
    }

    public void print(String message) {
        System.out.println(message);
    }
}