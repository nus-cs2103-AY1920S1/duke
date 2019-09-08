package fx;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandFactory;
import javafx.fxml.FXML;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;
import duke.task.TasksController;
import util.OutputBuilder;
import util.DukeOutput;
import util.DukeOutputImplementation;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class DukeMainWindowController implements DukeOutputImplementation {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Image userImage;
    private Image dukeImage;

    private CommandFactory commandFactory;
    private boolean isExited = false;

    public DukeMainWindowController() {
        File userFile = new File("src/main/assets/DaUser.png");
        File dukeFile = new File("src/main/assets/DaDuke.png");

        try {
            userImage = new Image(new FileInputStream(userFile));
            dukeImage = new Image(new FileInputStream(dukeFile));
        } catch (FileNotFoundException e) {
            System.out.println("Image files not found");
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void configureMainWindowController(TasksController tasksController) {
        commandFactory = new CommandFactory(tasksController);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (!input.equals("") && !isExited) {
            dialogContainer.getChildren().addAll(
                    FxDialogBox.getUserDialog(input, userImage)
            );

            Optional<Command> next = commandFactory.parse(input);

            next.ifPresent(Command::execute);

            if (next.isPresent() && next.get() instanceof ByeCommand) {
                isExited = true;
                DukeOutput.printMessage(new OutputBuilder("Input will be disabled!"));
            }

            userInput.clear();
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    @Override
    public void printDukeOutput(String message) {
        dialogContainer.getChildren().addAll(FxDialogBox.getDukeDialog(message, dukeImage));
    }
}

