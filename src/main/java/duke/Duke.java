package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Main class that brings together all the implemented classes to execute the Duke
 * tasking logic.
 */
public class Duke extends Application {

    public static String saveFilePath = "data/savedTasks.txt";
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructor. Will attempt to load any saved tasks specified in the
     * Duke.savedFilePath class attribute. If not saved tasks are found, then
     * an empty TaskList is initialised.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.saveFilePath);
        try {
            this.allTasks = this.storage.load();
        } catch (DukeException e) {
            ui.printErrorMsg(e);
            this.allTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Keeps looping through to check the console for user input. Will terminate
     * when the user inputs "bye" to the console.
     */
    public static void main(String[] args) {
        Duke.saveFilePath = "data/savedTasks.txt"; //This is meant to be readily changed.
        Duke d = new Duke();
        d.run();
    }

    /**
     * Method to run Duke. Used to continually poll the user for input
     * via the console input.
     */
    public void run() {
        ui.printWelcomeMsg();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.ui, this.storage, this.allTasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                //This is the highest level at which a DukeException is caught.
                //Most DukeExceptions will be caught and handled at this level.
                ui.printErrorMsg(e);
            } catch (NoSuchElementException e) {
                ui.printSentence("Please enter a command!");
            }
        }

        ui.printExitMsg();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
