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
 * <p>
 * ui - Deals with user interaction (readToDo, showWelcome, showBye etc)
 */

public class Duke {
    private TaskList toDoList;
    private UI ui;
    private final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";

    /**
     * Driver method.
     */
    public void run() {
        Hashtable<String, String> toDo;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        toDoList = new TaskList();
        ui = new UI();

        ui.showWelcomeMessage();

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
                ui.showMessage(e.getMessage());
            }
        }
        ui.showByeMessage();
        sc.close();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        // Duke d = new Duke("Data/duke.txt");
        d.run();
    }
}
