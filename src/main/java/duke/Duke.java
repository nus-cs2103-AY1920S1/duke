package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.task.Parser;
import duke.task.Storage;
import duke.task.Ui;

public class Duke {
    private Storage storage;
    private Parser parser;

    public Duke() {
        this.storage = new Storage();
        this.parser = new Parser(this.storage);
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
