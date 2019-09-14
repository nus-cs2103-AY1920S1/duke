package task;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * The Ui class defines the behaviour of a user interface.
 * 
 * @author Joel Loong
 */
public class Ui {

    public static String startOfInteractions() {
        return "Konnichiwa!\n" + "Watashi wa Nezuko-chan!\n" + "What can I do for you?";
    }

    /**
     * Reads input from the user.
     */
    public static void readUserInput() {
        Scanner sc = new Scanner(System.in);

        String textInput;
        if (sc.hasNext()) {
            textInput = sc.nextLine();
        } else {
            textInput = "bye";
        }

        Parser.parse(textInput);

        // Close the scanner
        sc.close();
    }

    public static String endOfInteractions() {
        return "Sayonara. Hope to see you again soon!";
    }

    /**
     * Prints the list of all tasks.
     * 
     * @return The string format of the list of all tasks.
     */
    public static String printList() {
        String output = "";
        ArrayList<Task> task = TaskList.getTasks();
        int counter = TaskList.getCounter();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Hai! Here " + isPlural + " the " + taskIfPlural + " in your list:\n";
        for (int i = 1; i <= counter; i++) {
            output += "" + i + "." + task.get(i - 1) + "\n";
        }

        return output;
    }

    /**
     * Prints the task that was marked as done.
     * 
     * @param task Task marked as done.
     * @return The string format of the task marked as done.
     */
    public static String printDoneTask(Task task) {
        String output = "";
        output += "Hai! I've marked this task as done:\n";
        output += task + "\n";
        return output;
    }

    /**
     * Prints the task that was last added.
     * 
     * @param t Last added task.
     * @return The string format of added task.
     */
    public static String printAddedTask(Task t) {
        String output = "";
        output += "Hai! I've added this task:\n" + t + "\n";
        int counter = TaskList.getCounter();
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Now you have " + counter + " " + taskIfPlural + " in the list.\n";
        return output;
    }

    /**
     * Prints the task that was last deleted.
     * 
     * @param task Last deleted task.
     * @return The string format of deleted task.
     */
    public static String printDeleteTask(Task task) {
        String output = "";
        int counter = TaskList.getCounter();
        String taskIfPlural = counter <= 1 ? "task" : "tasks";
        output += "Hai! I've removed this task:\n";
        output += task + "\n";
        output += "Now you have " + counter + " " + taskIfPlural + " in the list.\n";
        return output;
    }

    /**
     * Prints the tasks that were found.
     * 
     * @param foundTasks Tasks that were found.
     * @return The string format of found tasks.
     */
    public static String printFoundTask(List<Task> foundTasks) {
        String output = "";
        int counter = foundTasks.size();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Hai! Here " + isPlural + " the matching " + taskIfPlural + " in your list:\n";
        for (int i = 1; i <= counter; i++) {
            output += "" + i + "." + foundTasks.get(i - 1) + "\n";
        }
        return output;
    }

    /**
     * Prints the customized DukeException message or general exception message.
     * 
     * @param e Exception to be printed.
     */
    public static String printException(Exception e) {
        return e.getMessage();
    }
}