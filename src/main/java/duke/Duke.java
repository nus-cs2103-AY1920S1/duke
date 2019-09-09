package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;*/

/**
 * A task list that supports several basic features:
 * 1) Addition and deletion of three types of task.
 * 2) Ability to mark tasks as done.
 * 3) Ability to search for expressions in given tasks.
 * 4) Ability to print current list of tasks.
 */
public class Duke {
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
    
    public static boolean isExitRunLoop;
    
    /*private ScrollPane scrollPane;
    
    private VBox dialogContainer;
    
    private TextField userInput;
    
    private Button sendButton;
    
    private Scene scene;*/
    
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
    private void run() {
        ui.print(ui.showHello());
        isExitRunLoop = false;
        while (!isExitRunLoop) {
            try {
                String input = ui.getNextLine();
                Command command = Parser.parse(input);
                ui.print(command.execute(taskList, ui, storage));
            } catch (DukeException | IOException e) {
                ui.print(ui.showError(e));
            }
        }
    }
    /*
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
    */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setPath("CurrentTaskList.txt");
        duke.run();
    }
}
