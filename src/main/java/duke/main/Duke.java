package duke.main;

import duke.command.Command;
import duke.exception.SaveFileWrongFormatDukeException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.FileNotFoundException;

/**
 * Duke is an application that keeps track of your
 * to-do tasks, deadlines and events.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialises a new Duke by loading the save file from a filePath.
     *
     * @param filePath the file path that storage loads from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.load(parser);
        } catch (FileNotFoundException | SaveFileWrongFormatDukeException e) {
            tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    /**
     * This is where the Duke application starts to run.
     */
    public String getResponse(String input) {
        Command commandFromInput = parser.parse(input);
        String returnString = commandFromInput.execute(tasks);
        storage.saveDuke(tasks.saveInfo());
        return returnString;
    }
}
