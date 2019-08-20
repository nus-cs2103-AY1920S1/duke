package duke.commands;

import duke.*;

import java.util.Map;

public class Delete extends Command {
    public Delete(Duke duke) {
        super(duke);
        name = "delete";
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
            throw new DukeException("The index to be deleted must be an integer.");
        }
        Task task;
        try {
            task = taskList.delete(oneIndex);
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with index " + oneIndex + ".");
        }
        duke.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                task.toString(), taskList.size(), taskList.size() == 1 ? "" : "s"));
    }
}
