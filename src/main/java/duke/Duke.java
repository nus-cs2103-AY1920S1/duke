package duke;

import java.text.ParseException;
import java.util.Scanner;

import duke.command.Add;
import duke.command.Bye;
import duke.command.Command;
import duke.command.Delete;
import duke.command.Done;
import duke.command.Find;
import duke.command.PrintList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents a Duke chatbot.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initiates a Duke object.
     * @param filePath path in hard disk to store tasks
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke object that stores file in tasks.txt
     */
    Duke() {
        this("tasks.txt");
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        while (true) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                if (command instanceof Bye) {
                    Bye bye = (Bye) command;
                    bye.exec(storage, tasks, ui);
                    break;
                } else if (command instanceof PrintList) {
                    PrintList pl = (PrintList) command;
                    pl.exec(storage, tasks, ui);
                } else if (command instanceof Done) {
                    Done done = (Done) command;
                    done.exec(storage, tasks, ui);
                } else if (command instanceof Delete) {
                    Delete delete = (Delete) command;
                    delete.exec(storage, tasks, ui);
                } else if (command instanceof Add) {
                    Add add = (Add) command;
                    add.exec(storage, tasks, ui);
                } else if (command instanceof  Find) {
                    Find find = (Find) command;
                    find.exec(storage, tasks, ui);
                } else {
                    throw new DukeException("Sorry Mirana, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                ui.showDukeException(ex);
            }
        }
        System.exit(0);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command instanceof Bye) {
                Bye bye = (Bye) command;
                return bye.exec(storage, tasks, ui);
            } else if (command instanceof PrintList) {
                PrintList pl = (PrintList) command;
                return pl.exec(storage, tasks, ui);
            } else if (command instanceof Done) {
                Done done = (Done) command;
                return done.exec(storage, tasks, ui);
            } else if (command instanceof Delete) {
                Delete delete = (Delete) command;
                return delete.exec(storage, tasks, ui);
            } else if (command instanceof Add) {
                Add add = (Add) command;
                return add.exec(storage, tasks, ui);
            } else if (command instanceof  Find) {
                Find find = (Find) command;
                return find.exec(storage, tasks, ui);
            } else {
                throw new DukeException("Sorry Mirana, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        }
    }

    /**
     * Runs Duke.
     * @param args dummy
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private Node getDialogLabel(String text) {
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
                DialogBox.getUserDialog(userText.toString(), new ImageView(user).getImage()),
                DialogBox.getDukeDialog(dukeText.toString(), new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}