package duke.main;

import duke.command.Command;
import duke.controllers.MainWindow;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets response to be returned through duke output
     */
 public String getResponse(String input) {
     String[] wordArray = input.split(" ");
     String command = wordArray[0];
     if (command.equals("list")) {
         try {
             Command c = Parser.parse(command);
             return c.execute(tasks, ui, storage);
         } catch (DukeException e) {
             return e.getMessage();
         } catch (ParseException e) {
             return e.getMessage();
         }
     } else {
         String remainingWords = input.substring(command.length());
         try {
             Command c = Parser.parse(command);
             System.out.println(remainingWords);
             ui.storeRemaining(remainingWords);
             return c.execute(tasks, ui, storage);
         } catch (DukeException e) {
             return e.getMessage();
         } catch (ParseException e) {
             return e.getMessage();
         }
     }
 }

    /**
     * Closes the application upon user input of bye
     */
    public static void exit() {
        Timer countdown = new Timer();
        TimerTask onExit = new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        };

        countdown.schedule(onExit, 2500);
    }

}
