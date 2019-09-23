package seedu.duke.core;

import seedu.duke.command.ByeCommandCli;
import seedu.duke.command.Command;
import seedu.duke.command.UnknownCommand;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.GraphicalUi;
import seedu.duke.ui.CommandLineUi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

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

    // private String taskFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt";
    // private String statFilePath = "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\stats.txt";

    private String taskFilePath = "\\src\\main\\resources\\data\\tasks.txt";
    private String statFilePath = "\\src\\main\\resources\\data\\stats.txt";

    /**
     * Default constructor to support Duke GUI.
     */
    public Duke() {
        taskStorage = new Storage(taskFilePath);
        statStorage = new Storage(statFilePath);

        gui = new GraphicalUi();
        cli = new CommandLineUi();
    }

    /**
     * Constructor to support Duke CLI.
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
            loadSavedDate();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String outputString = "";
        Command command = new UnknownCommand();

        try {

            command = Parser.getCommand(input, getGui());
            outputString = command.execute(input, getGui(), getTasks(), getTaskStorage(),
                    getStats(), getStatStorage());

            saveData();

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
        String welcomeString = getCli().getWelcomeString();
        getCli().printToCommandLine(welcomeString);

        try {
            loadSavedDate();
        } catch (FileNotFoundException e) {
            getCli().printToCommandLine(e.getMessage());
        } catch (DukeException e) {
            getCli().printToCommandLine(e.getMessage());
        } catch (IOException e) {
            getCli().printToCommandLine(e.getMessage());
        }

        Scanner in = new Scanner(System.in);
        String fullCommand = "";
        Command command = new UnknownCommand();

        while (command instanceof ByeCommandCli == false) {
            String outputString = "";
            fullCommand = in.nextLine().trim();
            try {

                command = Parser.getCommand(fullCommand, getCli());
                outputString = command.execute(fullCommand, getCli(), getTasks(), getTaskStorage(),
                        getStats(), getStatStorage());

            } catch (IOException e) {
                outputString = e.getMessage();
            } catch (DukeException e) {
                outputString = e.getMessage();
            }
            getCli().printToCommandLine(outputString);
        }
    }

    /**
     * Main method to run Duke.
     *
     * @param args Main entry point.
     */
    public static void main(String[] args) {
        // new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
        new Duke("\\src\\main\\resources\\data\\tasks.txt").run();
    }

    /**
     * Load saved data of tasks and stats.
     *
     * @throws IOException Thrown due to error in reading/writing of txt file.
     * @throws DukeException Custom error thrown.
     */
    public void loadSavedDate() throws IOException, DukeException {
        ArrayList<Task> retrievedTasksData = getTaskStorage().loadTasks();
        setTasks(retrievedTasksData);

        TreeMap<String, Integer> retrievedStatsData = getStatStorage().loadStats();
        setStats(retrievedStatsData);
    }

    /**
     * Save the TaskList and Statistic objects to file.
     *
     * @throws IOException Thrown due to error in reading/writing of txt file.
     */
    public void saveData() throws IOException {
        getTaskStorage().clearTaskFileBeforeSaving();

        for (int i = 0; i < getTasks().getSize(); i++) {
            String saveString = getTasks().getTask(i).toSaveString();
            getTaskStorage().writeToTaskFile(saveString);
        }

        getStatStorage().saveStatFile(getStats());
    }

    /**
     * Setter function for TaskList attribute.
     *
     * @param taskArrayList ArrayList of Tasks.
     */
    public void setTasks(ArrayList<Task> taskArrayList) {
        this.tasks = new TaskList(taskArrayList);
    }

    /**
     * Setter function for Statistic attribute.
     *
     * @param map Tree map mapping String to Integer.
     */
    public void setStats(TreeMap<String, Integer> map) {
        this.stats = new Statistic(map);
    }

    /**
     * Getter function for task storage object.
     *
     * @return Task Storage object.
     */
    public Storage getTaskStorage() {
        return this.taskStorage;
    }

    /**
     * Getter function for stats storage object.
     *
     * @return Stat storage object.
     */
    public Storage getStatStorage() {
        return this.statStorage;
    }

    /**
     * Getter function for Statistic object.
     *
     * @return Statistic object.
     */
    public Statistic getStats() {
        return stats;
    }

    /**
     * Getter function for TaskList object.
     *
     * @return TaskList object.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Getter function for Command Line UI object.
     *
     * @return Command Line UI object.
     */
    public CommandLineUi getCli() {
        return cli;
    }

    /**
     * Getter function for Graphical UI object.
     *
     * @return Graphical UI object.
     */
    public GraphicalUi getGui() {
        return gui;
    }
}



