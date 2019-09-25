package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tl, Storage st) {
        UI.findStart();
        int counter = 1;

        for (Task task : tl.getTaskList()) {
            String[] taskNameComponents = task.getName().split(" ");

            for (String nameComponent : taskNameComponents) {
                if (nameComponent.equalsIgnoreCase(searchTerm)) {
                    System.out.println("     " + counter + "." + task);
                    counter++;
                }
            }
        }

        UI.endLine();
    }
}
