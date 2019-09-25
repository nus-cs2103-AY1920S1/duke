package weijie.duke.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import weijie.duke.commands.CommandHistory;
import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.exceptions.DukeException;
import weijie.duke.responses.TaskResponse;
import weijie.duke.utils.StringUtils;

import java.util.Timer;
import java.util.TimerTask;


public class MainWindowController extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final TaskCommandFactory factory;
    private final CommandHistory commandHistory;
    private final Image userImage;
    private final Image dukeImage;

    /**
     * <p>
     *     Constructor for MainWindowController.
     * </p>
     * @param factory TaskCommandFactory dependency to create ITaskCommands.
     * @param commandHistory dependency that manages the command history.
     */
    public MainWindowController(TaskCommandFactory factory, CommandHistory commandHistory) {
        this.factory = factory;
        this.commandHistory = commandHistory;
        this.userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
        this.dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    /**
     * Called upon JavaFx generation of the MainWindow view. Sets up the scroll panel and displays a greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty()
                .bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(new DukeDialogController("Hello! I'm Duke\nWhat can I do for you?", dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();

        ObservableList<Node> dialogList = dialogContainer.getChildren();
        dialogList.add(new UserDialogController(input, userImage));

        if (input.equals("bye")) {
            doAfter(300, () -> dialogList.add(
                    new DukeDialogController(StringUtils.indent("Bye. Hope to see you again soon!"),
                            dukeImage)));

            doAfter(1000, () -> {
                Platform.exit();
                System.exit(0);
            });
            return;
        }

        String[] args = input.split(" ");

        try {
            ITaskCommand command = factory.tryMakeCommand(args[0]);
            TaskResponse response = command.execute(args);

            doAfter(300, () -> {
                if (response.isInvalidInput()) {
                    dialogList.add(new DukeDialogController(response.getErrorMessage(), dukeImage));
                } else {
                    commandHistory.addCommand(command);
                    dialogList.add(new DukeDialogController(response.getFormattedResponse(), dukeImage));
                }
            });

        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    private void doAfter(long milliseconds, Runnable task) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(task);
            }
        }, milliseconds);
    }
}

