import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws FileNotFoundException, DukeException, IOException {

        // Scanner object
        Scanner sc = new Scanner(System.in);
        boolean printed = false;

        // Create a Ui & show welcome message
        Ui ui = new Ui();
        ui.showWelcome();

        // Create file under Storage
        Storage s = new Storage("data/taskList.txt");

        while (true) {
            try {
                TaskList t = new TaskList(s);

                // If previous list has been loaded
                if (printed) {
                    String command = sc.next();

                    if (command.equals("bye")) {

                        // Create Parser & Execute
                        Parser p = new Parser(command, "");
                        p.executeAndSave(t, s);
                        break;

                    } else if (command.equals("list") || command.equals("deadline") || command.equals("todo")
                            || command.equals("event") || command.equals("done") || command.equals("delete")
                            || command.equals("find")) {

                        String description = sc.nextLine().stripLeading();
                        Parser p = new Parser(command, description);
                        p.executeAndSave(t, s);

                    } else {
                        throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
                    }
                } else {
                    // If previous list not loaded, print Tasks
                    t.printTasks();
                    printed = true;
                }
            } catch (DukeException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + e.getMessage());
                System.out.println("\t____________________________________________________________\n");
            } catch (IOException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + e.getMessage());
                System.out.println("\t____________________________________________________________\n");
            }
        }
        sc.close();
    }
}