import java.io.FileNotFoundException;

/**
 * This is the main class of the Duke application. The Duke application is an interface of a
 * to-do list that allows users to add tasks to do, deadlines and events to attend.
 * @author Shawn Lee
 * @version 1.0
 * @since 2019-08-20
 */
public class Duke {

    /**
     * Deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * Contains the task list.
     */
    private TaskList tasks;

    /**
     * Deals with interactions with the user.
     */
    private Ui ui;

    /**
     * This is the class constructor assigning a filepath to the Duke object.
     * @param filePath Path of .txt file where tasks are stored persistently
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * This method contains the main logic of the program. Program is executed until exit command is issued
     * by the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    /**
     * This is the main method that runs the program using a specified file path where the tasks
     * are stored in a .txt file.
     * @param args Unused
     */
    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}
