package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import duke.util.Parser;

import java.util.Scanner;

/**
 * Driver class.
 */
public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Initializes the duke chatbot with a file path for storage purpose.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new UI();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Receive input from JavaFX interface and process it and return output for user.
     * @param input Input from JavaFX interface
     * @return The response of Duke given the command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseUserInput(input);
            String result = c.execute(tasks, ui, storage);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

