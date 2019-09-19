package duke.application;

import duke.command.Ui;
import duke.command.Parser;
import duke.command.Executor;
import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/**
 * Represents an application that manages <code>ToDo</code>s, <code>duke.task.Event</code>s, and <code>Deadline</code>s.
 * A Duke object can add <code>Task</code>s, delete them, mark them as Done, and maintain a history of Tasks entered
 * during earlier execution.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a Duke object.
     * Creates a new 'data' directory and 'history.txt' file, if none exist to maintain TaskList.
     */
    public Duke() {
        ui = new Ui();
        //the following solution has been adapted from
        // https://github.com/nus-cs2103-AY1920S1/duke/pull/37/commits/060a6e36a706715f663c335ddb0b7d615fd4af81
        try {
            String rootPath = System.getProperty("user.dir");
            String dataDirectoryPath = rootPath + File.separator + "data";
            String dataFilePath = rootPath + File.separator + "data" + File.separator + "history.txt";
            File dataDirectory = new File(dataDirectoryPath);
            File dataFile = new File(dataFilePath);
            if (dataDirectory.mkdir()) {
                dataFile.createNewFile();
                System.out.println("New directory and history file created.");
            } else {
                if (dataFile.mkdir()) {
                    System.out.println("New file created.");
                }
            }
            storage = new Storage(dataFilePath);
            try {
                tasks = new TaskList(storage.load());
            } catch (IOException de) {
                ui.showLoadingError();
                tasks = new TaskList();
            } catch (DukeException de) {
                ui.showException(de);
            }
        } catch (IOException ie) {
            System.err.println(ie);
        }
    }

    /**
     * Integrates <code>Parser</code>, <code>TaskList</code>, <code>Storage</code>, and <code>Ui</code> to
     * deal with User's commands and accordingly manage database.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            try {
                Parser commandAnalyzer = new Parser(command);
                if (commandAnalyzer.getType().equals("bye")) {
                    break;
                }
                System.out.print(Executor.execute(commandAnalyzer, ui, tasks, storage));
            } catch (DukeException de) {
                System.err.println(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Parser commandAnalyzer = new Parser(input);
            return Executor.execute(commandAnalyzer, ui, tasks, storage); //returns Duke's response to command
        } catch (DukeException de) {
            return ui.showException(de);
        }
    }

    String getWelcome() {
        return ui.showWelcome();
    }
}
