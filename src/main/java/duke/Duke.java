package duke;

import commands.Command;

import exceptions.DukeException;

import java.io.File;
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
    private static boolean shouldExitProgram = false;

    /**
     * Getter method for the boolean indicating whether program should be exited.
     *
     * @return boolean shouldExitProgram
     */
    public static boolean getShouldExitProgram() {
        return shouldExitProgram;
    }

    /**
     * Setter method for the boolean indicating whether program should be exited.
     *
     * @param shouldExitProgram boolean
     */
    public static void setShouldExitProgram(boolean shouldExitProgram) {
        Duke.shouldExitProgram = shouldExitProgram;
    }

    /**
     * Duke constructor that takes in a file path and
     * instantiates Ui, Storage and TaskList objects.
     */
    public Duke() {
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The driver method where the logic of the Duke
     * program is written: parsing of user input, instantiating
     * the appropriate Command class and then executing the corresponding
     * set of actions associated with that command.
     *
     * @param fullCommand String that user typed as text
     * @return String output reply from Duke
     */
    public String getResponse(String fullCommand) {
        String reply;
        boolean isExit = false;
        try {
            Command c = Parser.parse(fullCommand);
            reply = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            reply = e.getMessage();
        }
        // Save the new task list to the hard disk
        if (isExit) {
            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showSavingError(e.getMessage());
            }
        }
        assert !reply.isEmpty() : "Reply should not be empty";
        return ui.showOpeningLine() + reply + ui.showClosingLine();
    }
}
