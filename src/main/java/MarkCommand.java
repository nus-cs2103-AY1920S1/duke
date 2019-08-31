public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException {
        ui.showLine();
        int value = Parser.getIndex(input, taskList.size());
        taskList.get(value - 1).setDone();
        ui.out("Nice! I've marked this task as done:");
        ui.out(taskList.get(value - 1).toString());
        ui.showLine();
    }
}
