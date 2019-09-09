import Exceptions.MissingInputException;
import UI.MessageGenerator;
import UI.UI;

import java.io.IOException;
import java.util.Scanner;

public class Duke{

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;

    /**
     * Reads file containing a list of tasks.
     * Updates and changes this file according to user input.
     */
    public Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        ui = new UI("files/tasks.txt");
        isExit = false;
    }

    private void run() throws MissingInputException {
        greeter.greet();
        ui.processFile();
        while (!isExit) {
            ui.processInput();
            isExit = ui.isExit();
        }
        greeter.bye();
    }

    String getResponse(String input) throws IOException {
        return ui.processInput(input);
    }

    public static void main(String[] args) throws MissingInputException {
        new Duke().run();
    }


}
