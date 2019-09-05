import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Duke program implements an application that
 * acts as a task manager for users that allows users to write task into a file and
 * update the file accordingly when users have completed their task/wish to delete a
 * task.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class Duke {

    protected boolean isExit = false;
    private static final String filePath = "data/duke.txt";

    /**
     * This storage deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * ui deals with interactions with the user.
     */
    private Ui ui;

    /**
     * tasks contains the task list of users and operations like adding and removing tasks in the list.
     */
    private TaskList tasks;

    /**
     * Class constructor of <code>Duke</code> that processes user input and outputs the tasks into a data file.
     * If a data file is already created in the given file path, the data file is updated according to
     * the user's input. If file does not exist in the given file path, a new date file is created.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        try {
            this.tasks = new TaskList(this.storage.loadFromFile());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            ui.printString();
            this.tasks = new TaskList();
        }
    }

    /**
     * This method is used to allow the execution of <code>duke</code> to take place until the user exits
     * the program. It allows the program to keep reading the user's input until the user exits the program.
     */
    public void run() {

        ui.showWelcome();
        while (!this.isExit) {
            if (!ui.isDoneReading()) {
                break;
            }
            try {
                String userInput = ui.userInput();
                Command c = Parser.parse(userInput);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    this.isExit = c.isExit();
                }
            } catch (DukeException e) {
                ui.printError("Something went wrong: " + e.getMessage());
            }
        }

        ui.printBye();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        if (this.isExit) {
            try {
                this.storage.saveToFile(this.tasks.getAllTasks());
            } catch (DukeException e) {
                ui.printError("Something went wrong: " + e.getMessage());
            }
            String out = "thisisexitcommand";
            return out;
        }
        ui.resetOutput();

        try {
            Command c = Parser.parse(input);
            if (c != null) {
                c.execute(tasks, ui, storage);
                this.isExit = c.isExit();
            }
            return ui.printString();
        } catch (DukeException e) {
            ui.printError("Something went wrong: " + e.getMessage());
            return ui.printString();
        }
    }


    /**
     * This is the main method where <code>Duke</code> is instantiated and executed using the <code>run</code> method.
     * A default file path to /duke.txt is provided in the main method.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
