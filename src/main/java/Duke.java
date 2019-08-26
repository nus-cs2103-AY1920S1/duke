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
        boolean isContinue = true;
        p.ui.welcome();
        while (isContinue) {
            String echo = s.nextLine();
            try {
                Verify.checkCommandValidity(echo, list, formatter);
                isContinue = p.parse(echo, list, formatter);
            } catch (DukeException e) {
            }
        }
        storage.writeFile(list);
        s.close();
    }
}
