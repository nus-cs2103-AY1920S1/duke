import java.io.IOException;

public class Duke {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    private Ui ui;
    private Storage fileMgr;
    private TaskList tasks;

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        
        this.fileMgr = new Storage(filePath);

        // Attempt to re-construct TaskList from data in file
        try {
            tasks = fileMgr.readTaskList();
        } catch (DukeException e) {
            this.ui.print(String.format("%s\n\nInitialised with empty TaskList", e.toString()));
            tasks = new TaskList();
        }
    }

    public void run() {
        // Show Duke's logo and welcome message
        this.ui.showWelcome();

        boolean isRunning = true;
        while (isRunning && this.ui.hasCommand()) {
            String command = this.ui.readCommand();
            // Terminate the bot if the 'bye' command is issued
            if (command.equals("bye")) {
                isRunning = false;
            } else {
                // Otherwise, parse the full command string
                this.ui.print(Parser.parse(command, tasks, fileMgr));
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            new Duke(DEFAULT_FILEPATH).run();
        } catch (IOException e) {
            System.err.println("\nERROR: Unable to resolve default file directory. Exiting...");
        }
    }
}
