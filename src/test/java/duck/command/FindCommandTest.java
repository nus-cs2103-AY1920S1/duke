package duck.command;

import duck.stubs.BufferStub;
import duck.stubs.StorageStub;
import duck.stubs.TaskListStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindCommandTest {
    private StorageStub storageStub;
    private BufferStub bufferStub;

    @BeforeEach
    void initTests() {
        storageStub = new StorageStub();
        bufferStub = new BufferStub();
    }

    @Test
    void execute_taskListEmpty() {
        TaskListStub taskListStub = new TaskListStub(0);
        FindCommand findCommand = new FindCommand("nullity");

        findCommand.execute(storageStub, bufferStub, taskListStub, null);
        assertEquals("There were no tasks in the list that matched your search term.#"
                + "", bufferStub.getOutputString());
    }

    @Test
    void execute_allMatch() {
        TaskListStub taskListStub = new TaskListStub(3);
        FindCommand findCommand = new FindCommand("nullity");

        findCommand.execute(storageStub, bufferStub, taskListStub, null);
        assertEquals("Here are the matching tasks in your list:#"
                + "1.X task1#2.X task2#3.X task3#", bufferStub.getOutputString());
    }

    @Test
    void execute_noneMatch() {
        TaskListStub taskListStub = new TaskListStub(3);
        taskListStub.setMatchType(1);
        FindCommand findCommand = new FindCommand("nullity");

        findCommand.execute(storageStub, bufferStub, taskListStub, null);
        assertEquals("There were no tasks in the list that matched your search term.#"
                + "", bufferStub.getOutputString());
    }

    @Test
    void testExecute_oddMatch() {
        TaskListStub taskListStub = new TaskListStub(3);
        taskListStub.setMatchType(2);
        FindCommand findCommand = new FindCommand("nullity");

        findCommand.execute(storageStub, bufferStub, taskListStub, null);
        assertEquals("Here are the matching tasks in your list:#"
                + "1.X task1#3.X task3#", bufferStub.getOutputString());
    }

    @Test
    void testExecute_lastMatch() {
        TaskListStub taskListStub = new TaskListStub(3);
        taskListStub.setMatchType(3);
        FindCommand findCommand = new FindCommand("nullity");

        findCommand.execute(storageStub, bufferStub, taskListStub, null);
        assertEquals("Here are the matching tasks in your list:#"
                + "3.X task3#", bufferStub.getOutputString());
    }
}