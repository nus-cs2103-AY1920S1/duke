package duke.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.task.Task;
import duke.DukeException;
import duke.task.TaskComparator;

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
        Collections.sort(list, new TaskComparator());
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

    public static int taskNumber = 1;

    public String printListUsingStream(ArrayList<Task> list) {
        Collections.sort(list, new TaskComparator());
        String message;
        if (list.size() == 0) {
            message = "Nothing added yet";
        } else {
            Stream<Task> stream = list.stream();
            message = "Here are the tasks in your list";
            Optional<String> result = stream.map(task -> task.toString())
                    .reduce((t1, t2) -> {
                        if (t1.substring(0, 1).equals("1")) {
                            String m1 = t1;
                            String m2 = Ui.taskNumber + "." + t2;
                            Ui.taskNumber++;
                            return m1 + "\n" + m2;
                        } else {
                            String m1 = Ui.taskNumber + "." + t1;
                            Ui.taskNumber++;
                            String m2 = Ui.taskNumber + "." + t2;
                            Ui.taskNumber++;
                            return m1 + "\n" + m2;
                        }
                    });
            Ui.taskNumber = 1;
            message = message + "\n" + result.get();
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


