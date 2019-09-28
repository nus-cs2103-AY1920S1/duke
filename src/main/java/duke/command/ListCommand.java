package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import duke.DukeRuntimeException;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand implements Command {
    private TaskList tasks;
    private Function<String[], String> getHeader = words -> "Here are the tasks in your list:";
    private Function<String[], Predicate<Task>> getCriteria = words -> task -> true;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    ListCommand(TaskList tasks,
                Function<String[], String> headerCreator,
                Function<String[], Predicate<Task>> criteriaCreator) {
        this(tasks);
        getHeader = headerCreator;
        getCriteria = criteriaCreator;
    }

    /**
     * Returns a message with the list of tasks.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     */
    @Override
    public List<String> run(String[] words) {
        Predicate<Task> shouldInclude;
        try {
            shouldInclude = getCriteria.apply(words);
        } catch (DukeRuntimeException e) {
            return List.of(e.getMessage());
        }
        List<String> messages = new ArrayList<>();
        messages.add(getHeader.apply(words));
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (shouldInclude.test(tasks.get(i))) {
                count++;
                messages.add(count + "." + tasks.get(i));
            }
        }
        return messages;
    }

    static String extractArgument(String[] words) {
        return String.join(" ", Arrays.copyOfRange(words, 1, words.length));
    }
}
