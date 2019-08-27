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
 * Command class
 * Parse to return command
 * c.execute(tasks, ui, storage)
 * c.isExit();
 *
 * Sort directory out...
 */

// TODO: update run method, ui read line
// TODO: Move exceptions to execute
public class Duke {
    private TaskList tasks;
    private UI ui;
    private final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";

    /**
     * Driver method.
     */
    public void run() {
        Command command;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        tasks = new TaskList();
        ui = new UI();

        ui.showWelcomeMessage();

        while (!exit) {
            try {
                command = Parser.parse(sc.nextLine());
                command.execute(tasks, ui);
                exit = command.isExit();
//                switch (comm.get("command")) {
//                case "todo":
//                    System.out.println(tasks.addToDo(toDo.get("task")));
//                    break;
//                case "deadline":
//                    System.out.println(tasks.addDeadline(toDo.get("task"), toDo.get("date"), "dd/MM/yyyy HHmm"));
//                    break;
//                case "event":
//                    System.out.println(tasks.addEvent(toDo.get("task"), toDo.get("date"), "dd/MM/yyyy HHmm"));
//                    break;
//                case "list":
//                    System.out.println(tasks.list());
//                    break;
//                case "done":
//                    System.out.println(tasks.done(Integer.parseInt(toDo.get("index"))));
//                    break;
//                case "delete":
//                    System.out.println(tasks.delete(Integer.parseInt(toDo.get("index"))));
//                    break;
//                case "save":
//                    tasks.save(toDo.get("path"));
//                    break;
//                case "bye":
//                    exit = true;
//                    break;
//                }
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
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
