package duke;

import java.io.IOException;

import duke.command.Command;
import duke.component.Parser;
import duke.component.TaskList;
import duke.component.Storage;
import duke.component.Window;
import duke.exception.DukeException;

public class Duke {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    private Storage fileMgr;
    private TaskList tasks;
    private Window window;

    /**
     *  Constructs a <code>Duke</code> application instance, an interactive task manager.
     *  @param filePath <code>String</code> containing the relative file path of the file to persist application
     *                  data to.
     *  @throws IOException if an error occured during a file I/O operation.
     */
    public Duke(String filePath) throws IOException {
        this.window = new Window();
        
        this.fileMgr = new Storage(filePath);

        // Attempt to re-construct TaskList from data in file
        try {
            tasks = fileMgr.readTaskList();
        } catch (DukeException e) {
            this.window.print(String.format("%s\n\nInitialised with empty TaskList", e.toString()));
            tasks = new TaskList();
        }
    }

    /** 
     *  Initiates the Duke application instance, allowing users to input commands to interact with the task maanger.
     */
    public void run() {
        // Show Duke's logo and welcome message
        this.window.showWelcome();

        boolean isRunning = true;
        while (isRunning && this.window.hasCommand()) {
            String command = this.window.readCommand();
            
            // Parse the command to return a Command object
            try {
                Command c = Parser.parse(command);
                this.window.print(c.execute(tasks, fileMgr));
                isRunning = !c.willTerminate();
            } catch (DukeException e) {
                this.window.print(e.toString());
            }
        }
    }
    
    /**
     *  Driver main method.
     *  @param args an array of <code>String</code> arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke(DEFAULT_FILEPATH).run();
        } catch (IOException e) {
            System.err.println("\nERROR: Unable to resolve default file directory. Exiting...");
        }
    }
}
