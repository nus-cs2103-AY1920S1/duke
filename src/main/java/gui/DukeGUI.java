package gui;

import com.TaskList;
import com.commands.Command;
import com.exceptions.DukeException;
import com.util.Parser;
import com.util.Storage;
import com.util.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, it can only
 * understand text commands of a specific structure.
 */
public class DukeGUI extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public DukeGUI() throws DukeException {
        storage = new Storage("F:\\CS2103\\duke\\data\\duke.txt");
        taskList = new TaskList(storage.load());
        parser = new Parser();
        ui = new Ui();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public void run() {
        ui.showGreetings();
        boolean doesProgramContinue = true;
        while (doesProgramContinue) {
            try {
                String userFullInput = ui.readUserInput();
                Command c = parser.parse(userFullInput);
                c.execute(this.getTaskList(), this.getStorage());
                doesProgramContinue = c.continuesProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new DukeGUI().run();
    }

    //////////////
    // GETTERS //
    ////////////

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

}