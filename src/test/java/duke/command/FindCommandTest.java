package duke.command;

import org.junit.jupiter.api.Test;

import java.util.List;

import duke.task.Task;
import duke.task.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();

    @Test
    void run_validIndex_success() {
        List<String> expected = List.of("Here are the matching tasks in your list:",
                "1." + new Task("get 3"));
        List<String> actual = new FindCommand(taskListStub).run(new String[]{"find", "t 3"});
        assertEquals(expected, actual);
    }
}
