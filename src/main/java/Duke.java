/**
 * The main class for managing all the java files.
 */
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiate a Duke object when a directory parameter is passed
     * into it. Will also instantiate the Ui, Storage and TaskList objects.
     * @param filePath the directory for the designated path to store the tasks.
     */
    private Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }

    }

    /**
     * Run the application Duke.
     */
    private void run() {
        ui.showWelcome();
        try {
            while(true) {
                String command = ui.enterCommand();
                if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                        tasks.addTask(command);
                        ui.getAddedMessage(tasks.getTaskList());
                } else if (command.contains("delete")) {
                    String deletedTask = tasks.deleteTask(command); // retrieve the deleted task.
                    ui.getDeletedMessage(tasks.getTaskList(), deletedTask);
                } else if (command.contains("done")) {
                    String taskDoneStr = tasks.doneTask(command);  // retrieve the task that is done.
                    ui.getDoneMessage(taskDoneStr);
                } else if (command.contains("list")) {
                    ui.showList(tasks.getTaskList());
                } else if (command.contains("bye")) {
                    storage.save(tasks.getTaskList());
                    ui.getByeMessage();
                    break;
                } else if (command.contains("find")) {
                    ArrayList<Task> foundTask = tasks.findTasks(command);
                    ui.showFoundMessage(foundTask);
                } else {
                    throw new IllegalCommandException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (IllegalCommandException errorMsg) {
            ui.getIllegalCommandError(errorMsg);
        }
    }

    /**
     * Passed in the file path for the .txt file to instantiate the Duke object
     */
    public static void main(String[] args) {
        new Duke("/Users/kchensheng/Documents/NUS/Y2" +
                "/Sem1/CS2103/chen_sheng_duke/data/data.txt").run();
    }
}
