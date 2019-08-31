import duke.exception.DukeException;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void setIsDone_emptyArrayList_dukeExceptionThrown(){
        int testIndex = 1;
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            tasks.setIsDone(1);
            fail();
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! There is no task number " + (testIndex + 1), e.getMessage());
        }
    }
}
