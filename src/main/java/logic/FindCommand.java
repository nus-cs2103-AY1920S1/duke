package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

import java.util.ArrayList;

public class FindCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of FindCommand with its arguments
     *
     * @param arguments arguments of the Command
     */
    public FindCommand(String arguments) {
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
        if (this.arguments == null || this.arguments.trim().equals("")) {
            content = "OOPS!!! The description of a find cannot be empty.";
        } else {
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            int i;
            for (i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(this.arguments)) {
                    indexes.add(i);
                }
            }

            if(indexes.size() == 0){
                content += "There are no Tasks that fits your description\n";
            }

            for (i = 0; i < indexes.size(); i++) {
                content += (indexes.get(i) + 1) + ". ";
                content += "[" + tasks.get(indexes.get(i)).getSymbol() + "]";
                content += "[" + tasks.get(indexes.get(i)).getIsDoneSymbol() + "]";
                content += " " + tasks.get(indexes.get(i)).getDescription();
                if (tasks.get(indexes.get(i)).getSymbol() == 'D') {
                    if (tasks.get(indexes.get(i)).getDetails() != null) {
                        content += " (by: " + tasks.get(indexes.get(i)).getTime() + ")";
                    }
                } else if (tasks.get(indexes.get(i)).getSymbol() == 'E') {
                    if (tasks.get(indexes.get(i)).getDetails() != null) {
                        content += " (at: " + tasks.get(indexes.get(i)).getDetails() + ")";
                    }
                }
                content += "\n";
            }
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
