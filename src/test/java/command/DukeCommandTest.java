package command;

import duke.command.DukeCommandAdd;
import duke.command.DukeCommandUpdate;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeCommandTest {

    private static final String DUKE_TASK_FILE_PATH = ".\\data\\duke.txt";
    private static DukeTaskList tasks;
    private static DukeUi ui;
    private static DukeStorage storage;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    /**
     * Initializes the required {@link DukeTaskList}, {@link DukeUi} and {@link DukeStorage} instances for the tests to
     * be ran. Assumes that {@link #DUKE_TASK_FILE_PATH} is a proper, readable and writable text file location.
     */
    @BeforeAll
    public static void beforeAll() {
        tasks = new DukeTaskList();
        ui = new DukeUi();
        storage = new DukeStorage(DUKE_TASK_FILE_PATH);
    }

    @Test
    public void testAddTodo() {
        //Test adding valid DukeTaskTodo
        String[] validTodoTestTokens = {"todo", "Test", "adding", "todo", "task."};
        assertDoesNotThrow(() -> new DukeCommandAdd(validTodoTestTokens).execute(tasks, ui, storage));

        //Test adding invalid DukeTask with missing arguments.
        systemOutRule.clearLog();
        String[] invalidTodoTestTokens = {"todo"};
        new DukeCommandAdd(invalidTodoTestTokens).execute(tasks, ui, storage);
        String output = systemOutRule.getLogWithNormalizedLineSeparator();

        systemOutRule.clearLog();
        ui.displayEmptyDescriptionError();
        String errorMessage = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);
    }

    @Test
    public void testAddDeadline() {
        //Test adding valid DukeTaskDeadline.
        String[] validDeadlineTestTokens = {"deadline", "Test", "adding", "deadline", "/by", "18/10/2019", "1930"};
        assertDoesNotThrow(() -> new DukeCommandAdd(validDeadlineTestTokens).execute(tasks, ui, storage));

        //Test adding invalid DukeTaskDeadline with missing parameter /by.
        systemOutRule.clearLog();
        String[] invalidDeadlineTestTokensMissingParam1 = {"deadline", "Test", "adding", "deadline"};
        new DukeCommandAdd(invalidDeadlineTestTokensMissingParam1).execute(tasks, ui, storage);
        String output = systemOutRule.getLogWithNormalizedLineSeparator();

        systemOutRule.clearLog();
        ui.displayMissingDeadlineParam();
        String errorMessage = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);

        //Test adding invalid DukeTaskDeadline with /by but without parameter after
        systemOutRule.clearLog();
        String[] invalidDeadlineTestTokensMissingParam2 = {"deadline", "Test", "adding", "deadline", "/by"};
        new DukeCommandAdd(invalidDeadlineTestTokensMissingParam2).execute(tasks, ui, storage);
        output = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);

        //Test adding invalid DukeTaskDeadline with invalid dateformat.
        String[] invalidDeadlineTestTokensInvalidDateFormat = {"deadline", "Test", "adding", "deadline", "/by", "abc"};
        assertDoesNotThrow(() ->
                new DukeCommandAdd(invalidDeadlineTestTokensInvalidDateFormat).execute(tasks, ui, storage));
    }

    @Test
    public void testAddEvent() {
        //Test adding valid DukeTaskEvent
        String[] validEventTestTokens = {"event", "Test", "adding", "event", "/at", "i3", "Auditorium"};
        assertDoesNotThrow(() -> new DukeCommandAdd(validEventTestTokens).execute(tasks, ui, storage));

        //Test adding invalid DukeTaskEvent with missing parameter /at
        systemOutRule.clearLog();
        String[] invalidEventTestTokensMissingParam1 = {"event", "Test", "adding", "event"};
        new DukeCommandAdd(invalidEventTestTokensMissingParam1).execute(tasks, ui, storage);
        String output = systemOutRule.getLogWithNormalizedLineSeparator();

        systemOutRule.clearLog();
        ui.displayMissingEventParam();
        String errorMessage = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);

        //Test adding invalid DukeTasktEvent with /at but missing parameter after
        systemOutRule.clearLog();
        String[] invalidEventTestTokensMissingParam2 = {"event", "Test", "adding", "event", "/at"};
        new DukeCommandAdd(invalidEventTestTokensMissingParam2).execute(tasks, ui, storage);
        output = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);
    }

    @Test
    public void testDoneDelete() {
        //Test marking invalid task index
        String[] invalidDoneIndex = {"done", "a"};
        assertDoesNotThrow(() -> new DukeCommandUpdate(invalidDoneIndex).execute(tasks, ui, storage));

        //Test deleting invalid task index
        String[] invalidDeleteIndex = {"delete", "a"};
        assertDoesNotThrow(() -> new DukeCommandUpdate(invalidDeleteIndex).execute(tasks, ui, storage));

        //Test missing input index parameter for done command
        systemOutRule.clearLog();
        String[] invalidDoneIndexMissingParam = {"done"};
        new DukeCommandUpdate(invalidDoneIndexMissingParam).execute(tasks, ui, storage);
        String output = systemOutRule.getLogWithNormalizedLineSeparator();

        systemOutRule.clearLog();
        ui.displayMissingIndex();
        String errorMessage = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);

        //Test missing input index parameter for delete command
        systemOutRule.clearLog();
        String[] invalidDeleteIndexMissingParam = {"delete"};
        new DukeCommandUpdate(invalidDeleteIndexMissingParam).execute(tasks, ui, storage);
        output = systemOutRule.getLogWithNormalizedLineSeparator();
        assertEquals(output, errorMessage);
    }
}
