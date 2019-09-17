package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;
import task.Task;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * Command to List the existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Command to List the existing tasks.
     */
    public ListCommand() {
    }

    @Override
    public String getResponse(TaskList tasklist, Ui ui,
                              Storage storage, VocabularyList vocabularyList) {
        Stream<String> taskStream = IntStream
                .range(0, tasklist.getTaskSize())
                .mapToObj(i -> {
                    Task t = tasklist.getTaskByIndex(i + 1);
                    return format("%d.%s", i + 1, t.toString());
                });
        Stream<String> combined = Stream.concat(Stream.of("Here are the tasks in your list:"), taskStream);
        String[] combinedString = combined.toArray(String[]::new);
        return ui.generateResponse(combinedString);
    }
}
