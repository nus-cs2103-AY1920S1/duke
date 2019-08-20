package duke.commands;

import duke.Command;
import duke.Duke;
import duke.DukeException;
import duke.TaskList;

import java.util.Map;

public class Done extends Command {
    public Done(Duke duke) {
        super(duke);
        name = "done";
    }

    @Override
    public void execute(String[] args) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(name);
        if(comArgs.length == 0) throw new DukeException("An index must be specified.");

        TaskList taskList = duke.getTaskList();
        int oneIndex;
        try {
            oneIndex = Integer.parseInt(comArgs[0]);
        }
        catch(NumberFormatException e) {
            throw new DukeException("The index to be marked must be an integer.");
        }
        try {
            taskList.markDone(oneIndex);
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with index " + oneIndex + ".");
        }
        duke.say("Nice! I've marked this task as done:\n" + taskList.get(oneIndex));
    }
}
