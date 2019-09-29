package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

import java.io.FileNotFoundException;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

/**
 * Main class of Duke programme.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;


    /**
     * Inititalises the Duke class.
     */

    public Duke() {
        String filePath = "C:\\Users\\Khairul\\Desktop\\Computing Resources\\CS2103T\\duke\\data\\duke.txt";
        storage = new Storage(filePath);
        assert storage != null : "storage should hold an actual Storage object.";
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            tasks = new TaskList();
        }
    }

    /**
     * Gets a response in the form of a String after receiving user.
     * input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "c should hold an actual command object.";
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

}