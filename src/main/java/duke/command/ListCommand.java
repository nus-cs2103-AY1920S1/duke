package duke.command;

import duke.DukeRuntimeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        List<Task> includedTasks = tasks.stream()
                .filter(shouldInclude)
                .collect(Collectors.toList());
        return Stream.concat(
                Stream.of(getHeader.apply(words)),
                IntStream.range(0, includedTasks.size())
                        .mapToObj(i -> i + 1 + "." + includedTasks.get(i))
        ).collect(Collectors.toList());
    }

    static String extractArgument(String[] words) {
        return String.join(" ", Arrays.copyOfRange(words, 1, words.length));
    }
}
