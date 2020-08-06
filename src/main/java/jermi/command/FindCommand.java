package jermi.command;

import static jermi.misc.Constant.COMMAND_FIND_KEYWORDS_DELIMITER;
import static jermi.misc.Constant.COMMAND_FIND_MESSAGE;
import static jermi.misc.Constant.COMMAND_TASK_FORMAT_WITH_INDEX;
import static jermi.misc.Constant.DUMMY_ARRAY_SIZE;
import static jermi.misc.Constant.INSERT_MESSAGE_INDEX;
import static jermi.misc.Constant.TASK_LIST_START_INDEX;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;

import java.util.Arrays;
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
        this.keywords = keywords.split(COMMAND_FIND_KEYWORDS_DELIMITER);
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
        List<String> formattedTasksInString = IntStream.rangeClosed(TASK_LIST_START_INDEX, tasksInString.size())
                .mapToObj(index -> String.format(COMMAND_TASK_FORMAT_WITH_INDEX,
                        index, tasksInString.get(index - TASK_LIST_START_INDEX)))
                .collect(Collectors.toList());

        formattedTasksInString.add(INSERT_MESSAGE_INDEX, COMMAND_FIND_MESSAGE);
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
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FindCommand)) {
            return false;
        } else {
            FindCommand other = (FindCommand) obj;
            return Arrays.equals(this.keywords, other.keywords);
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.keywords);
    }
}
