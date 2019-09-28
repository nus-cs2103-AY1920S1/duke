import duke.actionstack.DukeActionStack;
import duke.exception.DukeIllegalFileFormatException;
import duke.io.Storage;
import duke.ui.DialogPanel;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Main class of DukeBot.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private AnchorPane mainLayout;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor of Duke. Initiates instances used by the chat bot.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        DukeActionStack stack = new DukeActionStack();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor used by Launcher.
     */
    public Duke() {
        this("/data/duke.txt");
    }

    /**
     * Overrides the start method in Application.
     */
    //Reused from nus-cs2103-ay1920s1.github.io/ with minor modifications
    @Override
    public void start(Stage stage) {
        initPane();

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        setScrollPane();
        setAnchorPane();
    }

    private void initPane() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    private void setScrollPane() {
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogPanel.getDukeDialog(new Label(ui.initMessage()), new ImageView(bot))
        );
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            if (handleUserInput()) {
                Platform.exit();
            }
        });
    }

    private boolean handleUserInput() {
        String response = "";
        boolean isExit = false;
        Label userText = new Label(userInput.getText());
        try {
            if (userInput.getText().contains("bye")) {
                isExit = true;
            }
            response = ui.readUserInput(userInput.getText());
        } catch (Exception e) {
            response = e.getMessage();
        }

        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogPanel.getUserDialog(userText, new ImageView(user)),
                DialogPanel.getDukeDialog(dukeText, new ImageView(bot))
        );
        userInput.clear();
        return isExit;
    }

}
