package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Parser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke extends Application {

    /*
        Alas - OOP, using a sledgehammer to crack a nut.
        Yet another assignment with disproportionate workload #NUSLife
     */
    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private void initialize() {
        String filePath = "." + File.separator + "duke.txt";
        storage = new Storage(filePath);
        if (new File(filePath).exists()) {
            try {
                tasks = new TaskList(storage.load());
                showResponse(Ui.showLoading(tasks.getSize()));
            } catch (DukeException | FileNotFoundException ex) {
                showResponse(Ui.showError(ex.getMessage()));
                tasks = new TaskList();
            }
        } else {
            tasks = new TaskList();
        }
        showResponse(Ui.showWelcome());
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.getIcons().add(new Image("/images/Icon.png"));
        stage.setTitle("HustlePad");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        initialize();
    }

    private void handleUserInput() {
        try {
            String fullCommand = userInput.getText();
            Command c = Parser.parse(fullCommand);
            String response = c.execute(tasks, storage, fullCommand);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(fullCommand, user)
            );
            userInput.clear();
            showResponse(response);
            if (c.isExit()) {
                Thread.sleep(2000);
                System.exit(0);
            }
        } catch (DukeException | IOException | InterruptedException ex) {
            showResponse(Ui.showError(ex.getMessage()));
        }
    }

    public void showResponse(String dukeText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, duke));
    }

}