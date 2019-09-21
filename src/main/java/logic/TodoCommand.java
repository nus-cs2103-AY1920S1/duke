package logic;

import model.Task;
import model.Tasklist;
import model.todo;
import storage.Storage;
import ui.UI;

public class TodoCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of TodoCommand with its arguments
     *
     * @param arguments arguments of the Command
     */
    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parses the arguments of the Command and executes it
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "";
        try{
            if (arguments == null || arguments.trim().equals("")) {
                content = "OOPS! The description of a todo cannot be empty.\n" +
                            "Usage: todo DESCRIPTION\n";
            } else {
                Task task = new todo(arguments.trim());
                tasks.add(task);

                content += "Got it. I've added this task:\n";
                content += "[" + task.getSymbol() + "][" + task.getIsDoneSymbol() + "] " + task.getDescription() + '\n';
                content += "Now you have " + tasks.size() + " tasks in this list\n";
            }
        } catch (Exception E) {
            content = "Ohno something went wrong! :(\n";
        }

        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
