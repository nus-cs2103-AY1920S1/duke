package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


/**
* Main class of the programme.
*/ 

public class Duke extends Application {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private boolean exit = false;

    /**
     * Constructor method for Duke.
     */
    public Duke()  {
        ui = new Ui();
        this.storage = new Storage("./src/main/java/data/duke.txt");
        this.tasks = new TaskList(storage.load());
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

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to get appropriate output based on user input.
     * @param input Input that user typed into GUI.
     * @return String for Duke to display as a response to the user input.
     */
    String getResponse(String input) {
        try {
            Command nextCommand = Parser.parseCommand(input);
            if (nextCommand instanceof ExitCommand) {
                this.exit = true;
            }
            String output = nextCommand.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return ui.printException(e);
        } finally {
            if (this.exit) {
                this.exitApplication();
            }
        }
    }

    /**
     * Method to close application when user inputs "bye".
     */
    public void exitApplication() {
        Timer countdown = new Timer();
        TimerTask onExit = new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        };

        countdown.schedule(onExit, 2000);
    }
}
