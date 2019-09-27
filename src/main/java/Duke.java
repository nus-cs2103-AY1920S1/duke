import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.stage.Stage;

import slave.command.Command;
import slave.command.CommandType;

import slave.elements.Parser;
import slave.elements.Storage;
import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>
 *     Welcome to Slave.
 * </h1>
 * <p>
 *     This Slave Program is a productivity application which helps
 * you to store and load tasks as well as letting you mark them
 * as done as you go about your daily routine!
 * </p>
 *
 * @author Kalsyc
 * @version 1.0
 * @since 28 August 2019
 *
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/doge.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/kermit.png"));

    /**
     * Drives the application. Main method.
     *
     * @param args Placeholder
     * @throws DukeException Throws any exception that arises with running the application
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }

    /**
     * Constructor which initialises storage and taskList.
     *
     * @throws DukeException In case storage cannot be loaded
     */
    public Duke() throws DukeException, IOException {
        this.ui = new Ui();
        Path path = Paths.get("./data");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Storage storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(storage.load(), storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws InterruptedException {
        try {
            Command command = Parser.parse(input);
            if (command.getCommandType().equals(CommandType.EXIT)) {
                return command.execute(this.taskList, this.ui);
            }
            return command.execute(this.taskList, this.ui);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Runs the logic of the application. It takes in user input
     * and parses the command before determining what type of command and execute
     * the command accordingly.
     */
    private void run() {
        Ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    Platform.exit();
                    System.exit(0);
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }

    }

}
