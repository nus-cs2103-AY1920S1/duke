import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class DukeTest {
    @Test
    public void testList() {
        TaskList tasks1 = new TaskList();
        assertEquals(new ArrayList<Task>(), tasks1.getList());
    }
}