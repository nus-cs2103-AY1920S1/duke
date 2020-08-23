package duke.command;

import java.io.IOException;

import duke.error.DukeException;
import duke.task.ToDo;
import duke.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ToDoCommand implements Command {
    private String name;

    /**
     * Constructor.
     *
     * @param name String
     */
    public ToDoCommand(String name) {
        this.name = name;
    }

    /**
     * Check if exit.
     */
    public boolean isExit() {
        return false; 
    }

    /**
     * Execute.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(new ToDo(this.name));
            ui.sendAddTaskAck(tasks.get(tasks.getSize()), tasks.getSize());
            storage.save(tasks);
            return ui.genAddTaskAck(tasks.get(tasks.getSize()), tasks.getSize());
        } catch (IOException e) {
            ui.printWriteError();
            return ui.stringifyError(e);
        }
           
    }
}
