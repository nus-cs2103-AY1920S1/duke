import java.util.Scanner;

import utils.Storage;
import utils.TaskList;
import utils.Ui;
import utils.Parser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import exceptions.DukeException;

/**
 * this class is the main class of the application, it initializes the main components 
 * and passes them as references to the others.
 */

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/doug.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    /**
     * This is for the GUI, needs an empty constructor for it to work. 
     */

    public Duke() {}

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");


        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
        handleUserInput();
        });

        userInput.setOnAction((event) -> {
        handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane mainLayout = new AnchorPane();

        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

      /**
      * Iteration 1:
      * Creates a label with the specified text and adds it to the dialog container.
      *
      * @param text String containing text to add
      * @return a label with the specified text that has word wrap enabled.
      */
     private Label getDialogLabel(String text) {
         // You will need to import `javafx.scene.control.Label`.
         Label textToAdd = new Label(text);
         textToAdd.setWrapText(true);

          return textToAdd;
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
                 DialogBox.getUserDialog(userText, new ImageView(user)),
                 DialogBox.getDukeDialog(dukeText, new ImageView(duke))
         );
         userInput.clear();
     }

      /**
      * You should have your own function to generate a response to user input.
      * Replace this stub with your completed method.
      */
     private String getResponse(String input) {
         return "Duke heard: " + input;
     }
    /**
     * initialises the application with references to all main components.
     * @param filepath computer relative / absolute path to txt file
     */

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load(), ui);
    }
    
    /**
     * runs every command-line command through the parser method.
     */
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);
        while (sc.hasNextLine()) {
            try {
                parser.parse(sc.nextLine());
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
        sc.close();
    }
    
    public static void main(String[] args) {
        new Duke("/Users/estherngo/Documents/elsa/2103/duke/src/main/java/data/duke.txt").run();
    }
}
