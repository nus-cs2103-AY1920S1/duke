package bot.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import bot.duke.command.Command;
import bot.duke.command.Parser;
import bot.duke.exception.DukeException;
import bot.duke.storage.Storage;
import bot.duke.task.TaskList;
import bot.duke.ui.Gui;
import bot.duke.ui.Ui;
import javafx.application.Application;

/**
 * Drives the duke.Duke bot.
 * This is the main driver class and entry point.
 */
public class Duke {

    /** Instance of TaskList. */
    private TaskList tasks;
    /** Instance of Ui. */
    private Ui ui;
    /** Instance of Storage. */
    private Storage storage;

    /** States if the application is ready to exit. */
    private boolean isGoodbye = false;

    /**
     * Constructs the Duke Object.
     *
     * @param filePath Path to the data text file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            ui.addPreLaunchMsg("STARTUP: File at " + filePath + " loaded successfully, populating Tasks. \n");
        } catch (FileNotFoundException fnfe) {
            tasks = new TaskList();
            ui.addPreLaunchMsg("STARTUP: File not found at " + filePath + ", starting with a clean slate. \n");
        } catch (IOException | ParseException e) {
            tasks = new TaskList();
            ui.addPreLaunchMsg("STARTUP: Unable to read file at " + filePath + ", starting with a clean slade\n");
        }

    }

    /**
     * Runs the logic of Duke.
     * Deprecated due to GUI, kept around for contingencies
     */
    /*
    public void run() {

        this.ui.printWelcomeMsg();

        boolean isGoodbye = false;

        while (!isGoodbye) {
            try {
                String fullInput = ui.readInput();
                Command c = Parser.parse(fullInput);
                c.execute(tasks, ui, storage);
                isGoodbye = c.isExit();
            } catch (DukeException de) {
                ui.exposeError(de.getMessage());
            }
        }
    }
    */
    /**
     * Starts the Duke instance.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Gui gui = new Gui();
        Application.launch(Gui.class, args);
        //Duke dukeInstance = new Duke("data/duke.txt");
        //dukeInstance.run();
    }

    /**
     * Gets response to input from JavaFX Gui.
     *
     * @param fullInput Full input string from JavaFx GuiWindow
     */
    public void getResponse(String fullInput) {
        if (this.isGoodbye) {
            System.exit(0);
        }
        try {
            Command c = Parser.parse(fullInput);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.isGoodbye = true;
            }
        } catch (DukeException de) {
            ui.exposeError(de.getMessage());
        }
    }
}
