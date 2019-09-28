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

class DeadlineTest {
    private static final TaskListStub taskListStub = new TaskListStub();
    private static final StorageStub storageStub = new StorageStub();

    @Test
    void getCommand_runWithoutParams_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"deadline"},
                "To create a deadline, enter the command \"deadline <description> /by <time>\".");
    }

    @Test
    void getCommand_runWithoutDesc_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"deadline", "/by"},
                "The description of a deadline cannot be empty.");
    }

    @Test
    void getCommand_runWithoutTime_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"deadline", "desc", "/by"},
                "The time of a deadline cannot be empty.");
    }

    void getCommandExceptionThrownHelper(String[] words, String expected) throws IOException {
        try {
            Deadline.getCommand(taskListStub, storageStub).run(words);
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeException(expected).getMessage(), e.getMessage());
            assertNull(taskListStub.lastAction);
            assertNull(storageStub.stored);
        }
    }

    @Test
    void getCommand_run_success() throws IOException, DukeException {
        String deadlineString = new Deadline("desc", "28/8/2019 1234").toString();
        List<String> lines = Deadline.getCommand(taskListStub, storageStub)
                .run(new String[]{"deadline", "desc", "/by", "28/8/2019 1234"});
        assertEquals(List.of("Got it. I've added this task:", "  " + deadlineString,
                "Now you have " + taskListStub.size() + " tasks in the list."), lines);
        assertEquals(deadlineString, taskListStub.lastAction.toString());
        assertEquals(deadlineString, storageStub.stored.get(0));
    }

    @Test
    void getSaveList_normalDeadline_success() {
        List<String> saveList = new Deadline("desc", "28/8/2019 1234").getSaveList();
        assertEquals(List.of("D", "0", "desc", "28/8/2019 1234"), saveList);
    }

    @Test
    void toString_normalDeadline_success() {
        String output = new Deadline("desc", "28/8/2019 1234").toString();
        String crossSymbol = "\u2718"; // X symbol
        assertEquals("[D][" + crossSymbol + "] desc (by: 2019 August 28 1234)", output);
    }

    @AfterEach
    void clearTask() {
        taskListStub.lastAction = null;
        storageStub.stored = null;
    }
}
