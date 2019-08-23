package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private static final String DELETE_TASK_STRING = "Noted. I've removed this task:";
    private static final String TOO_MANY_ARGUMENTS = "☹ OOPS!!! The delete command needs only one argument.";
    private static final String WRONG_ARGUMENTS = "☹ OOPS!!! The delete command needs an integer argument.";
    private static final String WRONG_INDEX = "☹ OOPS!!! There is no task with index: ";

    private int index;

    public static DeleteCommand create(Duke duke, String input, String[] args) throws DukeException {
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
                return new DeleteCommand(duke, input, index);
            } catch (NumberFormatException e) {
                throw new DukeException(WRONG_ARGUMENTS);
            }

        }
    }

    private DeleteCommand(Duke duke, String input, int index) {
        super(duke, input);
        this.index = index;
    }

    public void execute() {
        Task deletedTask = duke.getTasks().getTask(index);
        duke.getTasks().deleteTask(index);
        duke.say(
                String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                        DELETE_TASK_STRING, deletedTask, duke.getTasks().numTasks())
        );
    }
}
