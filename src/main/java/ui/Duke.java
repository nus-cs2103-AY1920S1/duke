package ui;

import commands.Command;
import parser.Parser;
import storage.Storage;
import util.TaskList;

import java.io.IOException;
import java.text.ParseException;

/**
 * Encapsulates attributes and behaviour of ui.Duke, a personal assistant chatbot.
 *
 * <p> Duke manages a user's list of tasks. It can store, add to, remove from, find,
 * or modify the contents of the list. User input must come either in the form of
 * commands 'list', 'bye' 'done (task index)', 'delete (task index)' or storage
 * requests beginning with the type of task to be stored. Duke supports three
 * types of tasks - todos, deadlines, and events. Deadlines and events need to
 * be supplied with additional date or time information, which is parsed by
 * Duke using Java's SimpleDateFormat library. The input format for
 * todos is 'todo (task description)' and that for deadlines and events is
 * '(task type) (task description) / (day/month/year hh:mm)'. Deviating from this
 * input format results in Duke supplying error messages to the user. </p>
 *
 * @author atharvjoshi
 * @contributors j-lum, damithc
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Duke {

    /** Handler for loading from and writing to hard disk. */
    private Storage storage;

    /** The list of tasks. */
    private TaskList tasks;

    /** Handler for dealing with user interactions. */
    private Ui ui;

    /** flag to indicate if ui.Duke is listening to commands from the user. */
    private boolean isListening;

    /**
     * Initialises Duke with a user-interface, storage, and task
     * handler.
     *
     * @param filePath the hard disk file containing the saved list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.isListening = true;
        try {
            this.tasks = new TaskList();
            storage.loadToList(tasks);
        } catch (IOException | ParseException exception) {
            ui.showStartupError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke by asking the parser.Parser to parse the user command and
     * then executing the command, whilst handling exceptions.
     */
    public void run() {
        ui.welcomeUser();
        while (this.isListening) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                this.isListening = !command.getIsExit();
            } catch (IllegalArgumentException exceptionOne) {
                ui.showInvalidCommandError();
            } catch (IndexOutOfBoundsException exceptionTwo) {
                ui.showInvalidFormattingError();
            } catch (IOException exceptionThree) {
                ui.showStartupError();
            } catch (ParseException exceptionFour) {
                ui.showInvalidDateTimeFormattingError();
            }
        }
        ui.farewellUser();
    }
}