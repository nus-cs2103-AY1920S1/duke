package softeng.duke;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.StageStyle;
import softeng.tasks.TaskList;
import softeng.dukeExceptions.DukeException;
import softeng.gui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


/**
 * Represents a chat bot that manages your daily tasks.
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Parser parser;
    private Statistics stats;

    /*public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }*/
    public Duke() {
        ui = new Ui();
        storage = new Storage("../../save.txt");
        tasks = new TaskList(storage.load());
        parser = new Parser();
        stats = new Statistics();
    }
    public static void main(String[] args) throws DukeException{
        new Duke()/*.run()*/;
    }

    /*public void Greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }*/

    /*public void run() {
        Greet();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);
        parser.read(tasks);
        storage.save(tasks);
    }*/

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getStyleClass().add("root");

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        File f = new File("style/background.css");
        scene.getStylesheets().addAll(this.getClass().getResource("style/background.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        //formatting
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setMinHeight(535);

        // create a image
        Image image = new Image(this.getClass().getResourceAsStream("images/bg.jpg"));

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        //scrollPane.setBackground(new Background(backgroundimage));
        dialogContainer.setBackground(new Background(backgroundimage));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        DialogBox dukeInitial = DialogBox.getDukeDialog(new Label(tasks.initialList()), new ImageView(duke));
        dialogContainer.getChildren().add(dukeInitial);
        VBox.setMargin(dukeInitial, new Insets(10, 5, 10, 5));
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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
        DialogBox userBox = DialogBox.getUserDialog(userText, new ImageView(user));
        DialogBox dukeBox = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        dialogContainer.getChildren().addAll(
                userBox,
                dukeBox
        );
        VBox.setMargin(userBox, new Insets(10, 5, 10, 5));
        VBox.setMargin(dukeBox, new Insets(10, 5, 10, 5));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return parser.parse(tasks, input, storage, stats);
    }

}

