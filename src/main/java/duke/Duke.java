package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UserInterface;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UserInterface ui;
    private static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";

    public Duke(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionMessage(e.getMessage());
            taskList = new TaskList();
            ui.exitProgram();
        }
    }

    private void run() {
        boolean isTerminated = false;
        Task task;
        ui.showWelcomeMessage();
        while (!isTerminated) {
            try {
                String inputLine = ui.readLine();
                ui.echo(inputLine);
                ui.showLine();
                Command command = Parser.parse(inputLine);
                command.execute(taskList, ui, storage);
                isTerminated = command.isTerminated();
            } catch (DukeException e) {
                ui.showExceptionMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main (String[] args){
        new Duke(DEFAULT_STORAGE_FILEPATH).run();
    }
}
