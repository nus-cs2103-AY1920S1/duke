package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public class DeleteCommand implements Command {

    private String arguments;

    /**
     * Creates an instance of DeleteCommand with its arguments
     *
     * @param arguments arguments of the Command
     */
    public DeleteCommand(String arguments) {
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
            int index = Integer.parseInt(arguments.trim());
            if (tasks.size() >= index) {
                content = content.concat("Noted. I've removed this task:\n");
                content = content.concat("[" + tasks.get(index - 1).getSymbol() + "][" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription());
                if (tasks.get(index - 1).getSymbol() == 'D') {
                    if(tasks.get(index - 1).getDetails() != null){
                        content = content.concat(" (by: " + tasks.get(index - 1).getTime() + ")");
                    }
                } else if (tasks.get(index - 1).getSymbol() == 'E') {
                    if(tasks.get(index - 1).getDetails() != null) {
                        content = content.concat(" (at: " + tasks.get(index - 1).getDetails() + ")");
                    }
                }
                content = content.concat("\n");
                tasks.remove(index - 1);
                content = content.concat("You now have " + tasks.size() + " tasks in this list\n");
            } else {
                content = content.concat("Failed to delete Task!\n" +
                        "No such Task found!\n");
            }

        } catch (Exception E){
            content = "Ohno! You have entered an invalid argument!\n" +
                    "Usage: delete <id>\n";
        }

        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
