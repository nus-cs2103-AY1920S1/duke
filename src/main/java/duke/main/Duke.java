package duke.main;

import duke.command.Command;
import duke.controllers.MainWindow;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
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
import java.text.ParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

//    /**
//     * Runs the entire logic of the program. showWelcome() displays the duke logo and greets
//     * the user. isExit boolean ensures that the while loop doesn't end until the user input of
//     * "bye" is encountered. User's input will be read through the ui and then parsed to give
//     * a Command object. The specific command will then be executed.
//     * This method has try and catch blocks within to help with catching both DukeException
//     * and ParseException
//     *
//     */
//    public void run() {
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } catch (ParseException e) {
//                System.out.println(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

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


//    /**
//     * Runs the main method to load the duke.txt file into the program and then calls the run method
//     * @param args
//     * @throws DukeException when an error of duke.main.Duke occurs
//     * @throws ParseException when a string is not parsed properly
//     */
//    public static void main(String[] args) throws DukeException, ParseException{
//        new Duke("/Users/lawnce/Desktop/duke/data/duke.txt").run();
//    }

}
