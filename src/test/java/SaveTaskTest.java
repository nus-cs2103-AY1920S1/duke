import taskchick.task.Deadline;
import taskchick.task.Event;
import taskchick.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveTaskTest {

    @Test
    public void saveTask_validTask_success() {
        assertEquals("E | 0 | have project meeting | 12/3/2020 1430",
                (new Event("have project meeting", "12/3/2020 1430")).toSave());
        assertEquals("T | 0 | do homework", (new Todo("do homework")).toSave());
        assertEquals("D | 0 | complete level 6 | 20/8/2019 2359",
                (new Deadline("complete level 6", "20/8/2019 2359")).toSave());
    }
}
