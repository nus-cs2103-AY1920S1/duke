import java.util.Scanner;
import java.util.NoSuchElementException;

import com.util.Printer;
import com.core.State;
import com.core.Response;

public class Duke {
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
        // greet

        Scanner scanner = new Scanner(System.in);
        State state = new State();
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
        }
    }
}