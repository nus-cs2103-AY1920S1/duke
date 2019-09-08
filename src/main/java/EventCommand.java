public class EventCommand extends Command {

    private String inputInstruction;

    public EventCommand(String inputString) {
        inputInstruction = inputString;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.contains("/at") || inputInstruction.length() == 5
                    || inputInstruction.length() == 6) {
                throw new DukeException("event");
            }
            String subInput1 = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
            String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/at") + 4);
            Task newTask = new Event(subInput1, subInput2);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printEvent(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
