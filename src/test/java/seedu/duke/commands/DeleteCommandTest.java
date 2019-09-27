package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Todo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    TaskList tasks = new TaskList();

    DeleteCommandTest() {
        tasks.add(new Todo("Task A"));
        tasks.add(new Todo("Task B"));
        tasks.add(new Todo("Task C"));
    }

    @Test
    void deleteCommand_ShouldThrowInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> tasks.remove(5));
    }

    @Test
    void deleteCommand_ShouldDeleteTask() throws InvalidArgumentException{
        int size = tasks.size();
        tasks.remove(0);
        assertNotEquals(size, tasks.size());

    }
}
