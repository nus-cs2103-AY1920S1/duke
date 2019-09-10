package duke;

import duke.command.Command;
import duke.io.BufferedStringOutput;
import duke.task.TaskList;
import duke.util.PreParser;

import java.io.IOException;

/**
 * Main logic class.
 */
public class Duke {
    private static final String DEFAULT_FILEPATH = "./data/tasks.txt";

    private static final String LOADING_ERROR_MESSAGE = "Couldn't load tasks from disk."
            + "\nYour changes this session may not be saved!";
    private static final String COMMAND_ERROR_MESSAGE = "I couldn't understand my list of commands! "
            + "\nI won't be very helpful this session :(";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke."
            + "\nWhat can I do for you?";

    private PreParser preParser;
    private Model model;
    private Storage storage;
    private BufferedStringOutput bufferedUiOutput = new BufferedStringOutput();
    private BufferedStringOutput startupMessages = new BufferedStringOutput();

    /**
     * Constructs a Duke object with the data file residing in the default path.
     */
    public Duke() {
        startupMessages.say(WELCOME_MESSAGE);
        try {
            preParser = new PreParser();
        } catch (DukeException e) {
            e.printStackTrace();
            startupMessages.oops(COMMAND_ERROR_MESSAGE);
        }
        storage = new Storage(DEFAULT_FILEPATH);

        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
            startupMessages.oops(LOADING_ERROR_MESSAGE);
        }
        model = new Model(tasks);
    }

    /**
     * Generates Duke's response to user input.
     */
    public String getResponse(String input) {
        try {
            Command command = preParser.parse(input);
            command.execute(model, bufferedUiOutput, storage);
            try {
                storage.save(model.copyOfCurrentTasks());
            } catch (IOException e) {
                // Unable to save
                System.err.println("Unable to save.");
                e.printStackTrace();
            }
        } catch (DukeException e) {
            bufferedUiOutput.oops(e.getMessage());
            e.printStackTrace();
        }
        return bufferedUiOutput.nextResponse();
    }

    public String getStartupMessages() {
        return startupMessages.nextResponse();
    }
}
