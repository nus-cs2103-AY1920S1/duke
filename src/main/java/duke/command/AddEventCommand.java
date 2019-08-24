package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class AddEventCommand extends AddCommand {
    private final String time;

    public AddEventCommand(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Event(this.description, this.time);
        executeAddTask(tasks, ui, task);
    }
}
