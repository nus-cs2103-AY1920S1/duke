import java.util.Scanner;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.Todo;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


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


    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String command) {
        Parser parser = new Parser();
        String contents = Storage.readFile();
        TaskList tasks = new TaskList();
        String message;

        String[] contentLines = contents.split("\n");

        for (String line : contentLines) {
            String[] lineTokens = line.split(" \\| ");
            if (lineTokens[0].equals("T")) {
                Task newTask = new Todo(lineTokens[2]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("D")) {
                Task newTask = new Deadline(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("E")) {
                Task newTask = new Event(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            }
        }

        String[] commandTokens = command.split(" ");

        assert commandTokens.length > 0 : "There are no command tokens";

        if (!parser.isBye(command)) {
            try {
                if (parser.isList(command)) {
                    String list = "Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    Storage.saveFile(tasks);
                    return list;
                } else if (parser.isDone(commandTokens)) {
                    int completedIndex = Integer.parseInt(commandTokens[1]) - 1;
                    message = "Nice! I've marked this task as done:\n  ";
                    Task completedTask = tasks.getTask(completedIndex);
                    completedTask.setCompleted();
                    message += completedTask;
                    Storage.saveFile(tasks);
                    return message;
                } else if (parser.isTodo(commandTokens)) {
                    if (commandTokens.length == 1) {
                        throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(command.substring(5));
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    Storage.saveFile(tasks);
                    return message;
                } else if (parser.isDeadline(commandTokens)) {
                    String[] deadlineTokens = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    Storage.saveFile(tasks);
                    return message;
                } else if (parser.isEvent(commandTokens)) {
                    String[] eventTokens = command.substring(6).split(" /at ");
                    Task newTask = new Event(eventTokens[0], eventTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    Storage.saveFile(tasks);
                    return message;
                } else if (parser.isDelete(commandTokens)) {
                    int removeIndex = Integer.parseInt(commandTokens[1]) - 1;
                    Task removeTask = tasks.getTask(removeIndex);
                    tasks.removeTask(removeIndex);
                    message = String.format(
                            "Noted. I've removed this task:\n  %sNow you have %d tasks in the list.",
                            removeTask,
                            tasks.getSize());
                    Storage.saveFile(tasks);
                    return message;
                } else if (parser.isFind(commandTokens)) {
                    String keyword = commandTokens[1];
                    TaskList tasksWithKeyword = new TaskList();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        if (tasks.getTask(i).getDescription().contains(keyword)) {
                            tasksWithKeyword.addTask(tasks.getTask(i));
                        }
                    }

                    String list = "Here are the matching tasks in your list:\n";
                    for (int i = 0; i < tasksWithKeyword.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    Storage.saveFile(tasks);
                    return list;

                } else {
                    throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        } else {

            Storage.saveFile(tasks);
            return "Bye. Hope to see you again soon!";

        }
    }

    /**
     * Main method of duke.Duke application.
     */

    public static void main(String[] args) {
        // Create a scanner to take in user input
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        String contents = Storage.readFile();

        System.out.println(
                Ui.formatMessage("Hello, I'm duke.Duke\nWhat can I do for you?")
        );

        TaskList tasks = new TaskList();
        String message;

        String[] contentLines = contents.split("\n");

        for (String line : contentLines) {
            String[] lineTokens = line.split(" \\| ");
            if (lineTokens[0].equals("T")) {
                Task newTask = new Todo(lineTokens[2]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("D")) {
                Task newTask = new Deadline(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("E")) {
                Task newTask = new Event(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            }
        }

        String command = sc.nextLine();
        String[] commandTokens = command.split(" ");

        while (!parser.isBye(command)) {
            try {
                if (parser.isList(command)) {
                    String list = "Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    System.out.println(Ui.formatMessage(list));
                } else if (parser.isDone(commandTokens)) {
                    int completedIndex = Integer.parseInt(commandTokens[1]) - 1;
                    message = "Nice! I've marked this task as done:\n  ";
                    Task completedTask = tasks.getTask(completedIndex);
                    completedTask.setCompleted();
                    message += completedTask;
                    System.out.println(Ui.formatMessage(message));
                } else if (parser.isTodo(commandTokens)) {
                    if (commandTokens.length == 1) {
                        throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(command.substring(5));
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isDeadline(commandTokens)) {
                    String[] deadlineTokens = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isEvent(commandTokens)) {
                    String[] eventTokens = command.substring(6).split(" /at ");
                    Task newTask = new Event(eventTokens[0], eventTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isDelete(commandTokens)) {
                    int removeIndex = Integer.parseInt(commandTokens[1]) - 1;
                    Task removeTask = tasks.getTask(removeIndex);
                    tasks.removeTask(removeIndex);
                    message = String.format(
                            "Noted. I've removed this task:\n  %sNow you have %d tasks in the list.",
                            removeTask,
                            tasks.getSize());
                    System.out.println(Ui.formatMessage(message));
                } else if (parser.isFind(commandTokens)) {
                    String keyword = commandTokens[1];
                    TaskList tasksWithKeyword = new TaskList();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        if (tasks.getTask(i).getDescription().contains(keyword)) {
                            tasksWithKeyword.addTask(tasks.getTask(i));
                        }
                    }

                    String list = "Here are the matching tasks in your list:\n";
                    for (int i = 0; i < tasksWithKeyword.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    System.out.println(Ui.formatMessage(list));

                } else {
                    throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(Ui.formatMessage(e.getMessage()));
            }
            command = sc.nextLine();
            commandTokens = command.split(" ");
        }

        Storage.saveFile(tasks);

        System.out.println(
                Ui.formatMessage("Bye. Hope to see you again soon!")
        );
    }


}
