import command.Command;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    public String getStartMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }
}