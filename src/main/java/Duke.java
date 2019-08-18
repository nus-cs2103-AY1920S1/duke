import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke Chat Class.
 *
 * A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        chat();
    }

    /** Handles user chat input */
    public static void chat() {
        // Create default hi/bye strings
        String logo = " ____        _        \n\t"
                + "|  _ \\ _   _| | _____ \n\t"
                + "| | | | | | | |/ / _ \\\n\t"
                + "| |_| | |_| |   <  __/\n\t"
                + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "Hello! I'm Duke\n\tWhat can I do for you?";
        String bye = "Bye. Hope to see you again soon!";

        reply(greeting);

        // Begin chat interaction
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("done ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                doneTask(taskIndex);
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }

        reply(bye);
    }

    /** Add a new task */
    private static void addTask(String input) {
        tasks.add(new Task(input));
        reply("added: " + input);
    }

    /** Mark a task as done */
    private static void doneTask(int taskIndex) {
        StringBuilder messageBuilder = new StringBuilder("Nice! I've marked this task as done:\n\t");

        Task task = tasks.get(taskIndex-1);
        task.setDone(true);

        messageBuilder.append("  " + task);

        reply(messageBuilder.toString());
    }

    /** Display list of tasks */
    private static void listTasks() {
        StringBuilder messageBuilder = new StringBuilder("Here are the tasks in your list:\n\t");

        for (int i = 0; i < tasks.size(); i++) {
            messageBuilder.append((i+1) + ". " + tasks.get(i) + "\n\t");
        }

        reply(messageBuilder.toString());
    }

    /**
     * Prints out reply message to the user.
     *
     * @param message String containing reply message.
     */
    public static void reply(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
}
