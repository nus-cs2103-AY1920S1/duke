package logic;

import model.Task;
import model.Tasklist;
import model.event;
import storage.Storage;
import ui.UI;

public class EventCommand implements Command {
    private String arguments;
    private String description;
    private String details;

    /**
     * Creates an instance of EventCommand with its arguments
     *
     * @param arguments arguments of the Command
     */
    public EventCommand(String arguments) {
        this.arguments = arguments;
        try {
            String[] sp = arguments.split(" /at ", 2);
            this.description = sp[0];
            this.details = sp[1];
        } catch (Exception E) {
            this.description = arguments;
            this.details = null;
        }
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
        if (arguments == null) {
            content = "OOPS!!! The description of a event cannot be empty.";
        } else {
            Task task = new event(description, details);
            tasks.add(task);

            content = content.concat("Got it. I've added this task:\n");
            if (details != null) {
                content = content.concat("[" + task.getSymbol() + "][" + task.getIsDoneSymbol() + "] " + task.getDescription() + " (at: " + task.getDetails() + ")\n");
            } else {
                content = content.concat("[" + task.getSymbol() + "][" + task.getIsDoneSymbol() + "] " + task.getDescription() + "\n");
            }
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
