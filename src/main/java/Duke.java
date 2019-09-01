import command.Command;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import task.TaskList;
import exception.DukeException;

import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke programme implements a personal task assistant to which you can give commands.
 * It helps you store and manage to to-do tasks.
 *
 * @author Liu Zechu
 */
public class Duke extends Application {
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Returns a Duke programme whose tasks are stored in the given file path.
     *
     * @param filePath File path of the file where the tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(ui);

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            tasks = new TaskList();
        }
    }

    public Duke() {
        // nothing
    }

    public static void main(String[] args) {
        new Duke("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt").run();
    }

    private void run() {
        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.println("      OOPS!!!");
            }
        }

        exitDuke();
    }

    /**
     * Saves tasks into hard disk and exits the Duke programme.
     */
    private static void exitDuke() {
        String toSave = tasks.convertTasksToString();
        try {
            storage.writeToFile(toSave);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT SAVED PROPERLY");
        }
    }

    @Override
    public void start(Stage stage) {
        // initialize Duke object
        ui = new Ui();
        storage = new Storage("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt");
        parser = new Parser(ui);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            tasks = new TaskList();
        }


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

//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                new DialogBox(userText, new ImageView(user)),
//                new DialogBox(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
    private void handleUserInput() {
        //Label userText = new Label(userInput.getText());
        //Label dukeText = new Label(getResponse(userInput.getText()));
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String toReturn = "";
//        try {
//            Command c = parser.parse(input);
//            toReturn = c.execute(tasks, ui, storage);
//        } catch (Exception e) {
//            // toReturn = "      OOPS!!!";
//            toReturn = e.toString();
//        }

        Command c = parser.parse(input);
        toReturn = c.execute(tasks, ui, storage);

        if (toReturn == null || toReturn.equals("")) {
            toReturn = "I'm sorry, but I don't know what that means :-(";
        }

        return toReturn;
    }
}