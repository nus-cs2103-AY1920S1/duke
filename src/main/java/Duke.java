import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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

    /**
     * Initialises a new Duke object.
     *
     * @throws FileNotFoundException if file is not found.
     * @throws IOException if there is an issue reading the file.
     */
    public Duke() throws FileNotFoundException, IOException {

        this.ui = new UI();
        this.storage = new Storage("/Users/joshuaseet/Desktop/CS2103/Duke/src/main/duke.txt");
        ArrayList<Task> existing = storage.readFileContents();
        this.taskList = new TaskList(existing);
        this.storage.writeToFile("");

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
    }

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
        this.ui.goodbye();
    }


}
