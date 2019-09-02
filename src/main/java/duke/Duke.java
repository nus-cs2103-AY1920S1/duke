package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import duke.command.Command;
import duke.error.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    // JavaFx stuff
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Constructor.
     */
    public Duke() {
        // TODO: Don't hardcode
        this.storage = new Storage("/Users/joshuawong/Documents/NUS/Y2S1/CS2103/duke/data/duke.txt"); 
        this.ui = new Ui();
        try {
            this.tasks = this.storage.load();    
        } catch (IOException e) {
            this.ui.printReadError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program.
     */
    private void run() {
        this.ui.printWelcome();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                String strCommand = this.ui.readCommand();
                Command c = Parser.parse(strCommand);
                c.execute(this.tasks, this.ui, this.storage);
                shouldRun = !c.isExit();
            } catch (DukeException e) {
                this.ui.printError(e);
            } catch (ParseException e) {
                this.ui.print("Passed in an invalid date");
            } catch (NoSuchElementException e) {
                shouldRun = false;
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
}
