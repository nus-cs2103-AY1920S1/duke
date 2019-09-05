import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Duke chatbot.
 */
public class Duke {

    /**
     * The Storage used for access/writing to files.
     */
    private Storage storage;
    /**
     * The filepath to read from/write to.
     */
    private String filePath;
    /**
     * The taskList to store the user's tasks.
     */
    private TaskList tasks;
    /**
     * The User interface.
     */
    private Ui ui;

    /**
     * Constructor for Duke which takes in a filepath.
     *
     * @param filepath the path of the file to read from/write to
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.filePath = filepath;
    }

    /**
     * Main command to run the logic of the Duke chatbot.
     * Prints the welcome message first, then loads any existing events from the stored file.
     * Then it begins waiting for input from the user, and will keep processing input until
     * an exit command is issued.
     * When an exit command is issued, the existing TaskList is written to the same FilePath.
     */
    public void run() {
        this.ui.printWelcomeMessage();
        try {
            this.storage = new Storage(filePath);
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui.messageUser(e.getMessage());
        } catch (FileNotFoundException e) {
            this.ui.messageUser("The file is missing :(");
        } catch (IOException e) {
            this.ui.messageUser("Trouble creating file");
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner scanner = new Scanner(System.in);
                String userMessage = this.ui.readMessage(scanner);
                Command command = Parser.parseUserMessage(userMessage);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.messageUser(e.getMessage());
            }
        }
        try {
            this.storage.save(this.tasks);
        } catch (IOException e) {
            this.ui.messageUser("ERROR WRITING TO FILE :(");
        }
    }


    /**
     * Main method for Duke.
     * Calls the run() method which contains most of the logic
     *
     * @param args any arguments that are called when java duke is run.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
