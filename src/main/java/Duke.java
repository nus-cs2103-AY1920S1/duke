import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents Duke, a personal assistant ChatBot that helps a person to keep track of various things.
 */
public class Duke extends Application {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises a new Duke object.
     *
     */
    public Duke() {

        try {
            this.ui = new UI();
            this.storage = new Storage("/Users/joshuaseet/Desktop/CS2103/Duke/src/main/duke.txt");
            ArrayList<Task> existing = storage.readFileContents();
            this.taskList = new TaskList(existing);
            this.storage.writeToFile("");
        } catch (Exception e){
            System.out.println(e);
        }

    }

    /**
     * Runs the program. It will start from here.
     *
     * @param args
     * @throws IOException if there is an issue reading the file.
     */
    public static void main(String[] args) throws IOException  {

       new Duke().run();

    }

    @Override
    public void start(Stage stage) throws IOException {
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

        // more code to be added here later

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

//////////
        dialogContainer.getChildren().add(getDialogLabel(ui.welcomeApp()));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.welcomeApp(), duke));
//////////

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.welcomeApp(), duke) );

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {

            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {

            }
        });

    }

    public Label getDialogLabel(String text) {
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
    public void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        String[] arr = input.split(" ");
        String command = arr[0];
        String task = "";
        for(int i=1; i<arr.length; i++) {
            task = task + " " + arr[i];
        }
        try {
            switch (command) {
                case "list":
                    return taskList.currentList();

                case "todo":
                    String currentTask = task.trim();
                    if (!currentTask.isEmpty()) {
                        ToDo newTodo = new ToDo(task);
                        return taskList.addTaskApp(newTodo);
                    } else {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                case "deadline":
                    String wholeTask = task.trim();
                    int index = wholeTask.indexOf('/');
                    if (index > 0) {
                        //what the task is
                        String description = wholeTask.substring(0, index).trim();
                        //when it is due by
                        String date = wholeTask.substring(index + 4).trim();
                        String f = ui.getFormattedDate(date);
                        if (description.isEmpty() || date.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                    " It must be in the format <description> /by <date/time> ");
                        } else {
                            Deadline newDeadlineTask = new Deadline(description, f);
                            return taskList.addTaskApp(newDeadlineTask);
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                " It must be in the format <description> /by <date/time> ");
                    }

                case "event":
                    String eventAndDate = task.trim();
                    int index2 = eventAndDate.indexOf('/');
                    if (index2 > 0) {
                        //what the task is
                        String eventDescr = eventAndDate.substring(0, index2).trim();
                        //when it is due by
                        String dateAndTime = eventAndDate.substring(index2 + 4).trim();
                        String f1 = ui.getFormattedDate(dateAndTime);
                        if (eventDescr.isEmpty() || dateAndTime.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                    " It must be in the format <description> /at <start and end of specific time> ");
                        } else {
                            Event newEventTask = new Event(eventDescr, f1);
                            return taskList.addTaskApp(newEventTask);
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                " It must be in the format <description> /at <start and end of specific time> ");
                    }

                case "delete":
                    int taskToBeDeleted = Integer.parseInt(task.trim()) - 1;
                    return taskList.deleteTaskApp(taskToBeDeleted);

                case "done":
                    int taskNumber = Integer.parseInt(task.trim()) - 1;
                    return taskList.completeTaskApp(taskNumber);


                case "find":
                    String keyword = task.trim();
                    ArrayList<Task> found = taskList.find(keyword);
                    int n = 1;

                    if (found.isEmpty()) {
                        return "None found";
                    } else {
                        StringBuilder str = new StringBuilder();
                        for (Task item : found) {
                            str = str.append(n + "." + item);
                            n++;
                        }
                        return str.toString();
                    }


                case "bye":
                    writeToFile();
                    return ui.goodbyeApp();

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
        return "";
    }


        //code exits here when the user enters the command "bye"



    /**
     * Runs the program by prompting user to enter the command. The program will then carry out the program if the command
     * is valid. If not, it will throw a Duke exception.
     *
     * @throws IOException if there is an issue reading the .txt file to recover the previous list.
     */
    public void run() throws IOException {
        this.ui.welcome();
        String command = ui.promptEntry();
        ui.handleCommand(command, this.taskList);
        //after all commands are done, we will save the updated list into the txt file.
        writeToFile();
        this.ui.goodbye();
    }

    public void writeToFile() throws IOException {
        ArrayList<Task> updated = this.taskList.getList();
        if (!updated.isEmpty()) {
            for (Task task : updated) {
                if (storage.isFileEmpty()) {
                    storage.writeToFile(task.toTextFile());
                } else {
                    storage.appendToFile(task.toTextFile());
                }
            }
        }
    }


}
