package jermi.command;

import static jermi.misc.Constant.COMMAND_LIST_MESSAGE;
import static jermi.misc.Constant.COMMAND_TASK_FORMAT_WITH_INDEX;
import static jermi.misc.Constant.DUMMY_ARRAY_SIZE;
import static jermi.misc.Constant.INSERT_MESSAGE_INDEX;
import static jermi.misc.Constant.PARSER_LIST;
import static jermi.misc.Constant.TASK_LIST_START_INDEX;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        List<String> formattedTasksInString = IntStream.rangeClosed(TASK_LIST_START_INDEX, taskList.getSize())
                .mapToObj(index -> String.format(COMMAND_TASK_FORMAT_WITH_INDEX, index, taskList.getTask(index)))
                .collect(Collectors.toList());

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
        return Objects.hashCode(PARSER_LIST);
    }
}
