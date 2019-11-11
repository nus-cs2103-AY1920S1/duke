package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {
    public String findKeyword;
    protected TaskList taskList;

    public FindCommand(String findKeyword) {
        super.carryOn = true;
        this.findKeyword = findKeyword;
    }

    /**
     * Executes the find command and returns all tasks with the given keyword.
     *
     * @param taskList current taskList
     * @return all tasks containing the keyword
     */
    public String execute(TaskList taskList) {
        assert !this.findKeyword.isEmpty() : "The search keyword cannot be empty";
        this.taskList = taskList;
        ArrayList<Task> tasks = this.taskList.getArrayList();
        ArrayList<Task> rtnTaskArrayList = new ArrayList<>();
        for (Task task : tasks) {
            String fullTask = task.getTask();
            if (fullTask.contains(this.findKeyword)) {
                rtnTaskArrayList.add(task);
            }
        }
        return TextUi.findCommandPrint(rtnTaskArrayList);
    }

}
