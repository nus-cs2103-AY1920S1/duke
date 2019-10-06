package duke;
import duke.exception.InvalidTaskException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * Main class for Duke chatbot.
 * Handles user input.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        try {
            Ui ui = new Ui();
            Storage storageHandler = new Storage();
            TaskList tasks = new TaskList(storageHandler.load());
            Parser parser = new Parser();
            Executor executor = new Executor(ui, storageHandler, tasks, parser);
            executor.start();
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
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
    }

//
//
//
//
//    /**
//     * Adds and subsequently displays a new todo created from the user input.
//     *
//     * @param userInput string representing the todo the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a todo.
//     */
//    private static void addAndDisplayNewTodo(String userInput) throws InvalidTaskException {
//        Todo newTodo = new Todo(userInput.substring("todo ".length()));
//        tasks.add(newTodo);
//        storageHandler.save(tasks);
//        displayAddedTask(newTodo);
//    }
//
//    /**
//     * Adds and subsequently displays a new deadline created from the user input.
//     *
//     * @param userInput string representing the deadline the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a deadline.
//     */
//    private static void addAndDisplayNewDeadline(String userInput) throws InvalidTaskException {
//        String[] descriptionAndDate = userInput.substring("deadline ".length()).split("/by ", 2);
//        String description = descriptionAndDate[0];
//        LocalDateTime dueDate = LocalDateTime
//                .parse(descriptionAndDate[1].trim(), Deadline.dueDateFormat);
//        Deadline newDeadline = new Deadline(description, dueDate);
//        tasks.add(newDeadline);
//        storageHandler.save(tasks);
//        displayAddedTask(newDeadline);
//    }
//
//    /**
//     * Adds and subsequently displays a new event created from the user input.
//     *
//     * @param userInput string representing the event the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a event.
//     */
//    private static void addAndDisplayNewEvent(String userInput) throws InvalidTaskException {
//        String[] descriptionAndDateTimes = userInput.substring("event ".length()).split("/at ", 2);
//        String[] startAndEndDateTimes = descriptionAndDateTimes[1].split("-", 2);
//        String description = descriptionAndDateTimes[0];
//        LocalDateTime startDateTime = LocalDateTime
//                .parse(startAndEndDateTimes[0].trim(), Event.eventDateTimeFormat);
//        LocalDateTime endDateTime = LocalDateTime
//                .parse(startAndEndDateTimes[1].trim(), Event.eventDateTimeFormat);
//
//        Event newEvent = new Event(description, startDateTime, endDateTime);
//        tasks.add(newEvent);
//        storageHandler.save(tasks);
//        displayAddedTask(newEvent);
//    }
//
//    /**
//     * Deletes and subsquently displays the task at the input task index.
//     *
//     * @param taskIndex task index of the task to be deleted.
//     */
//    private static void deleteAndDisplayTask(int taskIndex) {
//        Task task = tasks.get(taskIndex);
//        tasks.remove(taskIndex);
//        storageHandler.save(tasks);
//        dukeReply("I have removed the following task:\n  " + task + "\nNow you have " +
//                tasks.size() + " tasks in the list.");
//    }


}
