public class FindCommand extends Command {

    private String inputInstruction;

    public FindCommand(String inputString) {
        inputInstruction = inputString;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            return ui.printFind(inputInstruction, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
