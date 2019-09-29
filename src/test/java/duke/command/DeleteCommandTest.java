package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static duke.util.ObjectsForTest.DEADLINE1;
import static duke.util.ObjectsForTest.EVENT;
import static duke.util.ObjectsForTest.FILE_PATH;
import static duke.util.ObjectsForTest.TODO1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteCommandTest {

    private final Ui ui = new Ui();
    private final Storage storage = new Storage(FILE_PATH);

    @Test
    void execute_deleteValidIndex_success() {
        TaskList originalList = new TaskList(TODO1, DEADLINE1, EVENT);
        TaskList deletedList = new TaskList(TODO1, EVENT);
        final int VALID_NUM = 1;

        DeleteCommand deleteCommand = new DeleteCommand(VALID_NUM);
        try {
            deleteCommand.execute(originalList, ui, storage);
            assertEquals(deletedList, originalList);
        } catch (DukeException e) {
            fail("Unexpected Exception");
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 3, 4, 5})
    void execute_deleteOutboundIndex_failure(int seqNum) {
        TaskList originalList = new TaskList(TODO1, DEADLINE1, EVENT);
        DeleteCommand deleteCommand = new DeleteCommand(seqNum);
        assertThrows(DukeException.class, () -> deleteCommand.execute(originalList, ui, storage));
    }
}