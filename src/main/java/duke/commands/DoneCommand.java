package duke.commands;

import java.io.IOException;
import java.util.List;


import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;

import duke.tasks.Task;


/**
 * Represents a command which contains an execute method that marks a task in the task list as done.
 * The DoneCommand object requires the task number of the task that is to be marked in the list.
 */
public class DoneCommand extends Command{

    private int index;

    /**
     * Initialises the command which contains the index of the task
     * to be marked as done
     *
     * @param index the index of the task to be deleted
     */
    private DoneCommand(int index){
        super(CommandType.COMMAND_DONE_TASK);
        this.index = index;
        assert index >= 0;
    }


    /**
     * Service for creating a done command that checks for number formatting errors
     *
     * @param tokens User input split by space, required for creating a done command
     * @throws DukeException Thrown when the parameters does not specify the index of  the task
     */
    public static DoneCommand createDoneIfValid(String [] tokens) throws DukeException {
        try {
            int index = Integer.parseInt(tokens[1])-1;
            return new DoneCommand(index);
        } catch (NumberFormatException error) {
            throw new DukeException("Must be integer", DukeExceptionType.NOT_INTEGER);
        }
    }

    /**
     * Executes by marking a particular task as done and prints to the user
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @throws IOException Thrown when the file update fails.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws IOException {
        assert ui != null;
        try {
            assert taskList != null;

            Task task = taskList.getTaskAt(index+1);
            boolean isDoneBefore = task.setDone();
            if (isDoneBefore) {
                throw new IllegalArgumentException("Task has already been done");
            }
            taskList.setDoneInList(this.index+1);

            List<String> inst = List.of("Nice! I've marked this task as done: ",
                    "  "+task.toString());
            return ui.printInput(inst);

        } catch (IndexOutOfBoundsException error3) {
            return ui.printOneLine(new DukeException("No such task", DukeExceptionType.MISSING_TASK).getMessage());
        } catch (IllegalArgumentException error2) {
            return ui.printOneLine(new DukeException(error2.getMessage(), DukeExceptionType.TASK_ALREADY_DONE).getMessage());
        }
    }

}
