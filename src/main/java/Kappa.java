import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import kappa.command.Command;
import kappa.command.CommandType;

import kappa.elements.Parser;
import kappa.elements.Storage;
import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.InOutWentWrongException;
import kappa.exception.KappaException;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>
 *     Welcome to Kappa.
 * </h1>
 * <p>
 *     This Kappa Program is a productivity application which helps
 * you to store and load tasks as well as letting you mark them
 * as done as you go about your daily routine!
 * </p>
 * <p>
 *     Image rights for doge.png and kermit.png goes to their respective owners. No profit is made from
 *     the creation of this productivity application.
 *     Link for doge.png: https://www.stickpng.com/img/memes/doge/doge-full-smiling
 *     Link for kermit.png: https://www.pngix.com/viewpng/mbiRi_kermit-the-frog-png-clipart-kermit-the-frog/
 * </p>
 *
 * @author Kalsyc
 * @version 2.0
 * @since 28 August 2019
 *
 */
public class Kappa {

    private Ui ui;
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/doge.png"));
    private Image kappa = new Image(this.getClass().getResourceAsStream("/images/kermit.png"));

    /**
     * Drives the application. Main method.
     *
     * @param args Placeholder
     * @throws KappaException Throws any exception that arises with running the application
     */
    public static void main(String[] args) throws KappaException, IOException {
        new Kappa().run();
    }

    /**
     * Constructor which initialises storage and taskList. Creates new directory to store text if missing.
     *
     * @throws KappaException In case storage cannot be loaded or Error in creating directory.
     */
    public Kappa() throws KappaException {
        this.ui = new Ui();
        Path path = Paths.get("./data");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException error) {
            throw new InOutWentWrongException();
        }
        Storage storage = new Storage("./data/store.txt");
        this.taskList = new TaskList(storage.load(), storage);
    }

    /**
     * Generates response based on user input.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.getCommandType().equals(CommandType.EXIT)) {
                return command.execute(this.taskList, this.ui);
            }
            return command.execute(this.taskList, this.ui);
        } catch (KappaException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Runs the logic of the application. It takes in user input
     * and parses the command before determining what type of command and execute
     * the command accordingly.s
     */
    private void run() throws KappaException {
        Ui.showWelcomeMessage();
        while (true) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(this.taskList, this.ui);
        }
    }
}
