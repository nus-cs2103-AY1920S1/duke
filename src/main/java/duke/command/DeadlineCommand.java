
package duke.command;

import java.io.IOException;
import java.util.Date;

import duke.TaskList;
import duke.task.Deadline;
import duke.error.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class DeadlineCommand implements Command {
    private String name;
    private Date time;
    
    /**
     * Constructor.
     */
    public DeadlineCommand(String name, Date time) {
        this.name = name; 
        this.time = time;
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
            tasks.add(new Deadline(this.name, this.time));
            ui.sendAddTaskAck(tasks.get(tasks.getSize()), tasks.getSize());
            storage.save(tasks);
        } catch (IOException e) {
            ui.printWriteError();
        }
    }
}
