import duke.exception.DukeException;
import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.text.Parser;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.SequenceInputStream;

public class Duke {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;
    protected String output;
    protected boolean isExit = false;

    public Duke(){};
    /**
     * Duke Constructor that takes in a filePath where application data would be stored.
     *
     * @param filePath Path of storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.setTasks(tasks);
        }
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke("data/duke.txt");
        newDuke.run();
    }

    /**
     * Main run method. Application is in a constant loop until bye command changes isExit to true and
     * break out of the loop.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                output = c.execute(tasks, ui, storage);
                System.out.println(output);
                isExit = c.isExit();
            } catch (DukeException e) {
                output = ui.showError(e.getMessage());
            } finally {
                output = ui.showLine();
            }
        }
    }

    public boolean getExit() {
        return isExit;
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            stringBuilder.append(c.execute(tasks, ui, storage));
            isExit = c.isExit();
        } catch (DukeException e) {
            stringBuilder.append(ui.showError(e.getMessage()));
        }
        return stringBuilder.toString();
    }
}
