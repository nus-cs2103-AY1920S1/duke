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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\nHere are the current tasks in your list:");
        ArrayList<Task> taskList = storage.load(new ArrayList<Task>());
        Parser p = new Parser(taskList, storage);
        while(scanner.hasNextLine()) {
            String x = scanner.nextLine();
            p.parse(x);
            if (x.equals("bye")) {
                scanner.close();
                break;
            }

        }
        scanner.close();
    }
}
