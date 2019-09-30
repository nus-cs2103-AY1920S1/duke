package duke.command;

import duke.task.TaskImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import duke.task.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();

    @Test
    void run_validIndex_success() {
        List<String> expected = Stream.concat(
                Stream.of("Here are the tasks in your list:"),
                IntStream.range(0, taskListStub.size())
                        .mapToObj(i -> i + 1 + "." + new TaskImpl("stream " + i))
        ).collect(Collectors.toList());
        List<String> actual = new ListCommand(taskListStub).run(new String[]{"list"});
        assertEquals(expected, actual);
    }
}
