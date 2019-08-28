import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private TodoList tasks;
    private Storage storage;
    private Ui ui;

    private Duke(String s) {
        storage = new Storage(s);
        ui = new Ui();
        tasks = storage.load();
    }

    private void run() {
        this.ui.welcome();

        while (true) {
            try {
                Command command = this.ui.getUserInput();
                String response = command.run(tasks, storage);
                this.ui.printResponse(response);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/home/dingyuchen/cs2103/duke/src/main/data/duke.ser");
        duke.run();
    }

}
