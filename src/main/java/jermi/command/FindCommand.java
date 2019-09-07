package jermi.command;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A representation of the command for finding the tasks containing the keyword.
 */
public class FindCommand extends Command {
    /** Keyword used for finding. */
    private String[] keywords;

    /**
     * Public constructor for class.
     *
     * @param keywords Keywords used for finding.
     */
    public FindCommand(String keywords) {
        super();
        this.keywords = keywords.split(" ");
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
        List<String> tasksInString = taskList.find(this.keywords);
        List<String> formattedTasksInString = IntStream.rangeClosed(1, tasksInString.size())
                .mapToObj(index -> String.format("%d.%s", index, tasksInString.get(index - 1)))
                .collect(Collectors.toList());

        formattedTasksInString.add(0, "Here are the matching tasks in your list:");
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
