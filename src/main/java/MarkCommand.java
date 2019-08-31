public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException {
        int value = Parser.getIndex(input, taskList.size());
        taskList.get(value - 1).setDone();
        Ui.out("Nice! I've marked this task as done:");
        Ui.out(taskList.get(value - 1).toString());
    }
}
