import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Storage.
 * Remove line breaks from duke.txt. Done
 * Add constructor for duke for file path.
 * Initialise list with file path or new array list.
 * Abstract out as storage.load and constructor for TaskList. --> Requires TaskList to be done...
 * Handle wrong file path or empty file path.
 * Saving tasks (storage.save(TaskList, filePath))
 * <p>
 * TaskList.
 * Abstract out TaskList.
 * Add constructor which parses storage Stores it as a list.
 * Internally Contains all methods to manipulate list.
 * <p>
 * ui - Deals with user interaction (readLine, showWelcome etc)
 * <p>
 * Parser - read entire line, and output Hashtable of details
 * Parser.parse(line) returns hashtable of details
 * Unit test parser
 * Refactor to take in string...
 */

public class Duke {
    private TaskList toDoList;
    private final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";
    private final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Driver method.
     */
    public void run() {
        String[] arr;
        Hashtable<String, String> toDo;
        String task, date;
        SimpleDateFormat readFormat;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        toDoList = new TaskList();
        while (!exit) {
            try {
                toDo = Parser.parse(sc.nextLine());
                switch (toDo.get("command")) {
                case "todo":
                    System.out.println(toDoList.addToDo(toDo.get("task")));
                    break;
                case "deadline":
                    System.out.println(toDoList.addDeadline(toDo.get("task"), toDo.get("date"), "dd/MM/yyyy HHmm"));
                    break;
                case "event":
                    System.out.println(toDoList.addEvent(toDo.get("task"), toDo.get("date"), "dd/MM/yyyy HHmm"));
                    break;
                case "list":
                    System.out.println(toDoList.list());
                    break;
                case "done":
                    System.out.println(toDoList.done(Integer.parseInt(toDo.get("index"))));
                    break;
                case "delete":
                    System.out.println(toDoList.delete(Integer.parseInt(toDo.get("index"))));
                    break;
                case "save":
                    toDoList.save(toDo.get("path"));
                    break;
                case "bye":
                    exit = true;
                    break;
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
