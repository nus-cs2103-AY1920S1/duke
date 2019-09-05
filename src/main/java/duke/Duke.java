package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Main class that brings together all the implemented classes to execute the Duke
 * tasking logic.
 */
public class Duke {
    public static String saveFilePath = "data/savedTasks.txt";
    private Storage storage;
    private TaskList allTasks;
    private UiResponse ui;

    /**
     * Constructor. Will attempt to load any saved tasks specified in the
     * Duke.savedFilePath class attribute. If not saved tasks are found, then
     * an empty TaskList is initialised.
     */
    public Duke() {
        this.ui = new UiResponse();
        this.storage = new Storage(Duke.saveFilePath);
        try {
            this.allTasks = this.storage.load();
        } catch (DukeException e) {
            this.allTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) throws DukeException, NoSuchElementException{
        Command c = Parser.parse(input);
        return c.execute(this.ui, this.storage, this.allTasks);
    }
}
