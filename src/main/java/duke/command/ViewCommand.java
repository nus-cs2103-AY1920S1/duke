package duke.command;

import duke.TaskList;

public class ViewCommand extends Command {
    protected TaskList taskList;
    private int searchTaskIdx;

    public ViewCommand(int searchTaskIdx) {
        super.carryOn = true;
        this.searchTaskIdx = searchTaskIdx;
    }

    @Override
    public String execute(TaskList taskList) {
        this.taskList = taskList;
        return searchTaskIdx + ". " + taskList.get(searchTaskIdx - 1).toString();
    }
}