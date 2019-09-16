import tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String toFind = getKeyWord(desc);
        ArrayList<Task> tasks = taskList.getList();
        ArrayList<Task> taskFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.hasKeyword(toFind)) {
                taskFound.add(t);
            }
        }
        return taskList.showTaskFound(taskFound);
    }

    /**
     * Get the keyword to be searched.
     * @param line The command line.
     * @return The keyword.
     */
    public String getKeyWord(String line) {
        return line.substring(5);
    }
}
