import java.io.IOException;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setPath("CurrentTaskList.txt");
        duke.run();
    }
}
