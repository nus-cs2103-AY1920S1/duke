import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.task.DeadlineTask;

public class DeadlineTaskTest {
    @Test
    @DisplayName("Encoded string of DeadlineTask is correct")
    public void testToEncodedString() throws ParseException {
        // Test setup
        String dateString = "12/12/2012 1212";
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = dateParser.parse(dateString);
        DeadlineTask task = new DeadlineTask("Finish duking it out", date);
        task.complete();

        assertEquals(
            String.format("D | 1 | Finish duking it out | %s", dateString),
            task.toEncodedString()
        );
    }

    @Test
    @DisplayName("String representation of DeadlineTask is correct")
    public void testToString() throws ParseException {
        // Test setup
        String dateString = "01/02/0304 0506";
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");
        Date date = dateParser.parse(dateString);
        String formattedDate = dateFormatter.format(date);
        DeadlineTask task = new DeadlineTask("S/U this module", date);

        assertEquals(
            String.format("[D][X] S/U this module (by: %s)", formattedDate),
            task.toString()
        );
    }
}
