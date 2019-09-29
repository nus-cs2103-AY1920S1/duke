package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import duke.util.Parser;

import java.io.File;
import java.io.IOException;

/**
 * Driver class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Initializes the duke chatbot with a file path for storage purpose.
     */
    public Duke() throws IOException {
        File new_file = new File("data/duke.txt");
        new_file.getParentFile().mkdirs();
        new_file.createNewFile();
        this.storage = new Storage(new_file);
        this.ui = new UI();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Receive input from JavaFX interface and process it and return output for user.
     * @param input Input from JavaFX interface
     * @return The response of Duke given the command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseUserInput(input);
            System.out.println(c.getClass().getSimpleName());
            String result = c.execute(tasks, ui, storage);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

