package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            if (description.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            Task deadlineTask = new Deadline(description, by);
            tasks.add(deadlineTask);
            response = "Got it. I've added this task:\n    " + deadlineTask + "\nNow you have " + tasks.size()
                    + " task(s) in the " + "list.";
            storage.save(tasks);
        } catch (DukeException | IOException e) {
            response = e.getMessage();
        }
    }
}
