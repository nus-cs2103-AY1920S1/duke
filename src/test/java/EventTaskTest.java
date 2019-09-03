import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.task.EventTask;

public class EventTaskTest {
    @Test
    @DisplayName("Encoded string of EventTask is correct")
    public void testToEncodedString() throws ParseException {
        // Test setup
        String dateString = "09/08/0706 0504";
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = dateParser.parse(dateString);
        EventTask task = new EventTask("Make useless PRs", date);

        assertEquals(
            String.format("E | 0 | Make useless PRs | %s", dateString),
            task.toEncodedString()
        );
    }

    @Test
    @DisplayName("String representation of EventTask is correct")
    public void testToString() throws ParseException {
        // Test setup
        String dateString = "31/12/2019 2359";
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");
        Date date = dateParser.parse(dateString);
        String formattedDate = dateFormatter.format(date);
        EventTask task = new EventTask("Receive Happy New Year text messages", date);
        task.complete();

        assertEquals(
            String.format("[E][V] Receive Happy New Year text messages (at: %s)", formattedDate),
            task.toString()
        );
    }
}
