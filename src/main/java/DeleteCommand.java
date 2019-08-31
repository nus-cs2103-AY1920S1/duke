public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException {
        int value = Parser.getIndex(input, taskList.size());
        Ui.out("Noted. I've removed this task:");
        Ui.out(taskList.get(value - 1).toString());
        taskList.remove(value - 1);
        Ui.out("Now you have " + taskList.size() + " tasks in the list.");
    }
}
