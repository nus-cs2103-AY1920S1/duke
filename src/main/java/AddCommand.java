import java.util.InputMismatchException;

public class AddCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");
    String deadline = "deadline";
    String event = "event";
    String todo = "todo";

    public AddCommand(String stringCommand) {
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        if (commandSplit[0].equalsIgnoreCase(deadline)) {
            String details = super.stringCommand.substring(deadline.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a deadline cannot be empty.");
            }
            String[] detail = details.split(" /by ");
            Task addTask = new Deadline(detail[0], detail[1]);
            String outputString = "Got it. I've added this task: \n" + addTask.toString();
            ui.printMessage(outputString);
            taskList.add(addTask);
        } else if (commandSplit[0].equalsIgnoreCase(event)) {
            String details = super.stringCommand.substring(event.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a event cannot be empty.");
            }
            String[] detail = details.split(" /at ");
            Task addTask = new Event(detail[0], detail[1]);
            String outputString = "Got it. I've added this task: \n" + addTask.toString();
            ui.printMessage(outputString);
            taskList.add(addTask);
        } else if (commandSplit[0].equalsIgnoreCase(todo)) {
            String details = super.stringCommand.substring(todo.length()).trim();
            if (details.length() == 0) {
                throw new InputMismatchException("The description of a todo cannot be empty.");
            }
            Task addTask = new Todo(details);
            String outputString = "Got it. I've added this task: \n" + addTask.toString();
            ui.printMessage(outputString);
            taskList.add(addTask);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
