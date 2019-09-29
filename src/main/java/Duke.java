import com.core.savedata.SaveFile;
import java.util.Scanner;
import java.util.NoSuchElementException;

import com.util.Printer;
import com.core.State;
import com.core.Response;

public class Duke {

    /**
     * Main for program.
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        State state = new State(SaveFile.loadTasks());
        try {
            while (!state.toExit) {
                String input = scanner.nextLine();
                for (Response r : Response.values()) {
                    if (r.call(input, state)) {
                        break;
                    }
                }
            }
        } catch (NoSuchElementException ignored) {
            System.out.print("");
        }
    }
}