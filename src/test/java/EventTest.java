import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    @Test
    public void toSaveString_normalTask_writtenCorrectly() {
        String dateAndTime = "21/09/2019 1231";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
        Event e = new Event("Do the dishes", dateTime);
        assertEquals("E | 0 | Do the dishes | 2019-09-21T12:31", e.toSaveString());
    }

    @Test
    public void generateDateAndTimeString_correctInput_CorrectDateAndTimeString() {
        String dateAndTime = "21/09/2019 1231";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
        Event e = new Event("Do the dishes", dateTime);
        assertEquals("21st of September 2019, 12.31pm", e.generateDateAndTimeString());
    }
}
