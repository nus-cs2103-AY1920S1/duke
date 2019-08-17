import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    
    //@@author Parcly-Taxel
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
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
                    ui.printTaskList(tasks);
                    continue;
                case "done":
                    try {
                        Task currTask = tasks.get(Integer.parseInt(data) - 1);
                        currTask.markDone();
                        ui.printDoneTask(currTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printMessage("\u2639 OOPS!!! Task index must be " +
                                "between 1 and " + tasks.size() + ".");
                    }
                    continue;
                case "delete":
                    try {
                        Task remTask = tasks.remove(Integer.parseInt(data) - 1);
                        ui.printRemovedTask(remTask);
                        ui.printNumTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printMessage("\u2639 OOPS!!! Task index must be " +
                                "between 1 and " + tasks.size() + ".");
                    }
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
                tasks.add(t);
                ui.printAddedTask(t);
                ui.printNumTasks(tasks);
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
