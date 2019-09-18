package seedu.duke;

import seedu.duke.commands.MasterCommand;
import seedu.duke.commands.base.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Parser;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.util.Scanner;

/**
 * Main class to drive the Duke application.
 */
public class Duke {

    private UI ui;
    private Storage storageHandler;
    private TaskList taskList;
    private Trivia trivia;
    private static final String savedPath
            = "C:\\Users\\drago\\Documents\\MEGA\\Work\\Uni\\Year 2\\CS2103T\\duke\\data\\duke.txt";
    private static final String triviaPath
            = "C:\\Users\\drago\\Documents\\MEGA\\Work\\Uni\\Year 2\\CS2103T\\duke\\data\\trivia.json";

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String command = input;
        MasterCommand c;
        try {
            if (Parser.getMode().equals("base")) {
                c = Parser.parse(command);
                return c.execute(taskList, ui, storageHandler);
            } else if (Parser.getMode().equals("trivia")) {
                c = Parser.parse(command);
                return c.execute(trivia, ui, storageHandler);
            } else {
                return "Well that sucks";
            }
        } catch (DukeException | TriviaException ex) {
            return ui.showError(ex.getMessage());
        }
    }

        /**
         * Main constructor for Duke that specifies the path of the save file.
         *
         * @param savedPath Path of the duke.txt file used to store information.
         */
    public Duke(String savedPath) {
        ui = new UI();
        storageHandler = new Storage(savedPath, triviaPath);
        try {
            taskList = new TaskList(storageHandler.loadList());
            trivia = storageHandler.loadTrivia();
        } catch (DukeException ex) {
            ui.cannotLoad();
            taskList = new TaskList();
        } catch (TriviaException ex) {
            ui.cannotLoadTrivia();
            trivia = new Trivia();
        }
    }

    public Duke() {
        ui = new UI();
        storageHandler = new Storage(savedPath, triviaPath);
        try {
            taskList = new TaskList(storageHandler.loadList());
            trivia = storageHandler.loadTrivia();
        } catch (DukeException ex) {
            System.out.println(ui.cannotLoad());
            taskList = new TaskList();
        } catch (TriviaException ex) {
            ui.cannotLoadTrivia();
            trivia = new Trivia();
        }
    }

    /**
     * Runs the main logic of the application.
     */
    public void run() {
        ui.greet();

        Scanner sc = new Scanner(System.in);
        String command;
        boolean isExit = false;
        while (!isExit) {
            try {
                command = sc.nextLine();
                if (Parser.getMode().equals("base")) {
                    MasterCommand c = Parser.parse(command);
                    System.out.println(c.execute(taskList, ui, storageHandler));
                    isExit = c.isExit();
                } else {
                    MasterCommand c = Parser.parse(command);
                    System.out.println(c.execute(trivia, ui, storageHandler));
                }
            } catch (DukeException | TriviaException ex) {
                System.out.println(ui.showError(ex.getMessage()));
            }
        }
    }

    /**
     * Public static void main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke(savedPath).run();
    }

}
