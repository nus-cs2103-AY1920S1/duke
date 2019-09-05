package jermi.command;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * A representation of the command to list the tasks in the list.
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
        List<String> formattedTasksInString = IntStream.rangeClosed(1, taskList.getSize())
                .mapToObj(index -> String.format("%d.%s", index, taskList.getTask(index)))
                .collect(Collectors.toList());

        formattedTasksInString.add(0, "Here are the tasks in your list:");
        return formatter.echo(formattedTasksInString.toArray(new String[0]));
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
}
