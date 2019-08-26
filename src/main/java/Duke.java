import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * main class, calls functions and other classes as needed
 */
public class Duke {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TaskList list = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        Parser p = new Parser();
        Storage storage = new Storage();
        storage.readFile(list);
        boolean keepGoing = true;
        p.ui.welcome();
        while (keepGoing) {
            String echo = s.nextLine();
            try {
                Verify.validCommand(echo, list, formatter);
                keepGoing = p.parse(echo, list, formatter);
            } catch (DukeException e) {
            }
        }
        storage.writeFile(list);
        s.close();
    }
}
