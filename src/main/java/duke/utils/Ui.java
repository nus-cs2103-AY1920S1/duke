package duke.utils;

import duke.tasks.Task;

import java.util.LinkedList;

/**
 * Represents the things that the user sees
 * on Duke.
 */
public class Ui {

    /**
     * Prints out the line for formatting purposes.
     */
    public void printline() {
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Prints a new line character.
     */
    public void printnewline() {
        System.out.println("\n");
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String name = "duke";
        printline();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printline();
    }

    /**
     * Confirmation message when the task is being added into the list.
     *
     * @param t task that is being added into the list
     * @param i number of task that are in the list
     */
    public String takeInput(Task t, int i) {
        printline();
        String msg = "Got it. I've added this task:\n";
        msg = msg + "\t" + t + "\n";
        msg = msg + "Now you have " + i + " tasks in the list";
        return msg;
    }

    /**
     * Prints out the current list of tasks.
     *
     * @param li List containing all the tasks
     */
    public void printList(LinkedList<Task> li) {
        printline();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < li.size(); i++) {
            int j = i + 1;
            System.out.println("\t" + j + " " + li.get(i));
        }
        printline();
    }

    /**
     * Prints the done message to show that the task
     * has been completed.
     *
     * @param task the task that has been completed
     */
    //completion of task confirmation
    public String printDone(Task task) {
        printline();
        String msg = "\tNice! I've marked this task as done:\n";
        msg = msg + "\t\t" + task;
        return msg;
    }

    /**
     * Prints the message to confirm that a task has
     * been deleted.
     *
     * @param task the task that is deleted
     * @param i the number of tasks left in the list
     */
    public String printDelete(Task task, int i) {
        String msg = "\tNoted. I've removed this task:\n";
        msg = msg + "\t\t" + task + "\n";
        msg = msg + "\tNow you have " + i + " tasks in the list";
        return msg;
    }


}