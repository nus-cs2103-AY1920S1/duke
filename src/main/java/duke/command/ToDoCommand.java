package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.task.ToDo;

import duke.exception.DukeEmptyDescriptionException;

public class ToDoCommand extends Command{
    public ToDoCommand(String details) throws DukeEmptyDescriptionException{
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("todo");
        }
    }

    public void execute(TaskList tasks, DukeUi ui, StorageData storage) {
        String details = this.getDetails();
        ToDo current = new ToDo(details);
        tasks.add(current);
        storage.addTodoData(details);
        ui.printAddToDoMessage(current, tasks.size());
    }
}
