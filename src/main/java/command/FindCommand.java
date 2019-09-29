package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

public class FindCommand implements Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute(TaskList tl, Storage st) {
        StringBuilder toReturn = new StringBuilder(UI.findStart());
        int counter = 1;

        for (Task task : tl.getTaskList()) {
            String[] taskNameComponents = task.getName().split(" ");

            for (String nameComponent : taskNameComponents) {
                if (nameComponent.equalsIgnoreCase(searchTerm)) {
                    toReturn.append("     " );
                    toReturn.append(counter);
                    toReturn.append(".");
                    toReturn.append(task);
                    toReturn.append("\n");
                    counter++;
                    break;
                }
            }
        }
        toReturn.append(UI.endLine());

        return toReturn.toString();
    }
}
