package duke.init;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

/**
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
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            printHorizontalBorder();
            process(command);
            printHorizontalBorder();
            System.out.println();
            command = scanner.nextLine();
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
     * Echos the specified command.
     * @param command The specified command.
     */
    private static void echo(String command) {
        System.out.println("\t" + command);
    }

    /**
     * Processes the specified command.
     * @param command The specified command.
     */
    private static void process(String command) {
        if (command.equals("list")) {
            listStoredTasks();
        } else if (command.length() > 4
                && command.substring(0, 4).equals("done")) {
            int taskNumber = Integer.parseInt(command.split(" ")[1]);
            setAsDone(taskNumber);
        } else {
            storeTask(command);
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
     * Stores a task with the specified description.
     * @param description The specified task.
     */
    private static void storeTask(String description) {
        System.out.println("\tadded: " + description);
        storedTasks.add(new Task(description));
    }

    /**
     * Says bye to the user.
     */
    private static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Sets the task with the specified number as done.
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
