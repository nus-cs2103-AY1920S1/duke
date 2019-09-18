package duke.command;

import duke.exceptions.DukeException;
import duke.formats.DateTime;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class SnoozeCommand extends Command {

    private int index;
    private String newDateTime;

    public SnoozeCommand(String[] commandArray){
        this.index = Integer.valueOf(commandArray[1]);
        StringBuilder dateTimeBuilder = new StringBuilder();
        dateTimeBuilder.append(commandArray[2]);
        dateTimeBuilder.append(" " + commandArray[3]);
        DateTime dateTime = new DateTime(dateTimeBuilder.toString());
        this.newDateTime = dateTime.toString();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException{
        if (index < 1 || tasks.getSize() < index) {
            throw new DukeException(":( OOPS!!! There is no available task in the given index.");
        }
        Task taskToChange = tasks.getTask(index - 1);
        if(taskToChange instanceof Deadline){
            Deadline deadlineToChange = (Deadline) taskToChange;
            deadlineToChange.setDeadline(newDateTime);
        } else if (taskToChange instanceof Event){
            Event eventToChange = (Event) taskToChange;
            eventToChange.setDuration(newDateTime);
        } else {
            throw new DukeException(":( OOPS!!! Operation cannot be executed for this Task type");
        }
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Nice! I've changed the timing of this task :\n"
                + "  " + taskToChange;
    }
}
