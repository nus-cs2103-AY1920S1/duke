import java.util.Scanner;
import java.util.NoSuchElementException;

import com.util.Printer;
import com.core.State;
import com.core.Response;
import java.util.stream.Stream;

public class Duke {

    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
        // greet

        Scanner scanner = new Scanner(System.in);
        State state = new State();
        try {
            while (!state.toExit) {
                String input = scanner.nextLine();
                Stream.of(Response.values()).reduce(
                        false,
                        (matched, r) -> matched || r.call(input, state),
                        Boolean::logicalAnd);
            }
        } catch (NoSuchElementException ignored) {
        }
    }
}