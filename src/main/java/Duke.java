import java.util.Scanner;

//@@author Parcly-Taxel
public class Duke {
    private static TaskList tl = new TaskList();
    private static Ui ui = new Ui();
        
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
            Task t = null;
            
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
