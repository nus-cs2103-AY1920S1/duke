import java.util.Scanner;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.ListCommand;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

//@@author Parcly-Taxel
class Duke {
    private static final TaskList tl = new TaskList();
    private static final Ui ui = new Ui();
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean hasExited = false;
        int i;
        Command c;
        
        ui.printWelcome();
        
        while (!hasExited) {
            // Read the command and data separately, together comprising a line
            String cmd = sc.next();
            String data = sc.nextLine().trim();
            
            Task t;

            try {
                switch (cmd) {
                    case "bye":
                        c = new ExitCommand();
                        c.execute(tl, ui);
                        hasExited = c.isExit();
                        break;
                    case "list":
                        c = new ListCommand();
                        c.execute(tl, ui);
                        break;
                    case "done":
                        i = Integer.parseInt(data);
                        c = new DoneCommand(i);
                        c.execute(tl, ui);
                        break;
                    case "delete":
                        i = Integer.parseInt(data);
                        c = new DeleteCommand(i);
                        c.execute(tl, ui);
                        break;
                    case "todo":
                        t = Todo.parse(data);
                        c = new AddCommand(t);
                        c.execute(tl, ui);
                        break;
                    case "event":
                        t = Event.parse(data);
                        c = new AddCommand(t);
                        c.execute(tl, ui);
                        break;
                    case "deadline":
                        t = Deadline.parse(data);
                        c = new AddCommand(t);
                        c.execute(tl, ui);
                        break;
                    default:
                        ui.printError("I'm sorry, but I don't know what that means :-(");
                        break;
                }
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
