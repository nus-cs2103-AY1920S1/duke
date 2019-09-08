public class TodoCommand extends Command {

    private String inputInstruction;

    public TodoCommand(String inputString) {
        inputInstruction = inputString;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                throw new DukeException("todo");
            }
            String subInput = inputInstruction.substring(5);
            Task newTask = new Todo(subInput);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printTodo(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
