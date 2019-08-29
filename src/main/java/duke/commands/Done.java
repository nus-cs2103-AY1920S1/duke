package duke.commands;

import duke.*;

import java.util.Map;

public class Done extends Command {
    public Done(String[] args) {
        super(args);
    }
    @Override
    public String getName() {
        return "done";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if(comArgs.length == 0) throw new DukeException("An index must be specified.");

        int oneIndex;
        try {
            oneIndex = Integer.parseInt(comArgs[0]);
        }
        catch(NumberFormatException e) {
            throw new DukeException("The index to be marked must be an integer.");
        }
        try {
            tasks.markDone(oneIndex);
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with index " + oneIndex + ".");
        }
        ui.say("Nice! I've marked this task as done:\n" + tasks.get(oneIndex));
    }
}
