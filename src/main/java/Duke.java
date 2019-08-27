import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import duke.ui.DukeUI;
import duke.tasklist.TaskList;
import duke.storagedata.StorageData;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.*;
/**
 * Represents a Duke App that stores a list of tasks.
 * Users can add three types of tasks: Todo, Deadline or Event.
 * Users can marked tasks as done, or delete them.
 */
public class Duke {
    private DukeUI dukeUI;
    private StorageData storage;
    private TaskList tasks;

    /**
     * Instantiates a Duke Object.
     * @param filePath This is the File that is used to load data from and save into.
     */
    public Duke(String filePath) {
        this.dukeUI = new DukeUI();
        this.storage = new StorageData(new File(filePath));
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File name has changed.");
        }
    }

    /**
     * Starts the Duke Application.
     * Prints out a welcome message and waits for user input.
     * User input is then passed through the Parser object in the Duke object, creating a Command object.
     * Command object is then executed accordingly.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        dukeUI.printWelcomeMessage();
        while(input.hasNextLine()) {
            try {
                Command c = Parser.parseCommand(input.nextLine());
                c.execute(this.tasks, this.dukeUI, this.storage);
            } catch (DukeWrongInputException e) {
                System.out.println(e.getMessage());
            } catch (DukeMissingDescriptionException ex) {
                System.out.println(ex.getMessage());
            } catch (DukeEmptyDescriptionException exx) {
                System.out.println(exx.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


