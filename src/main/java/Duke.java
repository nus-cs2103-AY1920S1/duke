import java.io.IOException;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A task list that supports several basic features:
 * 1) Addition and deletion of three types of task.
 * 2) Ability to mark tasks as done.
 * 3) Ability to search for expressions in given tasks.
 * 4) Ability to print current list of tasks.
 */
public class Duke extends Application {
    /**
     * The TaskList object which abstracts out a list of tasks.
     */
    private TaskList taskList;
    
    /**
     * The Storage object which loads and writes data to the hard drive.
     */
    private Storage storage;
    
    /**
     * The Ui object which scans input and prints feedback to the user.
     */
    private Ui ui;
    
    private ScrollPane scrollPane;
    
    private VBox dialogContainer;
    
    private TextField userInput;
    
    private Button sendButton;
    
    private Scene scene;
    
    /**
     * Initializes a Duke object.
     * The Duke constructor has no parameters due to a quirk in javafx.application.Application, which does not work
     * with a constructor with parameters. This was the best workaround I could find after 4 days of trying.
     */
    public Duke() {
    
    }
    
    /**
     * Initializes the Ui, Storage, and TaskList objects.
     * This method serves as a proxy for the Duke constructor method, which is left empty due to a quirk in
     * javafx.application.Application, which does not work with a constructor with parameters.
     *
     * @param filePath The file path of the hard drive location to read and write from, as a String.
     */
    private void setPath(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadSavedList());
        } catch (IOException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }
    
    /**
     * Scans and parses commands given by the user.
     * Modifies the Tasks in the TaskList object based on the commands received by the user.
     */
    public void run() {
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getNextLine();
                String instruction = Parser.parseInstruction(input);
                if (instruction.equals("bye")) {
                    ui.printBye();
                    isExit = true;
                } else if (instruction.equals("list")) {
                    taskList.printList();
                } else if (instruction.equals("done")) {
                    int index = Parser.parseIndex(input);
                    taskList.markTask(index);
                    storage.writeSavedList(taskList.getList());
                } else if (instruction.equals("delete")) {
                    int index = Parser.parseIndex(input);
                    taskList.deleteTask(index);
                    storage.writeSavedList(taskList.getList());
                } else if (instruction.equals("find")) {
                    String searchPhrase = Parser.parseSearchPhrase(input);
                    ui.printFoundTasks(taskList.findTasks(searchPhrase));
                } else if (instruction.equals("todo") || instruction.equals("deadline")
                        || instruction.equals("event")) {
                    try {
                        if (instruction.equals("todo")) {
                            String taskDescription = Parser.parseDescription(input, true);
                            ui.testEmptyDescription(taskDescription);
                            taskList.createToDo(taskDescription);
                            storage.writeSavedList(taskList.getList());
                        } else {
                            String taskDescription = Parser.parseDescription(input, false);
                            ui.testTimeFormat(taskDescription);
                            String taskContent = Parser.parseContent(taskDescription);
                            LocalDateTime taskTime = Parser.parseTime(taskDescription);
                            ui.testEmptyDescription(taskContent);
                            if (instruction.equals("deadline")) {
                                taskList.createDeadline(taskContent, taskTime);
                            } else {
                                taskList.createEvent(taskContent, taskTime);
                            }
                            storage.writeSavedList(taskList.getList());
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                    }
                    ui.printSize(taskList.getSize());
                } else {
                    throw new InvalidInstructionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IOException | DukeException e) {
                ui.showError(e);
            }
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
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    
        userInput.setPrefWidth(325.0);
    
        sendButton.setPrefWidth(55.0);
    
        AnchorPane.setTopAnchor(scrollPane, 1.0);
    
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
    
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    
        // more code to be added here later
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setPath("CurrentTaskList.txt");
        duke.run();
    }
}
