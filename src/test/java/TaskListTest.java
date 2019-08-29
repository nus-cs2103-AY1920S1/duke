import exception.DukeException;
import exception.IncorrectDukeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListTest {

    @Test
    void listAllTasks_noTask_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList(null).listAllTasks());
    }

    @Test
    void delete_noTask_incorrectDukeCommandThrown() {
        assertThrows(IncorrectDukeCommand.class, () -> new TaskList(null).delete(1));
    }
}