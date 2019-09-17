package core;

import com.commands.*;
import com.exceptions.*;

import com.TaskList;

import com.util.Storage;
import com.util.Parser;
import com.util.ui.*;
import gui.GUIUi;
import javafx.scene.layout.VBox;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, it can only
 * understand text commands of a specific structure.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        storage = new Storage("F:\\CS2103\\duke\\data\\duke.txt");
        ui = new CLIUi();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
            taskList = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * If run GUI, change Ui to output GUI responses.
     */
    public void convertToGUIUi(VBox dialogContainer) {
        ui = new GUIUi(dialogContainer);
        ui.showGreetings();
    }


    public void run() {
        ui.showGreetings();
        boolean doesProgramContinue = true;
        while (doesProgramContinue) {
            String userFullInput = ui.readUserInput();
            assert doesProgramContinue == true : "doesProgramContinue should be true";
            doesProgramContinue = handleUserFullInput(userFullInput);
        }
    }

    public boolean handleUserFullInput(String userFullInput) {
        boolean doesProgramContinue;
        try {
            Command c = parser.parse(userFullInput);
            c.execute(this.getTaskList(), this.getStorage(), this.getUi());
            doesProgramContinue = c.continuesProgram();
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
            doesProgramContinue = true;
        }
        return doesProgramContinue;
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
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

    public Ui getUi() {
        return ui;
    }

}