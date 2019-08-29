import duke.MessageGenerator;
import duke.UI;

import java.util.Scanner;

public class Duke {

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;

    /**
     * Reads file containing a list of tasks.
     * Updates and changes this file according to user input.
     */
    private Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        ui = new UI("files/tasks.text");
        isExit = false;
    }

    private void run() {
        greeter.greet();
        ui.processFile();
        while (!isExit) {
            ui.processInput();
            isExit = ui.isExit();
        }
        greeter.bye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

