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

    /** Handles user chat interaction */
    public static void chat() {
        // Create default hi/bye strings
        String logo = " ____        _        \n\t"
                    + "|  _ \\ _   _| | _____ \n\t"
                    + "| | | | | | | |/ / _ \\\n\t"
                    + "| |_| | |_| |   <  __/\n\t"
                    + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        String bye = "Bye mate. Catch you later!";

        Scanner sc = new Scanner(System.in);
        String command = "";

        reply(greeting); //say greetings

        // Begin chat interaction
        do {
            try {
                command = sc.nextLine();
                if (command.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (command.startsWith("done")) {
                    doneTask(command);
                } else if (command.startsWith("todo")) {
                    addTodo(command);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.startsWith("event")) {
                    addEvent(command);
                } else {
                    throw new UnknownCommandException("OOPS!!! Sorry mate, I don't geddit.");
                }
            } catch (DukeException e) {
                reply(e.getMessage());
            }
        } while (!command.equalsIgnoreCase("bye"));

        reply(bye); //say goodbye
    }

    /** Add a new to-do */
    private static void addTodo(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            //parse description from command string
            int commandLength = "todo ".length();
            String description = command.substring(commandLength).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a task cannot be empty mate.");
            }

            // add task and reply user
            Task task = new Todo(description);
            tasks.add(task);
            replyAddTask(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a todo with the right format: " +
                    "'todo [description]'");
        }
    }

    /** Add a new deadline */
    private static void addDeadline(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            int byLength = "/by".length();
            int commandLength = "deadline ".length();
            int byIndex = command.indexOf("/by"); // look for '/by' keyword from command

            // parse description from command
            String description = command.substring(commandLength, byIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a deadline cannot be empty mate.");
            }

            // parse by-date from command
            String by = command.substring(byIndex + byLength).trim();
            if (by.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The date of a deadline cannot be empty mate.");
            }

            // add task and reply user
            Task task = new Deadline(description, by);
            tasks.add(task);
            replyAddTask(task);

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a deadline with the right format: " +
                    "'deadline [description] /by [deadline date]'");
        }
    }

    /** Add a new event */
    private static void addEvent(String command) throws EmptyFieldException, InvalidCommandFormatException {
        try {
            int atLength = "/at".length();
            int commandLength = "event ".length();
            int atIndex = command.indexOf("/at"); // look for '/at' keyword from command

            // parse description from command
            String description = command.substring(commandLength, atIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of an event cannot be empty mate.");
            }

            // parse at-date from command
            String at = command.substring(atIndex + atLength).trim();
            if (at.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The start & end dates of an event cannot be empty mate.");
            }

            // add task and reply user
            Task task = new Event(description, at);
            tasks.add(task);
            replyAddTask(task);

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme an event with the right format: " +
                    "'event [description] /at [start & end date]'");
        }
    }

    /**
     * Prints out reply message for adding a task to the user.
     *
     * @param task Task to print out reply for.
     */
    private static void replyAddTask(Task task) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Got it mate. I've added this task:\n\t" + task + "\n\t");
        messageBuilder.append("Now you have " + tasks.size() + " tasks in the list mate.");
        reply(messageBuilder.toString());
    }

    /** Mark a task as done */
    private static void doneTask(String command) throws InvalidIndexException {
        try {
            StringBuilder messageBuilder = new StringBuilder();

            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);

            messageBuilder.append("Nice one mate! I've marked this task as done:\n\t");
            messageBuilder.append("  " + task);

            reply(messageBuilder.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /** Display list of tasks */
    private static void listTasks() {
        StringBuilder messageBuilder = new StringBuilder();

        if (tasks.size() > 0) {
            messageBuilder.append("Here are the tasks in your list:\n\t");
            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i+1) + ". " + tasks.get(i) + "\n\t");
            }
            messageBuilder.setLength(messageBuilder.length() - 2); // strip trailing \n\t
        } else {
            messageBuilder.append("No tasks in your list. Add some tasks and get to work mate!");
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
