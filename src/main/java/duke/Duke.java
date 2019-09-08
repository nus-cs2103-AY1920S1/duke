package duke;

import duke.command.Command;
import duke.io.BufferedStringOutput;
import duke.task.TaskList;
import duke.util.PreParser;

import java.io.IOException;

/**
 * Main application class.
 */
public class Duke {
    public final String DEFAULT_FILEPATH = "./data/tasks.txt";
    public final String LOADING_ERROR_MESSAGE = "Couldn't load tasks from disk."
            + "\nYour changes this session may not be saved!";
    public final String WELCOME_MESSAGE = "Hello! I'm Duke."
            + "\nWhat can I do for you?";

    private PreParser preParser;
    private TaskList tasks;
    private Storage storage;
    private BufferedStringOutput bufferedStringOutput;
    private boolean wasLoadingError = false;

    /**
     * Constructs a Duke object with the data file residing in the default path.
     */
    public Duke() {
        preParser = new PreParser();
        storage = new Storage(DEFAULT_FILEPATH);
        bufferedStringOutput = new BufferedStringOutput();
        try {
            tasks = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            wasLoadingError = true;
            tasks = new TaskList();
        }
    }

    /**
     * Generates Duke's response to user input.
     */
    public String getResponse(String input) {
        try {
            Command command = preParser.parse(input);
            command.execute(tasks, bufferedStringOutput, storage);
        } catch (DukeException e) {
            bufferedStringOutput.oops(e.getMessage());
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {

        }

        return bufferedStringOutput.nextResponse();
    }
}
