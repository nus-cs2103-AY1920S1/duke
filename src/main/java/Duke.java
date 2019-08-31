import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
