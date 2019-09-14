public class DoneCommand extends Command {

    public DoneCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNum = Integer.parseInt(variable);
        TaskList.listOfTasks.get(taskNum - 1).markAsDone();
        ui.printIndent();
        System.out.println("Nice! I've marked this task as done:");
        ui.printIndent();
        System.out.println(TaskList.listOfTasks.get(taskNum - 1).toString());
    }
}
