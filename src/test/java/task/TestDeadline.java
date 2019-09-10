package task;

import duke.task.Deadline;
import duke.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeadline {

    private Deadline deadline;

    @BeforeEach
    public void setUp() throws ParseException {
        deadline = new Deadline("return a book", DateUtil.parse("1/12/2019 1800"));
    }

    @Test
    public void toString_normal_noException() {
        assertEquals("[D][✘] return a book (by: 01/12/2019 1800)", deadline.toString());
    }

    @Test
    public void stringify_normal_noException() {
        assertEquals("D | 0 | return a book | 01/12/2019 1800", deadline.stringify());
    }
}
