package duke.command;

import java.io.IOException;

import duke.TaskList;
import duke.error.DukeException;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.Ui;

public class ToDoCommand implements Command {
    private String name;

    /**
     * Constructor.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(new ToDo(this.name));
            ui.sendAddTaskAck(tasks.get(tasks.getSize()), tasks.getSize());
            storage.save(tasks);
        } catch (IOException e) {
            ui.printWriteError();
        }
           
    }
}
