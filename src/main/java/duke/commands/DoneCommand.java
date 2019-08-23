package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.tasks.Task;

public class DoneCommand extends Command {
    private static final String DONE_TASK_STRING = "Nice! I've marked this task as done:";
    private static final String TOO_MANY_ARGUMENTS = "☹ OOPS!!! The done command needs only one argument.";
    private static final String WRONG_ARGUMENTS = "☹ OOPS!!! The done command needs an integer argument.";
    private static final String WRONG_INDEX = "☹ OOPS!!! There is no task with index: ";
    private static final String ALREADY_DONE = "☹ OOPS!!! This task is already done.";

    private int index;
    private Task task;

    public static DoneCommand create(Duke duke, String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        } else if (numArgs == 1) {
            throw new DukeException(WRONG_ARGUMENTS);
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                int numTasks = duke.getTasks().numTasks();
                if (index > numTasks || index < 1) {
                    throw new DukeException(
                            String.format("%s%d", WRONG_INDEX, index)
                    );
                }
                Task task = duke.getTasks().getTask(index);
                if (task.isDone()) {
                    throw new DukeException(ALREADY_DONE);
                }
                return new DoneCommand(duke, input, index, task);
            } catch (NumberFormatException e) {
                throw new DukeException(WRONG_ARGUMENTS);
            }

        }
    }

    private DoneCommand(Duke duke, String input, int index, Task task) {
        super(duke, input);
        this.index = index;
        this.task = task;
    }

    public void execute() {
        duke.getTasks().markDone(index);
        String message = String.format("%s\n\t%s", DONE_TASK_STRING, task);
        duke.say(message);
    }

}
