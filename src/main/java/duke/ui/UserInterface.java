package duke.ui;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.SearchCommand;
import duke.command.DukeUnknownCommandException;

import duke.io.Parser;
import duke.io.Storage;

import duke.DukeException;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

/**
 * The user interface which the user uses to interacts with duke, and manipulate the task list
 */
public class UserInterface extends Application {
    private TaskList taskList;
    private Storage storage;
    private String dukeOutput;

    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainContainer;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/fatCat.png"));
    private DialogContainer dialogContainer;
    private Input userInput;

    /**
     * Constructs the user interface for Duke
     */
    public UserInterface() {
        //initialze storage
        storage = new Storage("DukeSave01.txt");

        // load default exisiting task list if any
        taskList = new TaskList();

        dukeOutput = new String();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        //intialize scrollable dialog container (VBox+ scrollpane)
        dialogContainer = new DialogContainer();

        //intialize input field (button + text box)
        EventHandler send = (event) -> handleUserInput();
        userInput = new Input(send);

        //intialize container and add subnodes
        mainContainer = new AnchorPane();
        mainContainer.getChildren().addAll(dialogContainer, userInput);

        //initlaize scene
        scene = new Scene(mainContainer);
        //scene.setFill(Color.TRANSPARENT);

        // load tasklist from storage if exists
        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e) {
        }

        // set up stage
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        //stage.initStyle(StageStyle.TRANSPARENT);

        // positions scene elements on stage
        mainContainer.setPrefSize(400, 600);
        scene.getWindow().centerOnScreen();

        // position dialog container
        AnchorPane.setTopAnchor(dialogContainer, 1.0);
        AnchorPane.setLeftAnchor(dialogContainer, 0.0);
        AnchorPane.setRightAnchor(dialogContainer, 1.0);

        //position input field
        userInput.setPrefWidth(400);
        AnchorPane.setBottomAnchor(userInput,8.0);
        AnchorPane.setRightAnchor(userInput,1.0);
        AnchorPane.setLeftAnchor(userInput,1.0);

        stage.show();
    }

    private void handleUserInput() {
        try {
            String response = getResponse(userInput.getText());
            dialogContainer.displayDialog(userInput.getText(), response);
        } catch (DukeException e) {
            dialogContainer.displayError(userInput.getText(), e);
        } finally {
            userInput.clearText();
        }
    }

    private String getResponse(String input) throws DukeException {
        executeCommand(Parser.parseAsCommand(input));
        String response = dukeOutput;
        dukeOutput = new String();
        return response;
    }

    /**
     * Attempts to execute the command given
     * @param command The command to be executed
     * @throws DukeException The exception thrown when an error occurs while trying to execute the command
     */
    public void executeCommand(Command command) throws DukeException {
        // all commands passed to this method have all required parameter non-empty
        switch (command.getClass().getSimpleName()) {
        case "AddTaskCommand":
            executeAddTaskCommand((AddTaskCommand) command);
            break;
        case "CompleteTaskCommand":
            executeCompleteTaskCommand((CompleteTaskCommand) command);
            break;
        case "DeleteTaskCommand":
            executeDeleteTaskCommand((DeleteTaskCommand) command);
            break;
        case "ShowListCommand":
            executeShowListCommand();
            break;
        case "ExitCommand":
            executeExitCommand();
            break;
        case "SearchCommand":
            executeSearchCommand((SearchCommand) command);
            break;
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private void executeAddTaskCommand(AddTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);
        Task task;

        switch (Command.getTypeOf(command)) {
        case TYPE_ADD_TODO:
            task = new ToDo(parameters[0]);
            break;
        case TYPE_ADD_DEADLINE:
            try {
                task = new Deadline(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException ex) {
                task = new Deadline(parameters[0], parameters[1]);
            }
            break;
        case TYPE_ADD_EVENT:
            try {
                task = new Event(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException e) {
                task = new Event(parameters[0], parameters[1]);
            }
            break;
        default:
            throw new DukeException("This task type is not supported yet");
        }

        taskList.add(task);

        storage.save(taskList);

        dukeOutput = String.format(
                "Got it! I've added this task to the list:\n%s\nNow you have %d task(s) in your list.",
                task.toString(),
                taskList.size());
    }


    private void executeCompleteTaskCommand(CompleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);

        Task task = taskList.complete(parameters[0]);

        storage.save(taskList);
        dukeOutput = String.format("Got it! I've marked this task as done:\n%s", task.toString());
    }

    private void executeDeleteTaskCommand(DeleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);

        Task task = taskList.delete(parameters[0]);

        storage.save(taskList);
        dukeOutput = String.format(
                "Got it! I've removed this task from the list:\n%s\nNow you have %d task(s) in your list.",
                task.toString(),
                taskList.size());
    }

    private void executeSearchCommand(SearchCommand command) {
        String[] parameters = Command.getArgumentsUsed(command);
        ArrayList<Task> results = taskList.search(parameters[0]);
        int resultsCount = results.size();

        if (resultsCount > 0) {
            StringBuilder output = new StringBuilder();
            int width = Integer.toString(resultsCount).length();
            int count = 0;

            output.append("Here are the matching task(s) in your list:");

            for (Task task : taskList.search(parameters[0])) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

             dukeOutput = output.toString();
        } else {
             dukeOutput = "There are no matching tasks in your list!";
        }
    }

    private void executeShowListCommand() {
        int taskCount = taskList.size();

        if (taskCount < 1) {
             dukeOutput = "Your list is empty!";
        } else {
            StringBuilder output = new StringBuilder();
            int count = 0;
            int width = Integer.toString(taskCount).length();

            output.append("Here are the task(s) in your list:");

            for (Task task : taskList.list()) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

             dukeOutput = output.toString();
        }
    }

    private void executeExitCommand() {
        Label bye = new Label("GoodBye! Hope to see you again!\n(click to exit)");
        bye.setTextFill(Color.rgb(255,255,255));

        DialogBox box = DialogBox.getDukeNormalDialog(bye, new ImagePattern(duke));
        box.setOnMouseClicked((event) -> Platform.exit());

        Scene sc = new Scene(box);
        sc.setFill(Color.TRANSPARENT);

        Stage gbw = new Stage();
        gbw.setTitle("CLICK TO EXIT");
        gbw.setScene(sc);
        gbw.initOwner(primaryStage);
        gbw.initModality(Modality.APPLICATION_MODAL);
        gbw.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0);
        gbw.showAndWait();
    }
}
