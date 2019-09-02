public class DeleteCommand extends Command {
    public DeleteCommand(String commandText) {
        super();
        description = commandText;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws  DukeException{
            int sizeOfList = task.getNumOfTasks();
            if (description.matches("^\\d+")) {
                int taskNum = Integer.parseInt(description);
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
