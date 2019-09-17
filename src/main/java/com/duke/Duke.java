package com.duke;

import com.duke.command.Command;
import com.duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private static final String FILE_PATH = "src/data/duke.txt";
    private static Parser parser;
    static RecycleBin recycleBin;
    private Storage storage;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        TaskList list = storage.genTaskListFromFile();
        parser = new Parser(list);
        recycleBin = new RecycleBin();
    }

    /**
     * Gets Duke's response to user input.
     *
     * @param text user input text.
     * @return response by Duke.
     */
    public static String getResponse(String text) {
        try {
            Command command = parser.parseCommand(text);
            if (!command.isUndoCommand()) {
                recycleBin.addCommandString(text);
                if (command.isDeleteCommand()) {
                    recycleBin.addDeletedTasks(command.getDeletedTask());
                }
            }
            return command.execute();
        } catch (DukeException | IOException | AssertionError e) {
            return e.getMessage();
        }
    }

    public String getRemainingTasks() {
        return storage.getFileContent();
    }
}
