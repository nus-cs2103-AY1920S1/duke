import duke.Duke;
import duke.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void todoTaskInfoTest() {
        try {
            Task t = new Todo("Eat Dinner");
            assertEquals("[T][\u2715] Eat Dinner",t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void todoRecordInfoTest() {
        try {
            Task t = new Todo("Eat Dinner");
            assertEquals("T|0|Eat Dinner",t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void todoFinishTest1() {
        try {
            Task t = new Todo("Eat Dinner");
            t.setAsFinish();
            assertEquals("[T][\u2713] Eat Dinner",t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void todoFinishTest2() {
        try {
            Task t = new Todo("Eat Dinner");
            t.setAsFinish();
            assertEquals("T|1|Eat Dinner",t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void todoInvalidTaskNameTest() {
        try {
            Task t = new Todo("");
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void deadlineTaskInfoTest() {
        try {
            Task t = new Deadline("Eat dinner", "12/08/2019 18:00:00");
            assertEquals("[D][\u2715] Eat dinner (by: 12/08/2019 18:00:00)", t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void deadlineRecordInfoTest() {
        try {
            Task t = new Deadline("Eat dinner", "12/08/2019 18:00:00");
            assertEquals("D|0|Eat dinner|12/08/2019 18:00:00", t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void deadlineFinishTest1() {
        try {
            Task t = new Deadline("Eat dinner", "12/08/2019 18:00:00");
            t.setAsFinish();
            assertEquals("[D][\u2713] Eat dinner (by: 12/08/2019 18:00:00)", t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void deadlineFinishTest2() {
        try {
            Task t = new Deadline("Eat dinner", "12/08/2019 18:00:00");
            t.setAsFinish();
            assertEquals("D|1|Eat dinner|12/08/2019 18:00:00", t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void deadlineInvalidNameTest() {
        try {
            Task t = new Deadline("", "12/08/2019 18:00:00");
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    void deadlineInvalidTimeTest() {
        try {
            Task t = new Deadline("", "gouliguojiashengsiyi");
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    void eventTaskInfoTest() {
        try {
            Task t = new Event("Eat dinner", "12/08/2019 18:00:00");
            assertEquals("[E][\u2715] Eat dinner (at: 12/08/2019 18:00:00)", t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void eventRecordInfoTest() {
        try {
            Task t = new Event("Eat dinner", "12/08/2019 18:00:00");
            assertEquals("E|0|Eat dinner|12/08/2019 18:00:00", t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void eventFinishTest1() {
        try {
            Task t = new Event("Eat dinner", "12/08/2019 18:00:00");
            t.setAsFinish();
            assertEquals("[E][\u2713] Eat dinner (at: 12/08/2019 18:00:00)", t.taskInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void eventFinishTest2() {
        try {
            Task t = new Event("Eat dinner", "12/08/2019 18:00:00");
            t.setAsFinish();
            assertEquals("E|1|Eat dinner|12/08/2019 18:00:00", t.recordInfo());
        } catch (DukeException e) {
            assertEquals(1, 2);
        }
    }

    @Test
    void eventInvalidNameTest() {
        try {
            Task t = new Event("", "12/08/2019 18:00:00");
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    void eventInvalidTimeTest() {
        try {
            Task t = new Event("Eat dinner", "qiyinhuofubiquzhi");
            assertEquals(1, 2);
        } catch (DukeException e) {
            assertEquals(1, 1);
        }
    }
}
