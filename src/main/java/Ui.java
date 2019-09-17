import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {

    /**
     * prints a indented line.
     */
    public static String showLine() {
        return "    _______________________________________________";
    }

    /**
     * prints a welcome message.
     */
    public static String greet() {
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        output.append("Hello! I'm Duke\nWhat can I do for you?");
        output.append("\n");
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * prints a goodbye message.
     */
    public static String exit() {
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        output.append("Bye. Hope to see you again soon!");
        output.append("\n");
        output.append(showLine());
        output.append("\n");
        return output.toString();

    }

    /**
     * prints a string in a special format.
     * @param s String to be printed.
     */
    public static String echo(String s) {
        String[] arr = s.split("\n");
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        for (String str : arr) {
            output.append("     " + str);
            output.append("\n");
        }
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * Overloaded method that prints a response when a task is added.
     * @param t Task that is added.
     * @param x Current number of tasks in list.
     */
    public static String echo(Task t, int x) {
        if (x == 1) {
            return echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " task in the list.");
        } else {
            return echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " tasks in the list.");
        }
    }

    /**
     * prints the current list of tasks.
     * @param taskList the list of tasks to be printed.
     */

    public static String printList(TaskList taskList) {
        Task[] tasks = taskList.getTasks();
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        if (tasks[0] == null) {
            output.append("     There are no tasks in your list.");
            output.append("\n");
        } else {
            output.append("     Here are the tasks in your list:");
            output.append("\n");
            int taskIndex = 0;
            while (tasks[taskIndex] != null) {
                output.append("     " + (taskIndex + 1) + ". " + tasks[taskIndex]);
                output.append("\n");
                taskIndex++;
            }
        }
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * prints out response when a task is completed.
     * @param t the completed task.
     */
    public static String printDone(Task t) {
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        output.append("     Nice! I've marked this task as done:");
        output.append("\n");
        output.append("       " + t);
        output.append("\n");
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * prints out a response when a task is deleted.
     * @param t the deleted task.
     * @param numOfTasksLeft number of tasks left in the list.
     */
    public static String printDeleted(Task t, int numOfTasksLeft) {
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        output.append("     Noted. I've removed this task:");
        output.append("\n");
        output.append("       " + t);
        output.append("\n");
        if (numOfTasksLeft == 1) {
            output.append("     Now you have " + numOfTasksLeft + " task in the list.");
            output.append("\n");
        } else {
            output.append("     Now you have " + numOfTasksLeft + " tasks in the list.");
            output.append("\n");
        }
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * find and print any tasks containing the keyword.
     * @param keyword the word to find for.
     * @param taskList the list of tasks to look at.
     */
    public static String printFound(String keyword, TaskList taskList) {
        Task[] tasks = taskList.getTasks();
        ArrayList<Task> taskArrayList = new ArrayList<>(Arrays.asList(tasks));
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");

        StringBuilder matchedTasks = new StringBuilder();
        int counter = 1;
        int index = 0;
        while (tasks[index] != null && index < 100) {
            if (tasks[index].getDescription().contains(keyword)) {
                matchedTasks.append("     " + counter + ". " + tasks[index].toString());
                matchedTasks.append("\n");
                counter++;
            } else {}
            index++;
        }
        if (matchedTasks.toString().equals("")) {
            output.append("     There are no matching tasks in your list.");
            output.append("\n");
        } else {
            output.append("     Here are the matching tasks in your list:");
            output.append("\n");
        }
        output.append(matchedTasks);
        output.append("\n");

        output.append(showLine());
        output.append("\n");
        return output.toString();
    }

    /**
     * prints out an error response.
     * @param string the error to be printed.
     */
    public static String showError(String string) {
        StringBuilder output = new StringBuilder();
        output.append(showLine());
        output.append("\n");
        output.append("     " + string);
        output.append("\n");
        output.append(showLine());
        output.append("\n");
        return output.toString();
    }
}
