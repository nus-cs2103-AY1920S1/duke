import java.io.FileNotFoundException;
import java.io.IOException;

import task.Parser;
import task.Storage;
import task.Ui;

public class Duke {
    private Storage storage;

    public Duke() {
        storage = new Storage();
    }

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        try {
            storage.readData();
        } catch (FileNotFoundException e) {
            Ui.printException(e);
        }

        Ui.startOfInteractions();

        Ui.readUserInput();

        Ui.endOfInteractions();

        try {
            storage.writeData();
        } catch (IOException e) {
            Ui.printException(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return Parser.parse(input);
    }

    protected String startOfShow() {
        try {
            storage.readData();
            return Ui.startOfInteractions();
        } catch (FileNotFoundException e) {
            return Ui.printException(e);
        }
    }
}
