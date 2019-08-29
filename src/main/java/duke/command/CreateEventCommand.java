package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Event;

import java.text.ParseException;

public class CreateEventCommand extends Command {
    public CreateEventCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String event = this.commandInformation;
        String[] eventParts = event.split(" /at ");
        String eventText = eventParts[0];
        String at = eventParts[1];

        try {
            tasks.addTask(new Event(eventText, at),true);
        } catch (ParseException error) {
            System.out.println("\t " + error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM - DD/MM/YYYY HHMM");
        }
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
