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
     * This method executes the program until the user issues an exit command.
     */
    public void run() {
        ui.printGreeting();
        String command = "";
        do {
            String userInput = ui.getUserInput();
            Parser p = new Parser(userInput);
            command = p.getCommand();
            executeCommand(command, userInput, tasks, storage);
        } while (!command.equals("bye"));
    }

    /**
     * This is the method that executes the command processed by the Parser class and uses the
     * methods in the Ui class to interact with the user.
     * @param command Command derived from user input by the Parser class
     * @param input Original input by user
     * @param tasks List of tasks
     * @param storage Loads and saves tasks from the file specified in the filepath
     */
    public void executeCommand(String command, String input, TaskList tasks, Storage storage) {
        if (command.equals("bye")) {
            ui.handleBye();
        } else if (command.equals("list")) {
            ui.handleList(input, tasks);
        } else if (command.equals("done")) {
            ui.handleDone(input, tasks, storage);
        } else if (command.equals("todo")) {
            ui.handleTodo(input, tasks, storage);
        } else if (command.equals("deadline")) {
            ui.handleDeadline(input, tasks, storage);
        } else if (command.equals("event")) {
            ui.handleEvent(input, tasks, storage);
        } else if (command.equals("delete")) {
            ui.handleDelete(input, tasks, storage);
        } else {
            ui.handleBadCommand();
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
