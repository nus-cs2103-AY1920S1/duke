package duke;

import duke.todo.TaskList;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.command.Command;
import duke.todo.Task;

/**
 * Duke class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke object with filePath input from which
     * the data of the existing is stored.
     *
     * @param filePath File path for input.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.greet();

        boolean isRunning = true;
        Parser parser = new Parser();
        Command command;

        while (isRunning) {
            try {
                String input = ui.takeCommand();
                command = parser.parse(input);

                switch (command.getTaskType()) {
                case "bye":
                    isRunning = false;
                    break;
                case "done":
                    int index = command.getIndex() - 1;
                    tasks.markTaskDone(index);
                    ui.reportDone(tasks.getTask(index));
                    break;
                case "list":
                    ui.printFormattedText(tasks.generateList());
                    break;
                case "delete":
                    Task removedTask = tasks.removeTask(command.getIndex());
                    ui.reportRemove(removedTask, tasks.getNumOfTasks());
                    break;
                case "todo":
                    Task addedTodo = tasks.addTask(command.getTask());
                    ui.reportAdd(addedTodo, tasks.getNumOfTasks());
                    break;
                case "deadline":
                    Task addedDeadline = tasks.addTask("D", command.getTask(), command.getDate());
                    ui.reportAdd(addedDeadline, tasks.getNumOfTasks());
                    break;
                case "event":
                    Task addedEvent = tasks.addTask("E", command.getTask(), command.getDate());
                    ui.reportAdd(addedEvent, tasks.getNumOfTasks());
                    break;
                default:
                    throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        ui.bye();

        // Write the latest To Do List into the file
        storage.writeBackToFile(tasks.outputTasks());
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/tasks.txt").run();
    }
}
