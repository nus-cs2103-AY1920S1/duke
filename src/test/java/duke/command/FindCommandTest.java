package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static duke.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    private static final String KEYWORD = "early";
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(FILE_PATH);
    private final FindCommand findCommand= new FindCommand(KEYWORD);

    @Test
    void execute_nonEmptyList_success() {
            String resultList = findCommand.execute(TASK_LIST_ALL, ui, storage);
            assertEquals(ui.showFullList(TASK_LIST_TODO), resultList);
    }

    @Test
    void execute_emptyList_success() {
        TaskList taskList = new TaskList();
        String resultList = findCommand.execute(taskList, ui, storage);
        assertEquals(ui.showNoTask(), resultList);
    }
}

