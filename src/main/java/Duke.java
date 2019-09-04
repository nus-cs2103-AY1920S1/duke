import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class where the main logic is executed
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke duke;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private boolean isExit = false;

    public Duke() {
        this("data/duke.txt", "data");
    }

    /**
     * Intialises all the classes needed to run this Application.
     * It also retrieves the previous tasks saved in the last session.
     * @param filePath File Path of duke.txt
     * @param folderPath Folder Path in which duke.txt is saved
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.retrieve());
        } catch (FileNotFoundException e) {

            try {
                storage.makeDirectory(folderPath);
                tasks = new TaskList();
            } catch (IOException ex) {
                ui.abort();
                System.exit(0);
            }
        }
    }

    /**
     * Runs the main logic of the application
     */
    public String run (String fullCommand) {

        try {
            Command c = Parser.parse(fullCommand);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (IOException | DukeException e) {
            return ui.showError(e.getMessage());
        }


    }

    /**
     * This is the starting point of the application
     * @param args
     * @throws DukeException
     * @throws IOException
     */


    public static void main(String[] args) throws DukeException, IOException {

        Application.launch(Duke.class, args);
    }

    //Fx Functions

    /**
     * Setting up the JavaFX Controls
     * @param stage Primary Stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //Step 1. Setting up required components

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send!");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected

        stage.setTitle("My name is Duke!");

        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325);
        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(userInput,1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked(mouseEvent -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        greetUser();

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(userImage)),
                new DialogBox(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();

        if (this.isExit) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            System.exit(0);
        }
    }

    private void greetUser() {
        Label dukeText = new Label(ui.greeting());
        dialogContainer.getChildren().addAll(
                new DialogBox(dukeText, new ImageView(dukeImage))
        );
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return this.run(input);
    }


}
