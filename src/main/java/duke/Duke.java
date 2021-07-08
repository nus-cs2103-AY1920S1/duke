package duke;

import duke.command.Command;
import duke.ui.Ui;

import java.io.File;

public class Duke {
    static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    static final String SAVE_FILE_NAME = "save.txt";
    static final String ABSOLUTE_PATH_TO_SAVE_FILE = WORKING_DIRECTORY + File.separator + SAVE_FILE_NAME;

    public static void main(String[] args) {
        new Duke(ABSOLUTE_PATH_TO_SAVE_FILE).run();
    }

    private Storage storage = null;
    private TaskList taskList = null;
    private Ui ui = null;
    private Parser parser = null;
    private boolean isRunning = true;

    /**
     * Creates an instance of Duke which runs the program. Uses the default path to save file.
     * Required for Launcher.java to work.
     */
    public Duke() {
        this(ABSOLUTE_PATH_TO_SAVE_FILE);
    }

    /**
     * Creates an instance of Duke which runs the program.
     *
     * @param saveFilePath Save file path to be used for saving / loading tasks
     */
    public Duke(String saveFilePath) {
        ui = new Ui();
        storage = new Storage(saveFilePath);
        try {
            taskList = storage.loadTaskListFromSaveFile(ui);
        } catch (DukeException e) {
            ui.printError(e);
        } finally {
            if (taskList == null) {
                taskList = new TaskList();
            }
        }
        parser = new Parser();
    }

    /**
     * Run the Duke instance (CLI version).
     */
    public void run() {
        ui.setEnabled(true);
        ui.printGreeting();
        while (isRunning()) {
            try {
                String line = ui.readLine();
                Command cmd = parser.parse(line);
                cmd.execute(this, taskList, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Initialize duke and return initial greeting (for GUI implementation).
     *
     * @return greeting
     */
    public String initAndGreet() {
        ui.setEnabled(true);
        ui.printGreeting();
        return ui.flushBuffer();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Function to generate response to user input (meant for GUI, but also used by JUnit).
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            cmd.execute(this, taskList, ui, storage);
        } catch (DukeException e) {
            ui.printError(e);
        }
        return ui.flushBuffer();
    }
}
