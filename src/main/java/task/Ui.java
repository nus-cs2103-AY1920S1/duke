package task;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Ui {

    public static String startOfInteractions() {
        return "Hello I'm Duke\n" + "What can I do for you?";
    }

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
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks in taskList.
     */
    public static String printList() {
        String output = "";
        ArrayList<Task> task = TaskList.getTasks();
        int counter = TaskList.getCounter();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Here " + isPlural + " the " + taskIfPlural + " in your list:\n";
        for (int i = 1; i <= counter; i++) {
            output += "" + i + "." + task.get(i - 1) + "\n";
        }

        return output;
    }

    public static String printDoneTask(Task task) {
        String output = "";
        output += "Nice! I've marked this task as done:\n";
        output += task + "\n";
        return output;
    }

    /**
     * Prints the task that was last added.
     * 
     * @param t       Last added task.
     * @param counter Remaining number of tasks in the list.
     */
    public static String printAddedTask(Task t) {
        String output = "";
        output += "Got it. I've added this task:\n" + t + "\n";
        int counter = TaskList.getCounter();
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Now you have " + counter + " " + taskIfPlural + " in the list.\n";
        return output;
    }

    /**
     * Prints the task that was last deleted.
     * 
     * @param task    Last deleted task.
     * @param counter Remaining number of tasks in the list.
     */
    public static String printDeleteTask(Task task) {
        String output = "";
        int counter = TaskList.getCounter();
        String taskIfPlural = counter <= 1 ? "task" : "tasks";
        output += "Noted. I've removed this task:\n";
        output += task + "\n";
        output += "Now you have " + counter + " " + taskIfPlural + " in the list.\n";
        return output;
    }

    public static String printFoundTask(List<Task> foundTasks) {
        String output = "";
        int counter = foundTasks.size();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        output += "Here " + isPlural + " the matching " + taskIfPlural + " in your list:\n";
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