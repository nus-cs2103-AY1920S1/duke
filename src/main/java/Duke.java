import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static TaskList tl = new TaskList();
    private static Ui ui = new Ui();
    
    //@@author Parcly-Taxel
    /**
     * Marks the task at one-based position i of tl as done.
     * Handles the error itself if this cannot be met.
     */
    public static void markTaskDone(int i) {
        try {
            Task doneTask = tl.markDone(i);
            ui.printDoneTask(doneTask);
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
    
    /**
     * Removes the task at one-based position i of tl.
     * Handles the error itself if this cannot be met.
     */
    public static void deleteTask(int i) {
        try {
            Task remTask = tl.removeTask(i);
            ui.printRemovedTask(remTask);
            ui.printNumTasks(tl);
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        
        ui.printWelcome();
        
        while (true) {
            // Read the command and data separately, together comprising a line
            String cmd = sc.next();
            String data = sc.nextLine().trim();
            
            switch (cmd) {
                case "bye":
                    ui.printGoodbye();
                    System.exit(0);
                case "list":
                    ui.printTaskList(tl);
                    continue;
                case "done":
                    i = Integer.parseInt(data);
                    markTaskDone(i);
                    continue;
                case "delete":
                    i = Integer.parseInt(data);
                    deleteTask(i);
                    continue;
                default:
                    break;
            }
            
            try {
                Task t = null;
                switch (cmd) {
                    case "todo":
                        t = Todo.parse(data);
                        break;
                    case "event":
                        t = Event.parse(data);
                        break;
                    case "deadline":
                        t = Deadline.parse(data);
                        break;
                    default:
                        throw new IllegalArgumentException("\u2639 OOPS!!! " +
                                "I'm sorry, but I don't know what that means :-(");
                }
                tl.addTask(t);
                ui.printAddedTask(t);
                ui.printNumTasks(tl);
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
