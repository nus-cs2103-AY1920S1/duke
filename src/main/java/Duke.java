import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    ScrollPane scrollPane;
    VBox dialogContainer;
    TextField userInput;
    Button sendButton;
    Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {

    }

    /**
     * Constructor for Duke that takes in a file to add text into.
     *
     * @param filepath File that the task is added to.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *         does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public Duke(String filepath) throws IOException {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Contains the methods to start the bot and
     * start to take in inputs for the bot.
     */
    public void run() {
        ui.greeting();
        ui.nextCommand();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        new Duke("todo.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
//        ScrollPane scrollPane;
//        VBox dialogContainer;
//        TextField userInput;
//        Button sendButton;
//        Scene scene;

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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
        // more code to be added here later
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
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String text = input.trim();
            try {
                if (text.equals("bye")) {
                    return ui.printBye();
                    //ui.printLine();
                } else if (text.equals("list")) {
                    return ui.printList();
                } else if (text.equals("delete all")) {
                    return tasks.deleteAllCommand(text);
                } else if (text.contains(" ")) {
                    String[] splittedText = text.split(" ");
                    if (splittedText[0].equals("done")) {
                        int num = text.indexOf(" ");
                        int taskNumber = Integer.parseInt(text.substring(num + 1, num + 2));
                        if (taskNumber > 0 && taskNumber <= TaskList.listOfTasks.size()) {
                            return ui.printDone(taskNumber);
                        } else {
                            throw new DukeException("☹ OOPS!!! There is no such task "
                                    + "number in your list of tasks!! Please enter a valid number!");
                        }
                    } else if (splittedText[0].equals("delete")) {
                        return tasks.deleteCommand(text);
                    } else if (splittedText[0].equals("find")) {
                        ArrayList<String> strList = ui.findCommand(splittedText[1]);
                        return ui.printKeywordList(strList);
                    } else {
                        if (splittedText[0].equals("todo")) {
                            System.out.println("hey");
                            return tasks.toDoCommand(text);
                        } else if (splittedText[0].equals("deadline")
                                && text.contains("/") && text.contains("by")) {
                            return tasks.deadlineCommand(text);
                        } else if (splittedText[0].equals("event")
                                && text.contains("/") && text.contains("at")) {
                            return tasks.eventCommand(text);
                        } else {
                            ui.printLine();
                            ui.printIndent();
                            throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct format! :<");
                        }
                    }
                } else {
                    ui.printLine();
                    ui.printIndent();
                    switch (text) {
                    case "todo":
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. "
                                + "It must be in proper format (i.e. todo clean table).");
                    case "deadline":
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. "
                                + "It must be in proper format (i.e. deadline return book /by 23 Aug).");
                    case "event":
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. "
                                + "It must be in proper format (i.e. event Don's birthday /at 15 Jan 3pm).");
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException | IOException e) {
                System.out.println(e);
                ui.printLine();
            }
        return "";
    }
}
