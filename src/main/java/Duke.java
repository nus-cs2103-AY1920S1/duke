import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * Entry point of this project Duke. Duke is a Task manager that aims
 * to serve as an efficient way to manage our day to day tasks. It supports multiple types of task
 * such as todo reminders, tasks with a deadline and even an event.
 * The Duke task manager has many iterations, it is
 * continuously evolving and becoming smarter to cater to it users' needs.
 *
 * @author TuanDingWei
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Construct a Duke object, the Task manager bot.
     *
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/Users/TuanDingWei/Desktop/NUS_Academia" +
                "/CS2103/Individual_project/Duke/local/Tasks.txt");
        tasks = new TaskList(storage.load());
        ui.welcome();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void run() {
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt";

        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        String check = "dummy";
        int taskCount;
        while (check.equals("bye") == false) {
            System.out.println(execution(input));
        }
    }

    /**
     * Contains most of the operations of the Task Manager bot.
     */
    protected String getResponse(String input) {
        return execution(input);
    }

    private String execution(String input) {
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt";

        String check = "dummy";
        int taskCount;

        try {
            check = input.toLowerCase();
            if (check.equals("list")) {
                return ui.showListOfTask(tasks);
            }

            Parser parser = new Parser(input);
            String userCommand = parser.getUserCommand();
            String due = parser.getDue();
            String taskDescription = parser.getTaskDescription();

            if (userCommand.equals("done")) {
                int target = Integer.valueOf(taskDescription);
                Task taskDone;
                if (tasks.size() >= target && target > 0) {
                    taskDone = tasks.get(target - 1);
                } else {
                    throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                }
                taskDone.markAsDone();
                storage.updateLocalFile(tasks.get());
                return ui.doneAnnouncement(taskDone);
            } else if (userCommand.equals("find")) {
                return tasks.keywordSearch(taskDescription);
            } else if (userCommand.equals("delete")) {
                int target = Integer.valueOf(taskDescription);
                Task taskDelete;
                if (tasks.size() >= target && target > 0) {
                    taskDelete = tasks.get((target - 1));
                    tasks.removeTask((target - 1));
                } else {
                    throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                }
                storage.updateLocalFile(tasks.get());
                taskCount = tasks.size();
                return ui.deleteAnnouncement(taskDelete, taskCount);
            } else if (!check.equals("bye")) {
                return createTask(userCommand, due, taskDescription, storage, ui, tasks);
            } else if (check.equals("bye")) {
                return ui.sayYourGoodBye();
            }
        } catch (DukeException ex) {
            System.out.println("OOPS!!! " + ex.getMessage() + "\n");
        }
        return "Sorry I don't understand what you are trying to tell me.";
    }

    /**
     * Performs the operation of creating a Task (Todo, Deadline, Task).
     * It also writes the list of tasks to the local storage.
     *
     * @param userCommand     Indicates the instruction (done, delete, etc) to the bot.
     * @param due             Provides the due date/ time of the task.
     * @param taskDescription Reflects the description of a task.
     * @param storage         The local storage for the Tasks as a Storage object.
     * @param ui              The User Interface object that performs all interactions with the user.
     * @param tasks           The list of Task that are temporary stored with the bot.
     * @return Gives a feedback to the user on the operation has performed after a command is given.
     * @throws EmptyToDoDescriptionException Indicates an empty description that should not be left empty for Todo.
     * @throws EmptyDescriptionException     Indicates an empty description that should not be left empty.
     * @throws UnknownCommandException       Indicates the inability of the bot to read the command that is given by the user.
     */
    public static String createTask(String userCommand, String due, String taskDescription, Storage storage, Ui ui, TaskList tasks) throws EmptyToDoDescriptionException, EmptyDescriptionException, UnknownCommandException {

        Task t;
        String typeOfTask = "";
        if (userCommand.equals("todo")) {
            if (taskDescription.equals("dummy")) {
                throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
            }
            t = new Todo(taskDescription);
            typeOfTask = "T";
            tasks.add(t);
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else if (userCommand.equals("deadline")) {
            Date dateDue = storage.convertStringToDate(due);
            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            }
            t = new Deadline(taskDescription, dateDue);
            tasks.add(t);
            typeOfTask = "D";
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else if (userCommand.equals("event")) {
            String[] eventStartEnd = due.split("-", 2);
            Date start = storage.convertStringToDate(eventStartEnd[0]);
            Date end = storage.convertStringToDate(eventStartEnd[1]);

            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a event cannot be empty.");
            }
            t = new Event(taskDescription, start, end);
            typeOfTask = "E";
            tasks.add(t);
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }

        assert tasks.size() > 0 : "List of tasks should not be empty.";
        int taskCount = tasks.size();
        return ui.newTaskAdded(t, taskCount);
    }
}
