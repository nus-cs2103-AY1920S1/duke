import java.util.InputMismatchException;

public class AddCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");
    String deadline = "deadline";
    String event = "event";
    String todo = "todo";

    /**
     * Constructor for AddCommand
     * @param stringCommand
     */
    public AddCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task addTask;
        if (commandSplit[0].equalsIgnoreCase(deadline)) {
            String details = super.stringCommand.substring(deadline.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a deadline cannot be empty.");
            }
            String[] detail = details.split(" /by ");
            addTask = new Deadline(detail[0], detail[1]);
            ui.printAddedMessage();
            ui.printTask(addTask);
            taskList.add(addTask);
        } else if (commandSplit[0].equalsIgnoreCase(event)) {
            String details = super.stringCommand.substring(event.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a event cannot be empty.");
            }
            String[] detail = details.split(" /at ");
            addTask = new Event(detail[0], detail[1]);
            ui.printAddedMessage();
            ui.printTask(addTask);
            taskList.add(addTask);
        } else if (commandSplit[0].equalsIgnoreCase(todo)) {
            String details = super.stringCommand.substring(todo.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a todo cannot be empty.");
            }
            addTask = new Todo(details);
            ui.printAddedMessage();
            ui.printTask(addTask);
            taskList.add(addTask);
        }
        ui.printNumberOfTasks(taskList);
    }

    /**
     * Checks if Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
