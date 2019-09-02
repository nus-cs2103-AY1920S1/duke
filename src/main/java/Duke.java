/**
 * Duke is the main class of the app, where the execution command exists.
 */
public class Duke {
    /**
     * A Storage attribute to deal with reading data from files or writing data
     to files.
     */
    private Storage storage;

    /**
     * A TaskList attribute to keep a list of all the tasks the user inputs.
     */
    private TaskList tasks;

    /**
     * A Ui attribute to deal with all display and print showed to the user.
     */
    private Ui ui;

    /**
     * Constructs and initializes the attributes of a new Duke object.
     * @param filePath File path to the text file to read and write data.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
    * A method to begin the application.
    */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * main method for the program to execute.
     * @param args Takes in an array of Strings as arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data\\duke.txt");
        duke.run();
    }
}
