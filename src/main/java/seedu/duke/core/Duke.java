package seedu.duke.core;

import seedu.duke.command.ByeCommandCli;
import seedu.duke.command.Command;
import seedu.duke.command.UnknownCommand;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;


/**
 * Project Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * storage attribute is a Storage object, which helps read and write data to the text file
 *
 */
public class Duke {

    private Storage taskStorage;
    private Storage statStorage;

    private TaskList tasks;
    private Statistic stats;

    private CommandLineUi cli;
    private GraphicalUi gui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/117.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/commando.jpg"));

    private String taskFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt";
    private String statFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\stats.txt";

    /**
     * Default constructor to support seedu.duke.core.Launcher of javaFX.
     */
    public Duke() {
        taskStorage = new Storage(taskFilePath);
        statStorage = new Storage(statFilePath);

        gui = new GraphicalUi();
        cli = new CommandLineUi();
    }

    /**
     * Instantiates a Duke object with filePath.
     *
     * @param filePath absolute filepath of the where the text file is stored.
     *                 Eg "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Duke(String filePath) {
        taskStorage = new Storage(filePath);
        statStorage = new Storage(this.statFilePath);

        gui = new GraphicalUi();
        cli = new CommandLineUi();
    }

    /**
     * Returns the response for the user input.
     * Represents the GUI of Duke.
     *
     * @param input Command input string.
     * @return Duke's response.
     */
    public String getResponse(String input) {

        try {

            loadSavedDate();;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e ) {
            System.out.println(e.getMessage());
        }

        String outputString = "";
        Command command = new UnknownCommand();

        try {

            command = Parser.getCommand(input, gui);
            outputString = command.execute(input, gui, tasks, taskStorage, stats, statStorage);

            // Saves tasks to txt file before returning output to GUI.
            this.taskStorage.clearTaskFileBeforeSaving();
            for (int i = 0; i < this.tasks.getSize(); i++) {
                this.taskStorage.writeToTaskFile(this.tasks.getTask(i).toSaveString());
            }

            // Saves stats file before returning output.
            statStorage.saveStatFile(stats);


        } catch (DukeException e) {

            DukeErrorInterface dei = (e1) -> e1.getMessage();
            outputString = dei.getLoadingError(e);

        } catch (IOException e) {

            outputString = e.getMessage();

        }

        return outputString;
    }

    /**
     * Executes the Duke Command Line Interface.
     */
    public void run() {
        System.out.println(cli.getWelcomeString());

        try {
           loadSavedDate();;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        

        Scanner in = new Scanner(System.in);
        String fullCommand = "";
        Command command = new UnknownCommand();


        while ( command instanceof ByeCommandCli == false) {
            String outputString = "";
            fullCommand = in.nextLine().trim();
            try {
                command = Parser.getCommand(fullCommand, cli);
                outputString = command.execute(fullCommand, cli, tasks, taskStorage, stats, statStorage);
            } catch (IOException e) {
                outputString = e.getMessage();
            } catch (DukeException e) {
                outputString = e.getMessage();
            }
            System.out.println(outputString);
        }

    }

    /**
     * Main method to run Duke.
     *
     * @param args Main entry point.
     */
    public static void main(String args[]) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }

    public void loadSavedDate() throws IOException, DukeException {
        // Loads the data from txt file to the TaskList object, tasks.
        this.tasks = new TaskList(getTaskStorage().loadTasks());

        // Loads the stats data from txt file.
        this.stats = new Statistic(getStatStorage().loadStats());
    }

    public Storage getTaskStorage() {
        return this.taskStorage;
    }

    public Storage getStatStorage() {
        return this.statStorage;
    }

}



