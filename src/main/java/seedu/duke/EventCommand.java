package seedu.duke;

public class EventCommand extends Command {
    private String command;
    public EventCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.checkErrorForEventCommand(command, tasks);
        tasks.add(Parser.createEvent(command));
        ui.printAddedTask(tasks.get(tasks.size() - 1));
        ui.printNoOfTaskInList(tasks);
        storage.appendFile(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        //for testing purposes
        return "EventCommand";
    }
}