package duke;

import duke.command.*;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Initializes other classes and get responses for input.
 */
public class Duke extends Application {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    /**
     * Constructs a duke object with a given filepath to save the output.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList  = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
        parser = new Parser(taskList, ui);
        ui.taskList = taskList;
    }

    /**
     * Starts the program.
     */
    public void run() {
        assert taskList.getSize() == 0 : "Initial TaskList should not be empty";
        ui.start(parser, storage, taskList);
    }

    /**
     * Main method for the whole program that creates a new Duke object and calls run().
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Setting up the layout (no longer used since fxml is used.
     * @param stage the main stage.
     */
    @Override
    public void start(Stage stage) {}

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                return  parser.parseLine(input);
            } catch (DukeException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "Must input an integer";
            } catch (FileNotFoundException e) {
                return "File not found";
            }
        }
    }

    public void updateFile() {
        storage.updateFile(taskList.getList());
    }
}
