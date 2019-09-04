package duke.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import duke.task.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();

    @Test
    void run_validIndex_success() {
        List<String> expected = new ArrayList<>();
        expected.add("Here are the tasks in your list:");
        for (int i = 0; i < taskListStub.size(); i++) {
            expected.add(i + 1 + "." + taskListStub.get(i));
        }
        List<String> actual = new ListCommand(taskListStub).run(new String[]{"list"});
        assertEquals(expected, actual);
    }
}
