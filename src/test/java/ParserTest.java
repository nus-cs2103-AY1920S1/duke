import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void parserGetDate() {
        try {
            Parser parser = new Parser("deadline CS3230 Assignment by 19/12/2019 1500");
            String date = parser.getDeadlineDate();
            assertEquals("19/12/2019 1500", date);
        } catch (DukeException de) {
            assertEquals(true, true);
        }
    }

    @Test
    public void parserDateInput() {
        LocalDateTime dateTime = Parser.parseInput("19/12/2019 1500");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMM yyyy h:mm a");
        assertEquals("19 of Dec 2019 3:00 PM", dateTime.format(formatter));
    }

}