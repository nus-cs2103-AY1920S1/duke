import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke extends Application {

    private static ArrayList<Task> items;
    private Ui user;
    private Image dukeUser = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeServer = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        user = new Ui();
        items = new ArrayList<>();
    }

    /**
     * Main method for Duke.
      * @param args user input
     */
    public static void main(String[] args) {
        Duke execute = new Duke();

        try {
            execute.run();
        } catch (FileNotFoundException e) {
            System.out.println("Stored file not found!!!");
        }
    }

    @Override
    public void start(Stage stage) {

        try {
            Scanner pastScan = new Scanner(new FileReader("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt"));
            loadExisting(pastScan);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(dukeRun(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(dukeUser)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dukeServer))
            );
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(dukeRun(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(dukeUser)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dukeServer))
            );
            userInput.clear();
        });

        //store current tasks into local file for future reference
        storeCurrent(items);
    }

    /**
     * Processing method for GUI version of Duke.
     * @param input String value from user typing in textfield
     * @return String output for Duke to reply
     */
    public String dukeRun(String input) {
        String reply = "";
        if (input.toLowerCase().equals("hello")) {
            reply = user.welcome() + user.greeting();
            assert !reply.isEmpty() : "Reply should not be empty";
        } else if (input.toLowerCase().equals("bye")) {
            //store current tasks into local file for future reference
            storeCurrent(items);
            Platform.exit();
        } else if (input.toLowerCase().contains("done")) {
            try {
                //mark task as done, change cross to tick
                int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                Task curr = items.get(itemNum - 1);
                curr.markAsDone();
                assert curr.isDone : "Task should have been marked as done";
                //forming the message
                reply = user.doneMessage(curr);
            } catch (IndexOutOfBoundsException e) {
                reply = user.indexError();
            } catch (NumberFormatException e) {
                reply = user.emptyError();
            }
        } else if (input.toLowerCase().equals("list")) {
            reply = user.listTask(items);
        } else if (input.toLowerCase().contains("delete")) {
            try {
                int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                reply = deleteTask(itemNum);
            } catch (IndexOutOfBoundsException e) {
                reply = user.indexError();
            } catch (NumberFormatException e) {
                reply = user.emptyError();
            }
        } else if (input.toLowerCase().contains("find")) {
            String searchTerm = input.substring(5);
            reply = user.findTask(items, searchTerm);
        } else {
            reply = generateTask(input);
            assert items.size() > 0 : "List of task should be more than 0";
        }

        return reply;
    }

