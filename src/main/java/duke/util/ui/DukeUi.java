package duke.util.ui;

import duke.Duke;
import duke.command.DukeCommand;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DukeUi extends Application {

    private Duke duke;
    private DukeUiMessages ui;
    private DukeStorage storage;
    private DukeTaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        /* The container for the content of the chat to scroll.
            |-----ScrollPane-----|
            ||-------VBox-------||
            ||                  ||
            ||------------------||
            |--------------------|
         */
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        //Input textfield
        userInput = new TextField();

        //Send button
        sendButton = new Button("Send");

        //Add all elements into the main window
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        //Dialog area size and responsiveness
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Input textfield
        userInput.setPrefWidth(325.0);

        //Send button
        sendButton.setPrefWidth(55.0);

        //Main window
        mainLayout.setPrefSize(400.0, 600.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        duke = new Duke(Duke.DUKE_TASK_FILE_PATH);
        storage = duke.getStorage();
        tasks = duke.getTasks();
        ui = duke.getUi();
        ui.initUiComponents(this);
        ui.displayWelcomeMessage();

        //Wait for user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Adds the specified String into the dialog container as a Label.
     * @param input String to add to the dialog container as a Label.
     */
    public void addAsLabelToDisplay(String input) {
        Label output = new Label(input);
        output.setWrapText(true);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Reads the input from the input textfield and returns it as a String. Clears the user input after processing.
     */
    public void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        addAsLabelToDisplay(input);
        Optional<DukeCommand> command = DukeParser.parseCommand(input, ui);
        if (!command.isEmpty()) {
            command.get().execute(tasks, ui, storage);
        }
    }

}
