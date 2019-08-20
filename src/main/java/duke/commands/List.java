package duke.commands;

import duke.Command;
import duke.Duke;
import duke.TaskList;

public class List extends Command {
    public List(Duke duke) {
        super(duke);
        name = "list";
    }

    @Override
    public void execute(String[] args) {
        TaskList taskList = duke.getTaskList();
        if(taskList.size() > 0) {
            duke.say(duke.getTaskList().toString());
        }
        else {
            duke.say("No tasks yet.");
        }
    }
}
