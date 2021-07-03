import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    /**
     * Greets user.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }



    /**
     * Prints errors raised due to wrong input.
     *
     * @param de DukeException to be printed
     */
    public static String printError(DukeException de) {
        return (de.getMessage());
    }

    /**
     * Prints task which was completed.
     *
     * @param t task which was completed
     */
    public static String completedTask(Task t) {
        return ("Nice! I've marked this task as done: \n" + t);
    }

    /**
     * Prints task which was deleted.
     *
     * @param t task which was deleted
     * @param todos remaining list used to calculate tasks left
     */
    public static String deletedTask(Task t, List<Task> todos) {
        return ("Noted. I've removed this task: \n" + t
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    /**
     * Prints entire list of tasks.
     *
     * @param todos list with all the tasks
     */
    public static String printList(List<Task> todos) {
        String out = "Here are the tasks in your list:";
        for (int i = 0; i < todos.size(); i++) {
             out += "\n" + ((i + 1) + ". " + todos.get(i));
        }
        return out;
    }

    /**
     * Finds tasks with same name.
     *
     * @param todos list with all the tasks
     * @param name being queried
     */
    public static String findTasks(List<Task> todos, String name) {
        String out = "Here are the matching tasks in your list:";
        for (int i = 0; i < todos.size(); i++) {
            Task curr = todos.get(i);
            if (curr.getDescription().contains(name)) {
                out += "\n" + ((i + 1) + ". " + curr);
            }
        }
        return out;
    }
}
