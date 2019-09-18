package duke.command;

import duke.task.TaskImpl;
import duke.task.TaskListStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();

    @Test
    void run_validIndex_success() {
        List<String> expected = List.of("Here are the matching tasks in your list:",
                "1." + new TaskImpl("get 3"));
        List<String> actual = new FindCommand(taskListStub).run(new String[]{"find", "t 3"});
        assertEquals(expected, actual);
    }
}
