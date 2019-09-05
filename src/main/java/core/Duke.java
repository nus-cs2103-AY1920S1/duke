package core;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import ui.DialogBox;
import ui.TextUi;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Main class of the program.
 * starts the application and receives use input.
 */
public class Duke extends Application {
    private Storage saveFile;
    private TextUi textui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image ui = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() throws IOException {
        saveFile = new Storage("tasklist.txt");
        textui = new TextUi();
    }

    public Duke(String filepath) throws IOException {
        saveFile = new Storage(filepath);
        textui = new TextUi();
    }

    /**
     * Runs the program until termination.
     * @throws IOException in case file doesn't load
     */
    public void run() throws IOException {
        textui.printIntroduction();
        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        TaskList scheduler;
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        while (!user.equals("bye")) {
            scheduler = new TaskList(saveFile.loadData());
            parser.parse(user, scheduler,false);
            saveFile.storeData(scheduler.getTaskList());
            user = input.nextLine();
        }

        textui.printGoodByeMsg();

    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(450.0);

        mainLayout.setPrefSize(450.0, 600.0);

        scrollPane.setPrefSize(435, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(375.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle ui input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
     * Creates two dialog boxes, one echoing ui input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the ui input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(ui)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

    }

    /**
     * You should have your own function to generate a response to ui input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) throws IOException {
        //textui.printIntroduction();
        String user = input;
        TaskList scheduler;
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scheduler = new TaskList(saveFile.loadData());
        parser.parse(user, scheduler,false);
        saveFile.storeData(scheduler.getTaskList());
        return outContent.toString();
    }



    public static void main(String[] args) throws IOException {
        new Duke("tasklist.txt").run();
    }
}
