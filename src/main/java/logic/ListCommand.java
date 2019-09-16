package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public class ListCommand implements Command {

    /**
     * Creates an instance of ListCommand
     */
    public ListCommand() {

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
        int i;
        String content = "";
        if(tasks.size() == 0){
            content += "There are currently no tasks!";
        }

        for (i = 0; i < tasks.size(); i++) {
            content += (i + 1) + ". ";
            content += "[" + tasks.get(i).getSymbol() + "]";
            content += "[" + tasks.get(i).getIsDoneSymbol() + "]";
            content += " " + tasks.get(i).getDescription();
            if (tasks.get(i).getSymbol() == 'D') {
                if (tasks.get(i).getDetails() != null) {
                    content += " (by: " + tasks.get(i).getTime() + ")";
                }

            } else if (tasks.get(i).getSymbol() == 'E') {
                if (tasks.get(i).getDetails() != null) {
                    content += " (at: " + tasks.get(i).getDetails() + ")";
                }
            }
            content += "\n";
        }
        storage.save(tasks);

        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
