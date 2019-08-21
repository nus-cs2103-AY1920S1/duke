package duke.init;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the Duke chatbot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private static final int BORDER_LENGTH = 80;
    private static final ArrayList<Task> storedTasks = new ArrayList<>();

    /**
     * Runs the Duke chatbot.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHorizontalBorder();
        greet();
        printHorizontalBorder();
        System.out.println();
        String input = scanner.nextLine();
        String command = input.split(" ")[0];
        while (!command.equals("bye")) {
            printHorizontalBorder();
            process(input);
            printHorizontalBorder();
            System.out.println();
            input = scanner.nextLine();
        }
        printHorizontalBorder();
        sayBye();
        printHorizontalBorder();
        scanner.close();
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tWhat can I do for you?");
    }

    /**
     * Processes the specified input.
     * @param input The specified input.
     */
    private static void process(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "list":
                listStoredTasks();
                break;
            case "done":
                setTaskAsDone(input);
                break;
            case "delete":
                deleteStoredTask(input);
                break;
            case "todo":
                storeTodo(input);
                break;
            case "deadline":
                storeDeadline(input);
                break;
            case "event":
                storeEvent(input);
                break;
            default:
                System.out.println("\u2639 OOPS!!! I'm sorry, but I don't"
                        + " know what that means :-(");
                break;
        }
    }

    /**
     * Lists stored text.
     */
    private static void listStoredTasks() {
        if (storedTasks.size() == 0) {
            System.out.println("\tYou have 0 tasks in the list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < storedTasks.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, storedTasks.get(i));
            }
        }
    }

    /**
     * Deletes the stored task that corresponds to the specified input.
     * @param input The specified input.
     */
    private static void deleteStoredTask(String input) {
        try {
            String[] inputWords = input.split(" ");
            if (inputWords.length > 2) {
                throw new DukeException();
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            Task task = storedTasks.remove(taskNumber - 1);
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task);
            System.out.format("\tNow you have %d task(s) in the list.\n",
                    storedTasks.size());
        } catch (NumberFormatException
                | ArrayIndexOutOfBoundsException
                | DukeException e) {
            System.out.println("\tdelete command format: delete <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease enter a valid task number for the delete command.\n"
                    + "\tThe delete command will not work if there are 0 stored tasks.");
        }
    }

    /**
     * Stores the specified task.
     * @param task The specified task.
     */
    private static void storeTask(Task task) {
        storedTasks.add(task);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task);
        System.out.format("\tNow you have %d task(s) in the list.\n",
                storedTasks.size());
    }

    /**
     * Stores a todo task based on the specified input.
     * @param input The specified input.
     */
    private static void storeTodo(String input) {
        try {
            String description = input.substring(5);
            storeTask(new Todo(description));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\ttodo command format: todo <description>");
        }
    }

    /**
     * Stores a deadline task based on the specified input.
     * @param input The specified input.
     */
    private static void storeDeadline(String input) {
        try {
            String description = input.substring(9);
            String[] taskInformation = description.split(" /by ");
            storeTask(new Deadline(taskInformation[0], taskInformation[1]));
    } catch (ArrayIndexOutOfBoundsException
                | StringIndexOutOfBoundsException e) {
            System.out.println("\tdeadline command format: deadline <description> /by <date>");
        }
    }

    /**
     * Stores an event task based on the specified input.
     * @param input The specified input.
     */
    private static void storeEvent(String input) {
        try {
            String description = input.substring(6);
            String[] taskInformation = description.split(" /at ");
            storeTask(new Event(taskInformation[0], taskInformation[1]));
        } catch (ArrayIndexOutOfBoundsException
                | StringIndexOutOfBoundsException e) {
            System.out.println("\tevent command format: event <description> /at <dateAndTime>");
        }
    }

    /**
     * Says bye to the user.
     */
    private static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Sets a task as done based on the specified input.
     * @param input The specified input.
     */
    private static void setTaskAsDone(String input) {
        try {
            String[] inputWords = input.split(" ");
            if (inputWords.length > 2) {
                throw new DukeException();
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            Task task = storedTasks.get(taskNumber - 1);
            task.setAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException
                | ArrayIndexOutOfBoundsException
                | DukeException e) {
            System.out.println("\tdone command format: done <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tPlease enter a valid task number for the done command.\n"
                    + "\tThe done command will not work if there are 0 stored tasks.");
        }
    }

    /**
     * Prints a horizontal border.
     */
    private static void printHorizontalBorder() {
        StringBuilder border = new StringBuilder("\t");
        for (int i = 0; i < BORDER_LENGTH; i++) {
            border.append('_');
        }
        System.out.println(border);
    }

}
