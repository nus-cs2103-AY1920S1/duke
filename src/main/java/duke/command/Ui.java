package duke.command;

import java.util.ArrayList;
import java.util.Scanner;

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
     * @return
     */
    public String printTaskDone(Task task) {
        String message;
        message = "Nice! I've marked this task as done:";
        message =  message + "[" + "\u2713" + "] " + task.getDescription();
        return message;
    }

    /**
     * prints the message when a task is deleted.
     * @param task The task that is removed from the list.
     * @param list The list of Task where the task was from.
     */
    public String printDeleteTask(Task task, ArrayList<Task> list) {
        String message;
        message = "Noted. I've removed this task:\n";
        message = message + task + "\n";
        message = message + "Now you have " + list.size() + " tasks in the list";
        return message;
    }

    /**
     * prints the message when a todo task is added to the list.
     * @param task Todo task that is added to the list of Task.
     * @param list The list of task to which the todo task is added.
     */
    public String printToDoTask(Task task, ArrayList<Task> list) {
        String message;
        message = "Got it. I've added this task:\n";
        message = message + " " + task + "\n";
        message = message + "Now you have " + list.size() + " tasks in the list";
        return message;
    }

    /**
     * prints the message when an event task is added to the list.
     * @param task Event task that is added to the list of Task.
     * @param list The list of task to which the event task is added.
     */
    public String printEventtTask(Task task, ArrayList<Task> list) {
        String message;
        message = "Got it. I've added this task:\n";
        message = message + " " + task + "\n";
        message = message + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * prints the message when a deadline task is added to the list.
     * @param task Deadline task that is added to the list of Task.
     * @param list The list of task to which the deadline task is added.
     */
    public String printDeadlineTask(Task task, ArrayList<Task> list) {
        String message;
        message = "Got it. I've added this task: \n";
        message = message + " " + task + "\n";
        message = message + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * prints all the task that is in the list of Task.
     * @param list The list of task in which all the tasks are to be printed.
     */
    public String printList(ArrayList<Task> list) {
        String message;
        if (list.size() == 0) {
            message = "Nothing added yet";
        } else {
            message = "Here are the tasks in your list:";
            for (int i = 1; i <= list.size(); i++) {
                message = message + "\n" + i + "." + list.get(i - 1);
            }
        }
        return message;
    }

    /**
     * prints a "bye" message when the programme terminates.
     */
    public String printByeMessage() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * prints a "welcome" message when the programme starts.
     */
    public static String printWelcomeMessage() {
        String message;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        message = "Hello from\n" + logo + "\n";
        message = message + "What can I do for you?";
        return message;
    }
}
