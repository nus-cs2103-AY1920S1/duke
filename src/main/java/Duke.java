import task.Task;
import tool.Parser;
import tool.Storage;
import tool.TaskList;
import tool.Ui;

import java.util.*;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("src/main/java/data/duke.txt");
        Ui ui = new Ui();
        ui.hi();
        TaskList commands = new TaskList(storage.load(new ArrayList<Task>())); //TODO
        Parser p = new Parser(commands, storage, ui);
        while(scanner.hasNextLine()) {
            String x = scanner.nextLine();
            p.parse(x);
            if (x.equals("bye")) {
                scanner.close();
                break;
            }

        }
    }
}
