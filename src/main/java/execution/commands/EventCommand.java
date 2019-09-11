package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Event;
import models.Task;

public class EventCommand extends Command {
    public EventCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);

        String wholeTask = this.descriptionOfTask.trim();
        int index = wholeTask.indexOf('/');
        //what the task is
        String description = wholeTask.substring(0, index).trim();
        //when it is due by
        String date = wholeTask.substring(index + 4).trim();
        Task newEvent = new Event(description, date);
        taskList.addTask(newEvent);

        ui.displayAddingOfTask(newEvent, taskList.getSize());
        storage.saveToDataFile(taskList);
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!this.descriptionOfTask.contains("/at")) {
            throw new DukeException(" ☹ OOPS!!! Event input should include '/at'.");
        }
    }
}
