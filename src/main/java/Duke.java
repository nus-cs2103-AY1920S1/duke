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

import java.io.FileNotFoundException;

public class Duke extends Application {

    private Ui user;
    private Parser parse;
    private Storage store;
    private Image dukeServer= new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeUser = new Image(this.getClass().getResourceAsStream("/images/Speaker.png"));

    /**
     * Constructor for Duke class.
     */
    public Duke() throws FileNotFoundException {
        user = new Ui();
        parse = new Parser(user);
        store = new Storage(parse);
    }

    /*
    public static void main(String[] args) {
        Duke execute = new Duke();

        try {
            execute.run();
        } catch (FileNotFoundException e) {
            System.out.println("Stored file not found!!!");
        }
    }
    */

    @Override
    public void start(Stage stage) {
        System.out.println("Loading ");
        store.loadExisting();

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
            store.storeCurrent(parse.getList());
            Platform.exit();
        } else if (input.toLowerCase().contains("done")) {
            try {
                //mark task as done, change cross to tick
                int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                Task curr = parse.getList().get(itemNum - 1);
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
            reply = user.listTask(parse.getList());
        } else if (input.toLowerCase().contains("delete")) {
            try {
                int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                reply = parse.deleteTask(itemNum);
            } catch (IndexOutOfBoundsException e) {
                reply = user.indexError();
            } catch (NumberFormatException e) {
                reply = user.emptyError();
            }
        } else if (input.toLowerCase().contains("find")) {
            String searchTerm = input.substring(5);
            reply = user.findTask(parse.getList(), searchTerm);
        } else {
            reply = parse.generateTask(input);
        }

        return reply;
    }

}
