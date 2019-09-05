package weijie.duke.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.exceptions.DukeException;
import weijie.duke.responses.TaskResponse;


public class MainWindowController extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final TaskCommandFactory factory;
    private final Image userImage;
    private final Image dukeImage;

    public MainWindowController(TaskCommandFactory factory) {
        this.factory = factory;
        this.userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
        this.dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * <p>
     *     Starts the main flow of the app. Presenter will start to listen for user input from the Ui, and will
     *     create appropriate Commands and invoke them, returning any TaskResponses to the Ui to be displayed.
     * </p>
     */
//    public void run() {
//        ui.startDisplay();
//
//        while (true) {
//            String input = ui.readCommand();
//
//            if (input.equals("bye")) {
//                ui.printExit();
//                break;
//            }
//
//            String[] args = input.split(" ");
//
//            try {
//                ITaskCommand command = factory.tryMakeCommand(args[0]);
//                TaskResponse response = command.execute(args);
//                ui.print(response);
//
//            } catch (DukeException e) {
//                ui.printError(e);
//            }
//
//        }
//    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren()
                .add(DialogBoxController.getUserDialog(input, userImage));

        if (input.equals("bye")) {
            Platform.exit();
            System.exit(0);
        }

        String[] args = input.split(" ");

        try {
            ITaskCommand command = factory.tryMakeCommand(args[0]);
            TaskResponse response = command.execute(args);

            if (response.isInvalidInput()) {
                dialogContainer.getChildren()
                        .add(DialogBoxController.getDukeDialog(response.getErrorMessage(), dukeImage));
            } else {
                dialogContainer.getChildren()
                        .add(DialogBoxController.getDukeDialog(response.getFormattedResponse(), dukeImage));
            }

        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}

