package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import duke.DukeException;
import duke.StorageStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ToDoTest {
    private static final TaskListStub taskListStub = new TaskListStub();
    private static final StorageStub storageStub = new StorageStub();

    @Test
    void getCommand_runWithoutParams_exceptionThrown() throws IOException {
        try {
            ToDo.getCommand(taskListStub, storageStub).run(new String[]{"todo"});
            fail();
        } catch (DukeException e) {
            String expected = "To create a todo, enter the command \"todo <description>\".";
            assertEquals(new DukeException(expected).getMessage(), e.getMessage());
            assertNull(taskListStub.lastAction);
            assertNull(storageStub.stored);
        }
    }

    @Test
    void getCommand_run_success() throws IOException, DukeException {
        String todoString = new ToDo("desc").toString();
        List<String> lines = ToDo.getCommand(taskListStub, storageStub)
                .run(new String[]{"todo", "desc"});
        assertEquals(List.of("Got it. I've added this task:", "  " + todoString,
                "Now you have " + taskListStub.size() + " tasks in the list."), lines);
        assertEquals(todoString, taskListStub.lastAction.toString());
        assertEquals(todoString, storageStub.stored.get(0));
    }

    @Test
    void getSaveList_normalToDo_success() {
        List<String> saveList = new ToDo("desc").getSaveList();
        assertEquals(List.of("T", "0", "desc"), saveList);
    }

    @Test
    void toString_normalToDo_success() {
        String output = new ToDo("desc").toString();
        String crossSymbol = "\u2718"; // X symbol
        assertEquals("[T][" + crossSymbol + "] desc", output);
    }

    @AfterEach
    void clearTask() {
        taskListStub.lastAction = null;
        storageStub.stored = null;
    }
}
