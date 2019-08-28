import duke.MessageGenerator;
import duke.UI;

import java.util.Scanner;

public class Duke {

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;

    public Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        ui = new UI("files/tasks.text");
        isExit = false;
    }

    public void run() {
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

