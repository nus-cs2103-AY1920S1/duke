


import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    @Test
    public void instatiate_instantiateWithIsDone_todoObject() {
        Deadline deadline = new Deadline("1", "read book", "01/02/1999 1900");

        assertTrue(deadline instanceof Deadline);
    }


    @Test
    public void testToString_objectWithDatAsDateAsParameter_string() throws ParseException {
        Date deadline = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/02/1999 1900".trim());

        assertEquals("[D][x] read book (by: Mon Feb 01 19:00:00 SGT 1999)\n", new Deadline("read book", deadline).toString());
    }

    @Test
    public void testToString_objectWithStringAsDateParameter_string() {
        assertEquals("[D][x] read book (by: Mon Aug 02 00:00:00 SGT 1999)\n", new Deadline("1", "read book", " Mon Aug 02 00:00:00 SGT 1999").toString());
    }
}