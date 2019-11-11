package duke.command;

import duke.task.Task;
import duke.TaskList;

import static duke.ui.TextUi.NEWLINE;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super.carryOn = true;
        this.newTask = newTask;
    }

    private String addDone(int size) {
        String rtn = "Got it. I've added this task: "
                + NEWLINE + this.newTask + NEWLINE
                + "Now thee have " + size + " tasks in the list.";
        return rtn;
    }

    @Override
    public String execute(TaskList taskList) {
        taskList.add(this.newTask);
        return addDone(taskList.getSize());

    }
}
