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

class EventTest {
    private static final TaskListStub taskListStub = new TaskListStub();
    private static final StorageStub storageStub = new StorageStub();

    @Test
    void getCommand_runWithoutParams_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"event"},
                "To create an event, enter the command \"event <description> /at <time>\".");
    }

    @Test
    void getCommand_runWithoutDesc_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"event", "/at"},
                "The description of an event cannot be empty.");
    }

    @Test
    void getCommand_runWithoutTime_exceptionThrown() throws IOException {
        getCommandExceptionThrownHelper(new String[]{"event", "desc", "/at"},
                "The time of an event cannot be empty.");
    }

    void getCommandExceptionThrownHelper(String[] words, String expected) throws IOException {
        try {
            Event.getCommand(taskListStub, storageStub).run(words);
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeException(expected).getMessage(), e.getMessage());
            assertNull(taskListStub.lastAction);
            assertNull(storageStub.stored);
        }
    }

    @Test
    void getCommand_run_success() throws IOException, DukeException {
        String eventString = new Event("desc", "28/8/2019 1234").toString();
        List<String> lines = Event.getCommand(taskListStub, storageStub)
                .run(new String[]{"event", "desc", "/at", "28/8/2019 1234"});
        assertEquals(List.of("Got it. I've added this task:", "  " + eventString,
                "Now you have " + taskListStub.size() + " tasks in the list."), lines);
        assertEquals(eventString, taskListStub.lastAction.toString());
        assertEquals(eventString, storageStub.stored.get(0));
    }

    @Test
    void getSaveList_normalEvent_success() {
        List<String> saveList = new Event("desc", "28/8/2019 1234").getSaveList();
        assertEquals(List.of("E", "0", "desc", "28/8/2019 1234"), saveList);
    }

    @Test
    void toString_normalEvent_success() {
        String output = new Event("desc", "28/8/2019 1234").toString();
        String crossSymbol = "\u2718"; // X symbol
        assertEquals("[E][" + crossSymbol + "] desc (at: 2019 August 28 1234)", output);
    }

    @AfterEach
    void clearTask() {
        taskListStub.lastAction = null;
        storageStub.stored = null;
    }
}
