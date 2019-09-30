package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Parser;
import duke.command.Storage;
import duke.DukeException;
import duke.task.Task;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the main class of the programme.
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private HashSet<Task> set;

    public Duke() {}

    /**
     * Constructs a duke object that takes in filePath which is the
     * path from which the file is to be loaded.
     * @param filePath The path of the file from which the file is loaded.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            this.set = this.storage.getSet();
            this.storage.setList(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
            this.storage.setList(taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private TaskList getTaskList() {
        return this.taskList;
    }

    private Ui getUi() {
        return this.ui;
    }

    private Storage getStorage() {
        return this.storage;
    }

    private HashSet<Task> getSet() {
        return this.set;
    }

    /**
     * Start getting user input and update storage.
     */
    String getResponse(String input) {
        String response;
        Duke duke = new Duke("tasks.txt");

        Storage x = duke.getStorage();
        assert x != null : "storage should not point to null pointer";
        TaskList y = duke.getTaskList();
        assert y != null : "TaskList should not point to null pointer";
        Ui z = duke.getUi();
        assert z != null : "Ui should not point to null pointer";

        Parser parser = new Parser(duke.getTaskList(), duke.getUi(), duke.getStorage(), duke.getSet());

        try {
            response = parser.parseLineInput(input);
            assert !response.equals("") : "Response should not be empty";
            return response;
        } catch (DukeException e) {
            response = e.getMessage();
            return response;
        } catch (ParseException e) {
            response = e.getMessage();
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
