public class DoneCommand extends Command {
    public DoneCommand(String commandText) {
        super();
        desc = commandText;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
            int sizeOfList = task.getNumOfTasks();
            String number = desc.substring(4).trim();
            if (number.matches("^\\d+")) {
                int taskNum = Integer.parseInt(number);
                if (taskNum > sizeOfList || taskNum < 1) {
                    throw new InvalidDescriptionException("Wrong description");
                } else {
                    ui.showText(task.tickTask(taskNum));
                }
            } else {
                throw new InvalidDescriptionException("Wrong description");
            }
    }
}
