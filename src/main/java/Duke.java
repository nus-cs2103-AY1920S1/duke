import duke.dukeinterface.Parser;
import duke.dukeinterface.Tasklist;
import duke.dukeinterface.Ui;

import textfiles.ReadFile;
import textfiles.WriteFile;

import java.io.IOException;

import javafx.application.Platform;
import javafx.application.Application;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Keeps track of the list of tasks the user have.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private Tasklist taskList = new Tasklist();
    private ArrayList<String> textArr;
    private String filename = "data/duke.txt";
    private WriteFile data = new WriteFile(filename, true);

    public Duke() {
        ReadFile file = new ReadFile(filename);
        try {
            textArr = file.openFile();
        } catch (IOException e) {
            file.ioErrorMessage();
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Stage 2
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(750.0);
        stage.setMinWidth(650.0);

        mainLayout.setPrefSize(650.0, 750.0);

        scrollPane.setPrefSize(635, 685);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(75.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Start up Duke first
        Label startup = new Label(ui.greet() + taskList.loadEvents(textArr));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startup, new ImageView(duke))
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(ui.userCommand(userInput.getText()));
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if (userInput.getText().equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }

    private String getResponse(String input) {
        return parser.run(input, taskList, data);
    }
}
