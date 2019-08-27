package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.Commands;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class of the Duke app.
 * Runs a main read, print, evaluate loop.
 */
class Duke {

    /** The directory name to use in construction of the storage object. */
    private static final String RECURSIVE_PARENT_DIR_NAME = "data";
    /** The storage object to use for task-disk storage. */
    private Storage storage;
    /** The Ui object to use for displaying output to the user. */
    private Ui ui;
    /** The TaskList object to use for in-memory task storage. */
    private TaskList tasks;

    /**
     * Creates an instance of the Duke app and runs it.
     * Does not use any command line arguments currently.
     *
     * @param args Array of command line string arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(RECURSIVE_PARENT_DIR_NAME);
        duke.run();
    }

    /**
     * Constructor of the Duke instance.
     * Initializes the various instance properties.
     *
     * @param dirName The directory name to use for the storage object.
     */
    private Duke(String dirName) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(dirName, ui);
        tasks = new TaskList();
        storage.loadTasksToList(tasks);
    }

    /**
     * The main Read, Print, Evaluate loop of the Duke app.
     * While the command parsed is not of the bye command type,
     * the method continues to execute the commands.
     * Any remaining uncaught duke exceptions are displayed and
     * presented to the user here.
     */
    private void run() {
        ui.printGreetingMsg();

        while (true) {
            try {
                String input = ui.readLine();
                ui.printLineDivider();
                Command command = CommandParser.parseCommand(input);
                command.execute(tasks, ui, storage);

                if (command.commandType == Commands.bye) {
                    break;
                }
            } catch (DukeExceptions ex) {
                ui.displayDukeException(ex);
            } finally {
                ui.printLineDivider();
                ui.printEmptyLine();
            }
        }
    }
}