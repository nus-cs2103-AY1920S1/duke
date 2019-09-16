package duke;

import duke.logic.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The main class in this programme.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/chris.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/jason.jpg"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String run(String input) {
        ui.showWelcome(); //can take this out and put it at the start of the initial programme
        Parser inputParser = new Parser(input);
        inputParser.parse();
        String command = inputParser.getCommand();
        String taskDetails = inputParser.getTaskDetails();
        if (command.equals("bye")) {
            return ui.showBye();
        } else if (command.equals("list")) {
            StringBuilder listBuilder = new StringBuilder();
            listBuilder.append(ui.showSeparationLine());
            if (tasks.getListOfTasks().isEmpty()) {
                listBuilder.append("Sorry, there are no tasks in the list");
            } else {
                listBuilder.append("     Here are the tasks in your list:\n");
                listBuilder.append(tasks.printList());
                listBuilder.append(ui.showSeparationLine());
            }
            return listBuilder.toString();
        } else if (command.equals("done")) {
            int taskNumber = Integer.parseInt(taskDetails);
            Task newlyDoneTask = tasks.getTask(taskNumber - 1);
            tasks.setTaskAsDone(taskNumber - 1);
            storage.write(tasks.getListOfTasks());
            return ui.showDone(newlyDoneTask);
        } else if (command.equals("delete")) {
            int taskNumber = Integer.parseInt(taskDetails);
            Task taskToBeDeleted = tasks.getTask(taskNumber - 1);
            tasks.deleteTask(taskNumber - 1);
            int newSizeOfList = tasks.getListOfTasks().size();
            storage.write(tasks.getListOfTasks());
            return ui.showDelete(taskToBeDeleted, newSizeOfList);
        } else if(command.equals("find")) {
            String keyword = taskDetails;
            return ui.showMatchingTasks(tasks.find(keyword));
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            try {
                if (taskDetails.equals("")) {
                    throw new DukeException("      :( OOPS!!! The description of a " +
                            command + " cannot be empty.");
                }
                switch (command) {
                    case "todo":
                        tasks.addTask(new Todo(taskDetails));
                        break;
                    case "deadline":
                    case "event":
                        //replace the first / so that the dates will not be split up
                        taskDetails = taskDetails.replaceFirst("/", ":");  //need to assign this to tempString so it is re-recorded
                        String[] tempStringArr = taskDetails.split(":");
                        String description = ((String) Array.get(tempStringArr, 0)).trim();  //to remove ending whitespace
                        String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                        if (command.equals("deadline")) {
                            tasks.addTask(new Deadline(description, secondString));
                        } else {
                            tasks.addTask(new Event(description, secondString));
                        }
                    default: //all other keywords not part of Duke's task handling schedule
                        throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de) {
                return ui.showSeparationLine() + de.getMessage() + ui.showSeparationLine() + ui.showBlankLine();
            } catch (ArrayIndexOutOfBoundsException ae) {
                return ui.showSeparationLine() + "      :( OOPS!!! You need to specify the " + command +
                        " time through a /by (deadline) and /at (event)\n" + ui.showSeparationLine() + ui.showBlankLine();
            }
        } else {//all other keywords not part of Duke's task handling schedule
            try {
                throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException de) {
                return ui.showSeparationLine() + de.getMessage() + ui.showSeparationLine() + ui.showBlankLine();
            }
        }

            //to prevent printing of below mentioned lines

            Task temp = tasks.getTask(tasks.getListOfTasks().size() - 1);
            int size = tasks.getListOfTasks().size();
            storage.write(tasks.getListOfTasks());
            return ui.showAdd(temp, size);
        }
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
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
        //pass the string input to duke, duke should then run this and return a string that can be passed into the label.
        return this.run(input);
    }
}



