package duke;

public class EventCommand extends Command {

    EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        int atIndex = fullCommand.indexOf(" /at ");
        if (atIndex < 0) {
            throw new DukeException("Duke.Duke.Command event requires an argument /at, followed by event date");
        }
        String eventDescription =  fullCommand.substring(6, atIndex);
        String at = fullCommand.substring(atIndex + 5);
        Event event = new Event(eventDescription, at);
        tasks.addTask(event);
        ui.showAddTask(event, tasks.numberOfTasks());
    }
}
