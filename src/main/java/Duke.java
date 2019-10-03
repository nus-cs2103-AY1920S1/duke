import exceptions.InvalidInputException;
import exceptions.MissingInputException;
import ui.MessageGenerator;
import ui.UI;

import java.util.Scanner;

public class Duke {

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;
    private static String FILE_PATH = "files/tasks.txt";

    /**
     * Reads file containing a list of tasks.
     * Updates and changes this file according to user input.
     */
    public Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        ui = new UI(FILE_PATH);
        isExit = false;
    }

    void processFile() throws InvalidInputException, MissingInputException {
        ui.processFile();
    }

    String greet() {
        return greeter.greet();
    }

    String getResponse(String input) {
        return ui.processInput(input);
    }

}
