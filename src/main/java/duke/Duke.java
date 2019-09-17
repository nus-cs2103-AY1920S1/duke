package duke;

import duke.command.Command;
import duke.component.DukeDatabase;
import duke.component.Parser;
import duke.component.TaskList;
import duke.exception.DukeException;

/**
 * Encapsulates a product named duke.Duke, a personal assistant chat bot that helps a person to keep track of various
 * tasks to do. It can add, delete, and list task entries and mark them as done.
 */
public class Duke {
    private Parser parser;
    private DukeDatabase database;
    private TaskList tasksList;

    /**
     * Creates a Duke chat bot.
     */
    public Duke() {
        // Initialise the essential components of duke bot.
        parser = new Parser();
        database = DukeDatabase.getDukeDatabaseInstance();
        tasksList = database.getAllTasks();
    }

    /**
     * Returns the response relevant to the input after processing it.
     * @param input input given by user.
     * @return response relevant to the input.
     */
    public String getResponse(String input) {
        String response = "";

        try {
            Command command = parser.parse(input);
            response = command.execute(database, tasksList);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Terminates duke and do necessary cleaning tasks such as update the data file of the program.
     */
    public void terminate() {
        database.update(tasksList);
    }
}
