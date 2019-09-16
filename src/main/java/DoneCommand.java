public class DoneCommand extends Command {

    public DoneCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNum = Integer.parseInt(variable);
        TaskList.listOfTasks.get(taskNum - 1).markAsDone();
        ui.printIndent();
        String doneOutput = "Nice! I've marked this task as done:\n";
        ui.printIndent();
        doneOutput += TaskList.listOfTasks.get(taskNum - 1).toString();
        return doneOutput;
    }
}
