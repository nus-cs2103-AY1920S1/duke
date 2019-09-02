package seedu.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.text.ParseException;

/**
 * Runs as a chat bot that helps to manage tasks.
 * Loads task information from data file in hard drive when initialized
 * or creates file in hard drive if it does not exist.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {}

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return run(input);
    }


    /**
     * Runs Duke which will take in user commands to create tasks, delete tasks,
     * mark task as done, list out task while updating data file when the tasks in
     * the list is updated.
     */
    public String run(String input) {
        //ui.showIntro();
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand, ui);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        } catch (ParseException e) {
            return ui.showParseError();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showParseError();
        } catch (Exception e) {
            return ui.showExceptionMsg(e);
        }
    }
}
