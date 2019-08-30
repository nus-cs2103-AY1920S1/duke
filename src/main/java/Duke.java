/**
 * CLI Chat assistant that keep tracks of tasks.
 * Will be developed incrementally over the course
 * of CS2103.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Drives the main code to start up Duke. It is the CLI entry
     * point.
     *
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        Duke duke;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        if (args.length > 1) {
            duke = new Duke(args[1]);
        } else {
            duke = new Duke();
        }
        duke.start();
    }

    /**
     * Returns a Duke object, which can be used
     * to start the chat assistant driver loop.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    /*
     Main driver function for the program -
     Greets user, then repeatedly takes in
     user input and processes them.
    */
    private void start() {
        boolean isNotShutdown = true;

        this.ui.greetHello(); // greet user on startup

        do { // main loop and exception handler
            try {
                String input = ui.getUserCommand();
                this.ui.showLine();
                Command c = Parser.parseForCommands(input); // send it off to be parsed
                c.initialize(this.storage, this.taskList, this.ui);
                c.execute();
                isNotShutdown = !c.isExit();
            } catch (DukeException e) {
                ui.displayMessage(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        } while (isNotShutdown);
        //ui.greetGoodbye(); // greet user before exiting
    }
}