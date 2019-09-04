package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Parser;
import duke.command.Command;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> textEntered;
    private Storage storage;
    private TaskList tasks;
    private Ui userIF;

    /**
     * Constructor for Duke which is called in main. Creates a new Ui and Storage. If Storage can't find the file
     * duke.txt it will throw an exception, in which case a new TaskList will be created that is empty.
     */
    public Duke() {
        this.userIF = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.outputFileContents());
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }

//    public static void main(String[] args) {
//        new Duke().run();
//    }

    /**
     * First greets the user then enters a loop which includes the Command Pattern, reading the user input
     * using Ui then calling command.execute().
     */
//    public void run() {
//        userIF.printHello();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = userIF.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, userIF, storage);
//                isExit = c.shouldExit();
//            } catch (DukeException e) {
//                userIF.printError(e.getMessage());
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //userIF.printHello();
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, userIF, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String sayHello() {
        return userIF.printHello();
    }

}
