import org.junit.jupiter.api.Test;

import task.Todo;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void toDo_eat_success() {
        Todo todo = new Todo("eat", 1);
        assertEquals("[T][X] eat (PRIORITY: HIGH)", todo.toString());
    }

    @Test
    public void deadLine_cs2103tIp_success() {
        Deadline deadline = new Deadline("CS2103T iP", "6/9/2019", 2);
        assertEquals("[D][X] CS2103T iP (by: Fri Sep 06 00:00:00 SGT 2019) (PRIORITY: MEDIUM)", deadline.toString());
    }
}