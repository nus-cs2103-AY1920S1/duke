public class DeleteCommand extends Command {
    public DeleteCommand(String commandText) {
        super();
        desc = commandText;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws  DukeException{
            int sizeOfList = task.getNumOfTasks();
            if (desc.matches("^\\d+")) {
                int taskNum = Integer.parseInt(desc);
                if (taskNum > sizeOfList || taskNum < 1) {
                    throw new InvalidDescriptionException("Wrong description");
                } else {
                    ui.showText(task.removeTask(taskNum));
                }
            } else {
                throw new InvalidDescriptionException("Wrong description");
            }

    }
}
