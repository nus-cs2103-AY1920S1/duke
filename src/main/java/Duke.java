import java.util.Date;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filepath refers to where items should be kept in memory.
     */
    Duke(String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList(storage.load());
        ui = new Ui();
    }


    /**
     * Adds a task to the array and saves it to memory.
     */
    private void addTask(Task task) {
        // 1. First add the task to the task list.
        taskList.addTask(task);
        // 2. Next, use Storage to save the task list.
        storage.save(taskList.getArr());
    }


    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage, taskList);
            isExit = c.isExit();
        }
    }

    /**
     * Starts a Duke instance.
     *
     * @param args takes in arguments.
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}


