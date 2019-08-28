package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.helper.DateTimeHelper;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.time.LocalDateTime;

public class AddCommand extends Command {

    public AddCommand(String inputCommand){
        super(inputCommand);
    }

    //note private can't be accessed by child
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ", 2);
        String typeTask = inputsplit[0];
        if(inputsplit.length <= 1 ){
            throw new DukeException("OOPS!!! The description of a " + typeTask + " cannot be empty.");
        }
        Task taskToAdd;
        if (typeTask.equalsIgnoreCase("todo")) {
            taskToAdd = new ToDo(inputsplit[1]);
        } else if (typeTask.equalsIgnoreCase("deadline")) {
            String[] descripSplit = inputsplit[1].split(" /by ", 2);
            LocalDateTime ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Deadline(descripSplit[0],ldt);
        } else {
            String[] descripSplit = inputsplit[1].split(" /at ", 2);
            LocalDateTime ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Event(descripSplit[0], ldt);
        }
        tasks.addToRecord(taskToAdd);
        ui.printAdd(taskToAdd, tasks.getSize());
        storage.writeToFile(tasks.getTaskList());
    }



}
