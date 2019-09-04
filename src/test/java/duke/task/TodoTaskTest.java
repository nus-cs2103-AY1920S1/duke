package duke.task;

import duke.command.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTaskTest {
    @Test
    void todoTaskConstructor_invalidDescriptionCallsValidate_throwsDukeInvalidArgumentException() {
        assertThrows(DukeInvalidArgumentException.class,
                () -> new TodoTask(TaskTestConstants.INVALID_DESCRIPTION_1));
    }

    @Test
    void getTaskType() {
        assertDoesNotThrow(() -> {
            TodoTask todo = new TodoTask(TaskTestConstants.VALID_DESCRIPTION_1);
            assertEquals(TaskType.TODO, todo.getTaskType());
        });
    }

    @Test
    void getTiming() {
        assertDoesNotThrow(() -> {
            TodoTask todo = new TodoTask(TaskTestConstants.VALID_DESCRIPTION_1);
            assertNull(todo.getTiming());
        });
    }

    @Test
    void getDescription() {
        assertDoesNotThrow(() -> {
            TodoTask todo = new TodoTask(TaskTestConstants.VALID_DESCRIPTION_1);
            assertEquals(TaskTestConstants.VALID_DESCRIPTION_1, todo.getDescription());
        });
    }

    @Test
    void doneStatusDependentTest() {
        //tests isDone, getStatusIcon, getStatusText, and setDone

        assertDoesNotThrow(() -> {
            TodoTask todo = new TodoTask(TaskTestConstants.VALID_DESCRIPTION_1);

            assertFalse(todo.isDone());
            assertEquals("\u2718", todo.getStatusIcon());
            assertEquals(
                    String.format("[T][\u2718] %s", TaskTestConstants.VALID_DESCRIPTION_1),
                    todo.getStatusText());

            todo.setDone(true);

            assertTrue(todo.isDone());
            assertEquals("\u2713", todo.getStatusIcon());
            assertEquals(
                    String.format("[T][\u2713] %s", TaskTestConstants.VALID_DESCRIPTION_1),
                    todo.getStatusText());
        });
    }

}
