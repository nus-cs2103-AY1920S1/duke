import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) {

        // Scanner object
        Scanner sc = new Scanner(System.in);

        // Create a Ui & show welcome message
        Ui ui = new Ui();
        ui.showWelcome();

        // Create file under Storage
        Storage s = new Storage("data/taskList.txt");

        // Load taskList from Storage
        try {
            TaskList t = new TaskList(s);
            t.printTasks();

            while (true) {
                String command = sc.next();

                if (command.equals("bye")) {
                    Parser p = new Parser(command, "");
                    p.executeOnly(t);
                    break;
                } else if (command.equals("list") || command.equals("deadline") || command.equals("todo")
                        || command.equals("event") || command.equals("done") || command.equals("delete")) {
                    String description = sc.nextLine().stripLeading();
                    Parser p = new Parser(command, description);
                    p.executeAndSave(t, s);
                } else {
                    throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Input / Output Error!");
        }

        sc.close();
    }
}