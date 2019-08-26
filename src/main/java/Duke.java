import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Storage.
 * Remove line breaks from duke.txt. Done
 * Add constructor for duke for file path.
 * Initialise list with file path or new array list.
 * Abstract out as storage.load and constructor for TaskList. --> Requires TaskList to be done...
 * Handle wrong file path or empty file path.
 * Saving tasks (storage.save(TaskList, filePath))
 * 
 * TaskList.
 * Abstract out TaskList.
 * Add constructor which parses storage Stores it as a list.
 * Internally Contains all methods to manipulate list.
 * 
 * ui - Deals with user interaction (readLine, showWelcome etc)
 * 
 * Parser - read entire line, and output hashtable of details
 */

public class Duke {
    private TaskList toDoList;
    private final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";
    private final String exitMessage = "Bye. Hope to see you again soon!";
    private final String illegalArgumentMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "____________________________________________________________";

    /**
     * Driver method.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String[] arr;
        String task, date;
        SimpleDateFormat readFormat;
        boolean exit = false;
        toDoList = new TaskList();
        while (!exit) {
            try {
                String command = sc.next();
                switch (command) {
                case "todo":
                    task = sc.nextLine().trim();
                    if (!task.isEmpty()) {
                        toDoList.addToDo(task);
                    } else {
                        throw new IllegalArgumentException(emptyToDoErrorMessage);
                    }
                    break;
                case "deadline":
                    arr = sc.nextLine().split("/by");
                    task = arr[0].trim();
                    date = arr[1].trim();
                    readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    toDoList.addDeadline(task, readFormat.parse(date));
                    break;
                case "event":
                    arr = sc.nextLine().split("/at");
                    task = arr[0].trim();
                    date = arr[1].trim();
                    readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    toDoList.addEvent(task, readFormat.parse(date));
                    break;
                case "list":
                    System.out.println(toDoList.list());
                    break;
                case "done":
                    System.out.println(toDoList.done(sc.nextInt()));
                    break;
                case "delete":
                    System.out.println(toDoList.delete(sc.nextInt()));
                    break;
                case "save":
                    toDoList.save("./Data/duke.txt");
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    throw new IllegalArgumentException(illegalArgumentMessage);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println(exitMessage);
        System.out.println("____________________________________________________________");
        sc.close();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke d = new Duke();
        // Duke d = new Duke("Data/duke.txt");
        d.run();
    }
}
