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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
        storage = new Storage("/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt");
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
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        stage.setScene(scene);
//        stage.show();
    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        String userText = new String(userInput.getText());
//        String dukeText = new String(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new Image(user)),
//                DialogBox.getDukeDialog(dukeText, new Image(duke))
//        );
//        userInput.clear();
//    }

//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    protected String getResponse(String input) {
//        return "Duke heard: " + input;
//    }

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
        String check = "dummy";
        int taskCount;

        while (check.equals("bye") == false) {
            try {
                input = sc.nextLine();
                check = input.toLowerCase();
                if (check.equals("list")) {
                    System.out.println(ui.showListOfTask(tasks));
                    continue;
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
                    System.out.println(ui.doneAnnouncement(taskDone));
                } else if (userCommand.equals("find")) {
                    System.out.println(tasks.keywordSearch(taskDescription));
                } else if (userCommand.equals("delete")) {
                    int target = Integer.valueOf(taskDescription);
                    Task taskDelete;
                    if (tasks.size() >= target && target > 0) {
                        taskDelete = tasks.get((target - 1));
                        tasks.removeTask((target - 1));
                    } else {
                        throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                    }
                    Task.reduceTaskCount();
                    storage.updateLocalFile(tasks.get());
                    taskCount = Task.getTaskCount();
                    System.out.println(ui.deleteAnnouncement(taskDelete, taskCount));
                } else if (!check.equals("bye")) {
                    System.out.print(createTask(userCommand, due, taskDescription, storage, ui, tasks));
                }
            } catch (DukeException ex) {
                System.out.println("OOPS!!! " + ex.getMessage() + "\n");
            }
        }

        System.out.println(ui.sayYourGoodBye());
    }

    /**
     * Contains most of the operations of the Task Manager bot.
     */
    protected String getResponse(String input) {
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt";

        Scanner sc = new Scanner(System.in);
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
                Task.reduceTaskCount();
                storage.updateLocalFile(tasks.get());
                taskCount = Task.getTaskCount();
                return ui.deleteAnnouncement(taskDelete, taskCount);
            } else if (!check.equals("bye")) {
                return createTask(userCommand, due, taskDescription, storage, ui, tasks);
            } else if (check.equals("bye")) {
                return ui.sayYourGoodBye();
            }
        } catch (DukeException ex) {
            System.out.println("OOPS!!! " + ex.getMessage() + "\n");
        }

        return "Are you speaking in botlang? I don't understand what you are saying!";
    }

    /**
     * Converts user input in the form of String to the format of Date.
     * The Task Manager has the ability to read dates instead of taking dates as String.
     *
     * @param input The date/ time input in the form of String. It should follow either dd/MM/yyyy HHmm or HHmm format.
     * @return Date format of the input String.
     */
    private static Date convertStringToDate(String input) {
        Date date = new Date();
        try {
            if (input.length() <= 5) {
                SimpleDateFormat formatTimeOnly = new SimpleDateFormat("HHmm");
                date = formatTimeOnly.parse(input.trim());
                return date;
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.ENGLISH);
                date = format.parse(input);
                return date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
            Date dateDue = convertStringToDate(due);
            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            }
            t = new Deadline(taskDescription, dateDue);
            tasks.add(t);
            typeOfTask = "D";
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else if (userCommand.equals("event")) {
            String[] eventStartEnd = due.split("-", 2);
            Date start = convertStringToDate(eventStartEnd[0]);
            Date end = convertStringToDate(eventStartEnd[1]);

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

        int taskCount = Task.getTaskCount();
        return ui.newTaskAdded(t, taskCount);
    }
}
