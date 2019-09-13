package jermi.command;

import static jermi.misc.Constant.COMMAND_LIST_MESSAGE;
import static jermi.misc.Constant.COMMAND_TASK_FORMAT_WITH_INDEX;
import static jermi.misc.Constant.DUMMY_ARRAY_SIZE;
import static jermi.misc.Constant.INSERT_MESSAGE_INDEX;
import static jermi.misc.Constant.LIST_COMMAND;
import static jermi.misc.Constant.TASK_LIST_START_INDEX;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A representation of the command to list all tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Public constructor for class.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param formatter Formatter.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    @Override
    public String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException {
        List<String> formattedTasksInString = new ArrayList<>();
        int bound = taskList.getSize();
        for (int index = TASK_LIST_START_INDEX; index <= bound; index++) {
            String format = String.format(COMMAND_TASK_FORMAT_WITH_INDEX, index, taskList.getTask(index));
            formattedTasksInString.add(format);
        }

        formattedTasksInString.add(INSERT_MESSAGE_INDEX, COMMAND_LIST_MESSAGE);
        return formatter.echo(formattedTasksInString.toArray(new String[DUMMY_ARRAY_SIZE]));
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(LIST_COMMAND);
    }
}
