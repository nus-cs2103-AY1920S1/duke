package task;

import java.util.ArrayList;

public class Ui {

    public static void startOfInteractions() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
    }

    public static void endOfInteractions() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks in taskList.
     * 
     * @param taskList Contains the list of tasks and number of tasks.
     */
    public static void printList(TaskList taskList) {
        ArrayList<Task> task = taskList.getTasks();
        int counter = taskList.getCounter();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Here " + isPlural + " the " + taskIfPlural + " in your list:");
        for (int i = 1; i <= counter; i++) {
            System.out.println(i + "." + task.get(i - 1));
        }
    }

    public static void printDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the task that was last added.
     * 
     * @param t       Last added task.
     * @param counter Remaining number of tasks in the list.
     */
    public static void printAddedTask(Task t, int counter) {
        System.out.println("Got it. I've added this task:\n" + t);
        counter++;
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
    }

    /**
     * Prints the task that was last deleted.
     * 
     * @param task    Last deleted task.
     * @param counter Remaining number of tasks in the list.
     */
    public static void printDeleteTask(Task task, int counter) {
        String taskIfPlural = counter <= 1 ? "task" : "tasks";
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
    }

    public static void printFoundTask(TaskList taskList) {
        ArrayList<Task> task = taskList.getTasks();
        int counter = taskList.getCounter();
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Here " + isPlural + " the matching " + taskIfPlural + " in your list:");
        for (int i = 1; i <= counter; i++) {
            System.out.println(i + "." + task.get(i - 1));
        }
    }

    /**
     * Prints the customized DukeException message or general exception message.
     * 
     * @param e Exception to be printed.
     */
    public static void printException(Exception e) {
        if (e instanceof DukeException) {
            System.err.println(e);
        } else {
            System.err.println(e.getMessage());
        }
    }
}