package duke;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public abstract class Duke {
    protected static final String DEFAULT_FILE_PATH = "tasks.txt";
    private Parser parser;
    protected Ui ui;

    /**
     * Constructs a new copy of the Duke application.
     *
     * @param filePath File path of the save file to store tasks.
     */
    protected Duke(String filePath, Ui ui) {
        this.ui = ui;
        ui.showWelcome();
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showError("Could not load tasks. Proceeding may overwrite old tasks.");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
        parser = new Parser();
        parser.register("todo", ToDo.getCommand(tasks, storage));
        parser.register("deadline", Deadline.getCommand(tasks, storage));
        parser.register("event", Event.getCommand(tasks, storage));
        parser.register("list", new ListCommand(tasks));
        parser.register("find", new FindCommand(tasks));
        parser.register("done", new DoneCommand(tasks, storage));
        parser.register("delete", new DeleteCommand(tasks, storage));
        parser.register("bye", new ByeCommand());
    }

    protected boolean runInput(String input) {
        String[] words = input.split(" ");
        try {
            Command command = parser.parse(words);
            assert command != null;
            ui.showMessage(command.run(words));
            return command.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
