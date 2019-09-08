public class DeadlineCommand extends Command {

    private String inputInstruction;

    public DeadlineCommand(String inputString) {
        inputInstruction = inputString;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.contains("/by") || inputInstruction.length() == 8
                    || inputInstruction.length() == 9) {
                throw new DukeException("deadline");
            }
            String subInput1 = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
            String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/by") + 4);
            Task newTask = new Deadline(subInput1, subInput2);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printDeadline(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
