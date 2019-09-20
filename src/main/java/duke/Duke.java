package duke;

import commands.Command;

import exceptions.DukeException;
import ui.MainWindow;

import java.io.IOException;

/**
 * Duke is the driver class of the program.
 * Parser, Storage, TaskList, Ui objects are instantiated here and
 * passed to Command objects in order to execute the appropriate actions.
 */
public class Duke {

    /**
     * the Storage object that reads from and writes to the file.
     */
    private Storage storage;
    /**
     * the TaskList object storing all recorded Tasks.
     */
    private TaskList tasks;

    /**
     * the Ui object dealing with user interaction.
     */
    private Ui ui;

    /**
     * boolean indicating whether the program should be exited.
     */
    private boolean shouldExitProgram = false;

    /**
     * Returns the Ui object dealing with user interaction.
     *
     * @return Ui object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the Storage object dealing with reading and writing the list of tasks to the file.
     *
     * @return Storage object.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Getter method for the boolean indicating whether program should be exited.
     *
     * @return boolean shouldExitProgram
     */
    public boolean getShouldExitProgram() {
        return shouldExitProgram;
    }

    /**
     * Setter method for the boolean indicating whether program should be exited.
     *
     * @param shouldExitProgram boolean
     */
    public void setShouldExitProgram(boolean shouldExitProgram) {
        this.shouldExitProgram = shouldExitProgram;
    }

    /**
     * Duke constructor that takes in a file path and
     * instantiates Ui, Storage and TaskList objects.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a String containing Duke's response to the user command.
     * This method handles the parsing of user input, instantiating
     * the appropriate Command class and then executing the corresponding
     * set of actions associated with that command.
     *
     * @param fullCommand String that user typed as text
     * @return String output reply from Duke
     */
    public String getResponse(String fullCommand) throws DukeException, IOException {
        String reply;
        Command c = Parser.parse(fullCommand, this);
        reply = c.execute(tasks, ui, storage);
        // Save the new task list to the hard disk
        storage.save(tasks);
        assert !reply.isEmpty() : "Reply should not be empty";
        return reply;
    }
}
