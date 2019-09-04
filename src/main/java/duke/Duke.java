package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.UiText;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


import java.io.FileNotFoundException;

/**
 * Duke is  a Persional Assistant Chatbot that helps a person to keep track of various things.
 * @author Yang Shuting
 */

public class Duke extends Application implements EventHandler {
    private Storage storage;
    private TaskList tasks;
    private UiText ui;
    Button button;

    /**
     * constructor to create a Duke Chatbot
     *
     * @param filePath The path for the text file that storages
     *                 the data
     */
    public Duke(String filePath) {
        ui = new UiText();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    private void run() {
        try {
            ui.greeting();
            storage.printFileContents();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    ui.showLine();
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.printlnMsg(e.getMessage());
                } finally {
                    ui.showLine();
                }
            }
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }


    public static void main(String[] args) {
        //Duke duke = new Duke("src/main/data/duke.txt");
        //duke.run();
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DUKE");
        button = new Button();
        button.setText("Submit");
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == button) {
            System.out.println
        }
    }
}
