import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a task manager.
 * A Duke object has a file with the list of tasks.
 * Tasks can be added, deleted and updated.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("/Users/michelleyong/Desktop/duke/data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        } catch (FileNotFoundException f) {
            System.out.println(f);
        } catch (ParseException p) {
            System.out.println(p);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException, ParseException, DukeException {
        Command command = Parser.getCommand(input);
        return command.execute(storage, taskList, ui);
    }

    /**
     * Runs the task manager.
     *
     * @throws IOException If an input or output exception occurred.
     * @throws ParseException If a parse exception occurred.
     */
    /*
    public void run() throws IOException, ParseException {
        ui.printHello();
        Parser parser = new Parser();

        while (sc.hasNext()) {
            String desc = parser.nextCommandDesc();
            String[] commandArr = parser.breakDownCommand(desc);
            String command = parser.getCommand(commandArr);
            if (command.equals("todo")) {
                try {
                    if (desc.length() <= 4) {
                        throw new DukeException();
                    }
                    Todo todo = parser.getTodo(desc);
                    taskList.addTask(todo);
                    ui.printTaskAdded(todo, taskList.getSize());
                    storage.appendTaskToFile(todo);
                } catch (DukeException e) {
                    ui.printTodoError();
                }
            } else if (command.equals("deadline")) {
                try {
                    if (desc.length() <= 8) {
                        throw new DukeException();
                    }
                    String[] descArr = parser.breakDownDescription(desc);
                    Date date = parser.getDate(descArr, storage);
                    Deadline deadline =
                            new Deadline(parser.getDeadlineDesc(descArr), date);
                    taskList.addTask(deadline);
                    ui.printTaskAdded(deadline, taskList.getSize());
                    storage.appendTaskToFile(deadline);
                } catch (DukeException e) {
                    ui.printDeadlineError();
                }
            } else if (command.equals("event")) {
                try {
                    if (desc.length() <= 5) {
                        throw new DukeException();
                    }
                    String[] descArr = parser.breakDownDescription(desc);
                    Date date = parser.getDate(descArr, storage);
                    Event event = new Event(parser.getEventDesc(descArr), date);
                    taskList.addTask(event);
                    ui.printTaskAdded(event, taskList.getSize());
                    storage.appendTaskToFile(event);
                } catch (DukeException e) {
                    ui.printEventError();
                }
            } else if (command.equals("list")) {
                taskList.printList();
            } else if (command.equals("done")) {
                try {
                    if (desc.length() <= 4) {
                        throw new DukeException();
                    }
                    int num = parser.getTaskNum(commandArr);
                    if (num >= taskList.getSize()) {
                        throw new DukeException();
                    }
                    Task task = taskList.markTaskAsDone(num);
                    ui.printTaskDone(task);
                    storage.updateTaskInFile(taskList.getList());
                } catch (DukeException e) {
                    ui.printNoSuchTaskError();
                }
            } else if (command.equals("bye")) {
                ui.printBye();
                break;
            } else if (command.equals("delete")) {
                try {
                    if (desc.length() <= 6) {
                        throw new DukeException();
                    }
                    int num = parser.getTaskNum(commandArr);
                    if (num >= taskList.getSize()) {
                        throw new DukeException();
                    }
                    Task removed = taskList.removeTask(num);
                    ui.printTaskRemoved(removed, taskList.getSize());
                    storage.updateTaskInFile(taskList.getList());
                } catch (DukeException e) {
                    ui.printNoSuchTaskError();
                }
            } else if (command.equals("find")) {
                String toFind = parser.getKeyWord(desc);
                ArrayList<Task> tasks = taskList.getList();
                ArrayList<Task> taskFound = new ArrayList<>();
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    if (t.hasKeyword(toFind)) {
                        taskFound.add(t);
                    }
                }
                taskList.printTaskFound(taskFound);
            } else {
                try {
                    throw new DukeException();
                } catch (DukeException e) {
                    ui.printUnknownCommandError();
                }
            }
        }
        sc.close();
    }*/

    /*
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

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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
        });*/

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*private void handleUserInput() {
        String userText = new String(userInput.getText());
        String dukeText = new String(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new Image(user)),
                DialogBox.getDukeDialog(dukeText, new Image(duke))
        );
        userInput.clear();
    }*/
}