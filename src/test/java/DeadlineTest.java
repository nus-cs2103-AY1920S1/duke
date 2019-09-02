


import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represent a Test class for Deadline class.
 * The 'DeadlineTest' class supports the following operators
 * (i) Checking that a deadline object can be instantiated correctly,
 * (ii) Checking that a Deadline object with Date as parameter have
 * the correct string representation, and
 * (iii) Checking that a Deadline object with string as a Deadline parameter,
 * has the correct string representation.
 */
public class DeadlineTest {

    /**
     * Tests that a Deadline object can be instantiated correctly.
     * Asserts that a Deadline object is created.
     */
    @Test
    public void instantiate_instantiateWithIsDone_deadlineObject() {
        Deadline deadline = new Deadline("1", "read book", "01/02/1999 1900");

        assertTrue(deadline instanceof Deadline);
    }


    /**
     * Asserts that the string representation of Deadline object is correct.
     * The Deadline object created uses a String as its 'deadline' parameter.
     *
     * @throws ParseException If there is IOException when reading or writing from text file.
     */
    @Test
    public void testToString_objectWithDatAsDateAsParameter_string() throws ParseException {
        Date deadline = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("01/02/1999 1900".trim());

        assertEquals("[D][x] read book (by: Mon Feb 01 19:00:00 SGT 1999)\n", new Deadline("read book", deadline).toString());
    }

    /**
     * Asserts that the string representation of Deadline object is correct.
     * The Deadline object created uses Date as its 'deadline' parameter.
     *
     * @throws ParseException If there is IOException when reading or writing from text file.
     */
    @Test
    public void testToString_objectWithStringAsDateParameter_string() throws ParseException {
        assertEquals("[D][x] read book (by: Mon Aug 02 00:00:00 SGT 1999)\n", new Deadline("1", "read book", " Mon Aug 02 00:00:00 SGT 1999").toString());
    }
}