import java.util.Arrays;

public class ToDoCommand extends AddCommand {
    private String[] commands;

    public ToDoCommand(String[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String arg = GetArgumentsUtil.concatStrings(Arrays.copyOfRange(commands, 1, commands.length));
        Task toDoTask = new Todo(arg);
        taskList.addToTaskList(toDoTask);
        ui.showMessage(Messages.ADDED_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + toDoTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
    }
}
