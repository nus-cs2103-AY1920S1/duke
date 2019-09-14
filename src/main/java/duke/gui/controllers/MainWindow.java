package duke.gui.controllers;

import duke.Duke;
import duke.gui.AutoCompleteVBox;
import duke.gui.DialogType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private AutoCompleteVBox autoCompleteVBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a mainwindow GUI object which will be added into a stage in Main.class.
     *
     * @param duke duke object to be linked together with this GUI object
     */
    public MainWindow(Duke duke) {
        this();
        this.duke = duke;

        //Add initial dialog from duke
        String initGreeting = duke.initAndGreet();
        addDialog(DialogType.DUKE, initGreeting);
    }

    protected MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeAutoCompleteListeners();
    }

    /**
     * Setup auto complete listeners for auto complete vbox display.
     */
    public void initializeAutoCompleteListeners() {
        userInput.textProperty().addListener((observable, oldValue, newValue) -> {
            autoCompleteVBox.updateList(newValue);
        });

        userInput.setOnKeyPressed(e -> {
            if (autoCompleteVBox.isActive()) {
                if (e.getCode() == KeyCode.UP) {
                    autoCompleteVBox.upButtonPressed();
                } else if (e.getCode() == KeyCode.DOWN) {
                    autoCompleteVBox.downButtonPressed();
                } else if (e.getCode() == KeyCode.RIGHT) {
                    userInput.setText(autoCompleteVBox.requestAutoCompleteResult());
                    userInput.positionCaret(userInput.getText().length());
                }
            }
        });
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (autoCompleteVBox.isActive()) {
            userInput.setText(autoCompleteVBox.requestAutoCompleteResult());
            userInput.positionCaret(userInput.getText().length());
        } else {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();

            if (!duke.isRunning()) {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * Add a dialog box.
     *
     * @param type type of dialog (enum)
     * @param text Text to add
     */
    private void addDialog(DialogType type, String text) {
        DialogBox dialogBox;
        switch (type) {
        case USER:
            dialogBox = DialogBox.getUserDialog(text, userImage);
            break;
        default:
            dialogBox = DialogBox.getDukeDialog(text, dukeImage);
            break;
        }
        dialogContainer.getChildren().add(dialogBox);
    }
}