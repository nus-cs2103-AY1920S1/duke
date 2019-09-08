public class DeleteCommand extends Command {

    private String inputInstruction;

    public DeleteCommand(String inputString) {
        inputInstruction = inputString;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (inputInstruction.length() == 6 || inputInstruction.length() == 7) {
                throw new DukeException("delete");
            }
            int taskNum = Parser.getTaskNum(inputInstruction);
            if (taskNum > currentTaskList.getNoOfTask()) {
                throw new DukeException("index");
            }
            String outputString = ui.printDelete(taskNum, currentTaskList);
            storage.updateTaskToFile(currentTaskList.getEntireList());
            return outputString;
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}


