package seedu.duke;

import javafx.geometry.Insets;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 * Project Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * storage attribute is a Storage object, which helps read and write data to the text file
 *
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/117.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/commando.jpg"));

    @Override
    public void start(Stage stage) {

        /* Tutorial 1
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        // Image image = new Image(getClass().getResourceAsStream("/117.jpg"));
        // helloWorld.setGraphic(new ImageView(image));

        helloWorld.setFont(new Font("Cambria", 50));

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
         */



        // Tutorial 2: Getting a basic vertical pane

        // Step 1: Setting up required components
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Enter!");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2: Formatting the window to look as expected
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

        // scrollPane.setPadding(new Insets(50,0,50,0));

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3: Add functionaity to handle user input
/*
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
*/
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return ("No, you need some bacta");
        // return "Duke heard: " + input;
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
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                // new DialogBox(userText, new ImageView(user)),
                // new DialogBox(dukeText, new ImageView(duke))
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }


    /**
     * Instantiates a Duke object.
     *
     * @param filePath absolute filepath of the where the text file is stored.
     *                 Eg "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Executes the Duke Command Line Interface.
     */
    public void run() {
        // Variable initialization. description will hold the description of all tasks.
        // extraDescription will hold either the
        // dateTime attribute of Deadline class or location attribute of Event class.
        String output = "";
        String taskType = "";
        String description = "";
        String extraDescription = "";
        int taskNum = -1;

        ui.showWelcome();

        try {

            // Loads the data from txt file to the TaskList object, tasks.
            tasks = new TaskList(this.storage.load());

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        }

        // Creates scanner object to handle input.
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine().trim();

        while (true) {

            taskType = Parser.parseCommand(fullCommand);

            try {

                if (taskType.equals(PossibleTasks.LIST.toString().toLowerCase())) {

                    // LIST command.

                    ui.printList(tasks);

                } else if (taskType.equals(PossibleTasks.DONE.toString().toLowerCase())) {
                    // DONE command.

                    taskNum = Parser.getFinishedTaskNum(fullCommand);

                    // taskList index (starts from 0) differs from taskNum (starts from 1) by 1,
                    taskNum--;

                    tasks.getTask(taskNum).setDone();

                    ui.printDoneSequence(tasks, taskNum);

                } else if (taskType.equals(PossibleTasks.TODO.toString().toLowerCase())) {
                    // TODO command

                    if (fullCommand.length() < 5) {
                        // fullCommand contains only the string "todo".
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    description = Parser.getTodoDescription(fullCommand);

                    Todo newTodo = new Todo(description);

                    tasks.addTask(newTodo);

                    ui.printTodoSequence(tasks, newTodo);

                } else if (taskType.equals(PossibleTasks.DEADLINE.toString().toLowerCase())) {
                    // DEADLINE command.

                    if ((fullCommand.length() < 9)) {

                        // fullCommand contains only the string "deadline".
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

                    } else if ((fullCommand.lastIndexOf('/') < 1)
                            || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {

                        // fullCommand does not contain '/' chars or there are no char after "/by".
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

                    }

                    description = Parser.getDeadlineDescription(fullCommand);
                    extraDescription = Parser.getDeadlineDateTime(fullCommand);

                    Deadline newDeadline = new Deadline(description, extraDescription);

                    tasks.addTask(newDeadline);

                    ui.printDeadlineSequence(tasks, newDeadline);

                } else if (taskType.equals(PossibleTasks.EVENT.toString().toLowerCase())) {
                    // EVENT command.

                    if ((fullCommand.length() < 6)) {
                        // Input is only "event".

                        // fullCommand contains only the string "event".
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

                    } else if ((fullCommand.lastIndexOf('/') < 1)
                            || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {

                        // fullCommand does not contain '/' char or there are no chars after "/at".
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

                    }

                    description = Parser.getEventDescription(fullCommand);
                    extraDescription = Parser.getEventLocation(fullCommand);

                    Event newEvent = new Event(description, extraDescription);

                    tasks.addTask(newEvent);

                    ui.printEventSequence(tasks, newEvent);

                } else if (taskType.equals(PossibleTasks.DELETE.toString().toLowerCase())) {
                    // DELETE command.

                    taskNum = Parser.getDeletedTaskNum(fullCommand);

                    // taskList index (starts from 0) differs from taskNum (starts from 1) by 1.
                    taskNum--;

                    if (taskNum >= tasks.getSize()) {

                        // taskNum does not exist.
                        throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");

                    }

                    Task taskToDelete = tasks.getTask(taskNum);

                    tasks.deleteTask(taskNum);

                    ui.printDeleteSequence(tasks, taskToDelete);
                    taskToDelete = null;

                } else if (taskType.equals(PossibleTasks.FIND.toString().toLowerCase())) {

                    // Parser will parse the command and obtain the searchString.
                    // findSimilarTasks will return a TaskList containing only matching tasks.
                    TaskList similarTasks = tasks.findSimilarTasks(Parser.getFindTask(fullCommand));

                    ui.printFoundTasks(similarTasks);

                } else if (taskType.equals(PossibleTasks.BYE.toString().toLowerCase())) {
                    // BYE command
                    ui.printByeSequence();

                    storage.clearFileBeforeSaving();
                    // Clear the txt file and adds headers.

                    // Saves the task list to the file, following the pre-defined format.
                    for (int i = 0; i < tasks.getSize(); i++) {
                        storage.writeToFile(tasks.getTask(i).toSaveString());
                    }

                    break;
                    // Exits while loop once "bye" is entered.

                } else {
                    // An unrecognizable command is detected.
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {

                ui.showLoadingError(e);

            } catch (StringIndexOutOfBoundsException e) {

                System.out.println(e.getMessage());

            } catch (NumberFormatException e) {

                System.out.println(e.getMessage());

            } catch (IOException e) {

                System.out.println(e.getCause());

            }

            fullCommand = in.nextLine().trim();
            output = "";
        }

    }

    /**
     * Main method to run Duke.
     *
     * @param args Main entry point.
     */
    public static void main(String args) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }

    /**
     * possibleTasks is an enumeration of the constant, pre-defined, recognizable commands.
     */
    enum PossibleTasks {
        BYE,
        LIST,
        DONE,
        DELETE,
        EVENT,
        TODO,
        DEADLINE,
        FIND
    }

    /**
     * Default constructor to support seedu.duke.Launcher of javaFX.
     */
    public Duke(){

    }

}



