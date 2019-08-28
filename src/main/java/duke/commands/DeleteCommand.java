package duke.commands;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int entry;

    public DeleteCommand(int entry) {
        this.entry = entry;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.operateTask(tasks.getTask(entry - 1), tasks, false);
        tasks.deleteFromList(entry);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("☹ OOPS!!! I cannot read your file! :(");
        }
    }
}
