package duke.commands;

import duke.Command;
import duke.Duke;
import duke.TaskList;

public class Done extends Command {
    public Done(Duke duke) {
        super(duke);
        name = "done";
    }

    @Override
    public void execute(String[] args) {
        TaskList taskList = duke.getTaskList();
        int oneIndex = Integer.parseInt(args[1]);
        taskList.markDone(oneIndex);
        duke.say("Nice! I've marked this task as done:\n" + taskList.get(oneIndex));
    }
}
