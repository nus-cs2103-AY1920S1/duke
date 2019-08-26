/**
 * A program that manages and keeps track of a list of tasks.
 * Features include adding and deleting tasks, as well as displaying list of current tasks.
 * Tasks can be marked as done once the user has completed it.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke Object.
     * @param filePath The filepath of the file the Duke object reads and writes its task list to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.getTasksFromFile(tasks);
        } catch (Exception e) {
            ui.showExceptionError(e);
        }
    }

    /**
     * Starts the Duke program.
     */
    public  void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showExceptionError(e);
            }
        }


    }

    /**
     * Main Method of Duke object.
     *
     * @param args argument of main method.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        (new Duke("data/tasks.txt")).run();
    }
}
