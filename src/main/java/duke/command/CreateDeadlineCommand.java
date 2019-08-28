package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.text.ParseException;

public class CreateDeadlineCommand extends Command {
    public CreateDeadlineCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String deadline =  this.commandInformation;
        String[] deadlineParts = deadline.split(" /by ");
        String deadLineText = deadlineParts[0];
        String by = deadlineParts[1];

        try {
            tasks.addTask(new Deadline(deadLineText, by), true);
        } catch (ParseException e) {
            System.out.println("\t " + e.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM");
        }

        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
