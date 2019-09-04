package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String taskName;

    /**
     * Constructor
     * @param taskName - Name of task to search for
     */
    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Execute find command on given task and save into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        List<Task> list = new ArrayList<Task>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().contains(this.taskName)) {
                list.add(taskList.get(i));
            }
        }
        if (list.size() == 0) {
            return this.getNoKeywordMessage();
        } else {
            String response = this.getFoundKeywordMessage();
            for (int i = 0; i < list.size(); i++) {
                response += (i+1) + "." + list.get(i) + "\n";
            }
            return response;
        }
    }

    /**
     * Prints out message after tasks are found matching the given keyword
     */
    private String getFoundKeywordMessage() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints out message after no tasks are found matching the given keyword
     */
    private String getNoKeywordMessage() {
        return "☹ OOPS!!! There are no matching tasks in the list with the keyword: \"" +
                this.taskName + "\".";
    }
}
