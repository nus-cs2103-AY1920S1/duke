import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    @Test
    void deadlineSaveInfoTest() {
        Calendar c = Calendar.getInstance();
        c.set(2019,8,29,18,00);
        Deadline testDeadline = new Deadline("homework", c.getTime());
        assertEquals("deadline homework /by 29/09/2019 1800" + System.getProperty("line.separator") + "false", testDeadline.saveInfo());
    }

    @Test
    void todoTaskDoneTest() {
        TaskList tl = new TaskList();
        try {
            tl.parseInput("todo task1", true);
            tl.parseInput("done 1", true);
        } catch (DukeException | ParseException e) {
            System.out.println("error in parsing input.");
        }

        assertTrue(tl.getTaskList().get(0).isCompleted());
    }

    @Test
    void eventTaskDoneTest() {
        TaskList tl = new TaskList();
        try {
            tl.parseInput("todo task1", true);
            tl.parseInput("event abc123 /at 29/09/2019 1900", true);
            tl.parseInput("done 2", true);
        } catch (DukeException | ParseException e) {
            System.out.println("error in parsing input.");
        }

        assertFalse(tl.getTaskList().get(0).isCompleted());
        assertTrue(tl.getTaskList().get(1).isCompleted());
    }
}
