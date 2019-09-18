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
     * @param filePath The file path that storage loads from.
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
     * Returns a string of the executed command.
     *
     * @param c The command that is executed.
     * @return A string describing the effects of the executed command.
     */
    public String getResponse(Command c) {
        String returnString = c.execute(tasks);
        return returnString;
    }

    /**
     * Parses the input into a command.
     *
     * @param input The string that is parsed.
     * @return A command parsed from the string.
     */
    public Command parseInput(String input) {
        Command commandFromInput = parser.parse(input);
        return commandFromInput;
    }

    /**
     * Saves the state of tasks in storage.
     */
    public void dukeSave() {
        storage.saveDuke(tasks.saveInfo());
    }
}
