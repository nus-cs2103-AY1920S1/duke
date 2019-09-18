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

public class Duke {
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a new copy of the Duke application.
     *
     * @param filePath File path of the save file to store tasks.
     */
    Duke(String filePath) {
        ui = new Ui();
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

    /**
     * Runs this Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && ui.hasNextLine()) {
            String[] words = ui.readCommand().split(" ");
            try {
                Command command = parser.parse(words);
                assert command != null;
                ui.printMessage(command.run(words));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    String getResponse(String input) {
        try {
            String[] words = input.split(" ");
            Command command = parser.parse(words);
            assert command != null;
            return String.join("\n", command.run(words));
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    boolean isExit(String input) {
        String[] words = input.split(" ");
        try {
            return parser.parse(words).isExit();
        } catch (DukeException e) {
            return false;
        }
    }
}
