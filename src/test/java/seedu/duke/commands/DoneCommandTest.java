package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class DoneCommandTest {

    @Test
    void markIsDone_ShouldBeDone() {
        Task task = new Todo("Something");
        task.markAsDone();
        assertTrue(task.isDone());
    }

}
