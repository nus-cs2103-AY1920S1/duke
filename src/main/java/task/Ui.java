package task;

import java.util.ArrayList;

public class Ui {

    public static void startOfInteractions() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
    }

    public static void endOfInteractions() {
        System.out.println("Bye. Hope to see you again soon!");
    }

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

    public static void printAddedTask(Task t, int counter) {
        System.out.println("Got it. I've added this task:\n" + t);
        counter++;
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
    }

    public static void printDeleteTask(Task task, int counter) {
        String taskIfPlural = counter <= 1 ? "task" : "tasks";
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
    }

    public static void printException(Exception e) {
        if (e instanceof DukeException) {
            System.err.println(e);
        } else {
            System.err.println(e.getMessage());
        }
    }
}