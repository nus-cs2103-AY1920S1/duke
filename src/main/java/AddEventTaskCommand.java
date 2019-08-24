public class AddEventTaskCommand extends Command {
    public AddEventTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        if (TaskList.getNumberOfTasks() >= 100) {
            throw new TooManyTasksException();
        }
        String[] eventData = dataParser.parseEventDate();
        dateParser.readInput(eventData[1]);
        String dateOutput = dateParser.convertDateToString();
        String nameOutput = eventData[0];
        int taskIndex = taskList.addEventTask(nameOutput, dateOutput);
        ui.showAddedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

}
