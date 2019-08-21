package duke.init;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the Duke chatbot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private static final int BORDER_LENGTH = 60;
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
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        Task task;
        String[] taskInformation;
        switch(command) {
            case "list":
                listStoredTasks();
                break;
            case "done":
                int taskNumber = Integer.parseInt(inputWords[1]);
                setAsDone(taskNumber);
                break;
            case "todo":
                task = new Todo(input.substring(5));
                storeTask(task);
                break;
            case "deadline":
                taskInformation = input.substring(9).split(" /by ");
                task = new Deadline(taskInformation[0], taskInformation[1]);
                storeTask(task);
                break;
            case "event":
                taskInformation = input.substring(6).split(" /at ");
                task = new Event(taskInformation[0], taskInformation[1]);
                storeTask(task);
                break;
            default:
                task = new Task(input);
                storeTask(task);
                break;
        }
    }

    /**
     * Lists stored text.
     */
    private static void listStoredTasks() {
        if (storedTasks.size() == 0) {
            System.out.println("\tNo stored text.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < storedTasks.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, storedTasks.get(i));
            }
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
     * Says bye to the user.
     */
    private static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Sets the task with the specified task number as done.
     * @param taskNumber The specified task number.
     */
    private static void setAsDone(int taskNumber) {
        Task task = storedTasks.get(taskNumber - 1);
        task.setAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task);
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
