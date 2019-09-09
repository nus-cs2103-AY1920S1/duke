import java.io.IOException;

/**
 * A task list that supports several basic features:
 * 1) Addition and deletion of three types of task.
 * 2) Ability to mark tasks as done.
 * 3) Ability to search for expressions in given tasks.
 * 4) Ability to print current list of tasks.
 */
public class Duke {
    /**
     * The TaskList object which abstracts out a list of tasks.
     */
    private TaskList taskList;
    
    /**
     * The Storage object which loads and writes data to the hard drive.
     */
    private Storage storage;
    
    /**
     * The Ui object which scans input and prints feedback to the user.
     */
    private Ui ui;
    
    static boolean isExitRunLoop;
    
    /**
     * Initializes the Ui, Storage, and TaskList objects.
     *
     * @param filePath The file path to write data to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadSavedList());
        } catch (IOException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }
    
    /**
     * Scans and parses commands given by the user.
     * Modifies the Tasks in the TaskList object based on the commands received by the user.
     */
    public void run() {
        ui.print(ui.showHello());
        isExitRunLoop = false;
        while (!isExitRunLoop) {
            try {
                String input = ui.getNextLine();
                Command command = Parser.parse(input);
                ui.print(command.execute(taskList, ui, storage));
            } catch (DukeException | IOException e) {
                ui.print(ui.showError(e));
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke("CurrentTaskList.txt").run();
    }
}
