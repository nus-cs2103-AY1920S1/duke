package duke.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

//

/**
 * Implements the Duke chatbot.
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private static final int BORDER_LENGTH = 80;
    private static final ArrayList<Task> STORED_TASKS = new ArrayList<>();
    private static final File DATA_FILE = new File("../../../../../data/duke.txt");
    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    /**
     * Runs the Duke chatbot.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {

        // Read data from file into arraylist
        readDataFromHardDisk();

        // Greet user
        printHorizontalBorder();
        greet();
        printHorizontalBorder();
        System.out.println();

        // Process input
        String input = INPUT_SCANNER.nextLine();
        String command = input.split(" ")[0];
        while (!command.equals("bye")) {
            printHorizontalBorder();
            process(input);
            printHorizontalBorder();
            System.out.println();
            input = INPUT_SCANNER.nextLine();
            command = input.split(" ")[0];
        }

        // Say bye to user
        printHorizontalBorder();
        sayBye();
        printHorizontalBorder();
        INPUT_SCANNER.close();

        // Save data to file
        saveDataToHardDisk();
    }

    /**
     * Reads data from the hard disk into the list of stored tasks
     */
    private static void readDataFromHardDisk() {
        try {
            Scanner fileScanner = new Scanner(DATA_FILE);
            while (fileScanner.hasNextLine()) {
                String[] taskInformation = fileScanner.nextLine().split(" \\| ");
                String taskType = taskInformation[0];
                boolean isDone = taskInformation[1].equals("1") ? true : false;
                String description = taskInformation[2];
                Task task;
                if (taskType.equals("T")) {
                    task = new Todo(description, isDone);
                } else if (taskType.equals("D")) {
                    task = new Deadline(description, isDone, taskInformation[3]);
                } else {
                    task = new Event(description, isDone, taskInformation[3]);
                }
                STORED_TASKS.add(task);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The given data file could not be found");
        }
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
            System.out.println("\t\u2639 OOPS!!! I'm sorry, but I don't"
                    + " know what that means :-(");
            break;
        }
    }

    /**
     * Lists stored text.
     */
    private static void listStoredTasks() {
        if (STORED_TASKS.size() == 0) {
            System.out.println("\tYou have 0 tasks in the list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < STORED_TASKS.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, STORED_TASKS.get(i));
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
            Task task = STORED_TASKS.remove(taskNumber - 1);
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task);
            System.out.format("\tNow you have %d task(s) in the list.\n",
                    STORED_TASKS.size());
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
        STORED_TASKS.add(task);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task);
        System.out.format("\tNow you have %d task(s) in the list.\n",
                STORED_TASKS.size());
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
     * Saves data from the stored tasks list into the hard disk.
     */
    private static void saveDataToHardDisk() {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            for (int i = 0; i < STORED_TASKS.size(); i++) {
                String dataLine;
                Task task = STORED_TASKS.get(i);
                String status = task.isDone() ? "1" : "0";
                if (task.getType().equals("todo")) {
                    dataLine = String.format("T | %s | %s\n", status, task.getDescription());
                } else if (task.getType().equals("deadline")) {
                    dataLine = String.format("D | %s | %s | %s\n", status,
                            task.getDescription(), ((Deadline) task).getDate());
                } else {
                    dataLine = String.format("E | %s | %s | %s\n", status,
                            task.getDescription(), ((Event) task).getDateAndTime());
                }
                fileWriter.write(dataLine);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Data could not be saved.");
        }
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
            Task task = STORED_TASKS.get(taskNumber - 1);
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
