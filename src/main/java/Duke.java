public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private MainWindow window;

    /**
     * Constructs Duke object instance given the instantiated GUI MainWindow.
     *
     * @param window the instantiated GUI MainWindow.
     */
    public Duke(MainWindow window) {
        this.ui = new Ui(window);
        this.storage = new Storage("./data/duke.txt");
        this.tasks = new TaskList(this.storage.load());
        this.window = window;
    }

    /**
     * Constructs Duke object instance.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Creates instance of Duke and runs the program.
     *
     * @param args is the method argument.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the Duke to receive user input and execute command based on user input.
     */
    public void run() {
        this.ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                this.ui.printError(e);
            }
            this.ui.printLine();
        }
        this.close();
    }

    /**
     * Prints the welcome message when Duke is first initialised.
     */
    public void init() {
        this.ui.printWelcome();
    }

    /**
     * Writes data in TaskList to storage file and print goodbye.
     */
    public void close() {
        this.storage.save(this.tasks.getTaskList());
        this.ui.printGoodbye();
    }

    /**
     * Parses input given by user and executes the command based on user input.
     */
    public void handleUserInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.ui, this.storage);
            if (c.isExit()) {
                this.close();
            }

        } catch (DukeException e) {
            this.ui.printError(e);
        }
    }
}
