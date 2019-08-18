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
            } else if (input.startsWith("todo ")) {
                addTodo(input);
            } else if (input.startsWith("deadline ")){
                addDeadline(input);
            } else if (input.startsWith("event ")){
                addEvent(input);
            }
            input = sc.nextLine();
        }

        reply(bye);
    }

    /** Add a new to-do */
    private static void addTodo(String input) {
        String description = input.substring(5); //get description

        Task task = new Todo(description);
        tasks.add(task);

        replyAddTask(task);
    }

    /** Add a new deadline */
    private static void addDeadline(String input) {
        String description = input.substring(9, input.indexOf("/by")); //get description
        String by = input.substring(input.indexOf("/by") + 4); //get by

        Task task = new Deadline(description, by);
        tasks.add(task);

        replyAddTask(task);
    }

    /** Add a new event */
    private static void addEvent(String input) {
        String description = input.substring(6, input.indexOf("/at")); //get description
        String at = input.substring(input.indexOf("/at") + 4); //get at

        Task task = new Event(description, at);
        tasks.add(task);

        replyAddTask(task);
    }

    /**
     * Prints out reply message for adding a task to the user.
     *
     * @param task Task to print out reply for.
     */
    private static void replyAddTask(Task task) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Got it. I've added this task:: " + task);
        messageBuilder.append("Now you have " + tasks.size() + " tasks in the list.");
        reply(messageBuilder.toString());
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
        StringBuilder messageBuilder = new StringBuilder();

        if (tasks.size() > 0) {
            messageBuilder.append("Here are the tasks in your list:\n\t");

            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i+1) + ". " + tasks.get(i) + "\n\t");
            }
        } else {
            messageBuilder.append("No tasks in your list. Add some tasks now!\n\t");
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
