package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion1() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        assertEquals("[D][✗] return book (by: 02/12/2019 15.21 PM)", new Deadline("return book", date).toString());
    }

    @Test
    public void testStringConversion2() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        Deadline deadlineTask = new Deadline("return book", date);
        deadlineTask.markAsDone();
        assertEquals("[D][✓] return book (by: 02/12/2019 15.21 PM)", deadlineTask.toString());
    }

    @Test
    public void testWriteToFile1() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        Deadline deadlineTask = new Deadline("return book", date);
        deadlineTask.markAsDone();
        assertEquals("D | 1 | return book | 02/12/2019 1521", deadlineTask.writeToFile());
    }

}
