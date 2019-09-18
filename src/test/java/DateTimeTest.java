import taskchick.datetime.DateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void testDateTime_validInput_success() {
        assertEquals("31st of April 2019, 4:32pm", (DateTime.create("31/4/2019 1632")).toString());
        assertEquals("22nd of September 2030, 12:00am", (DateTime.create("22/9/2030 0000")).toString());
        assertEquals("3rd of March 1930, 8:20am", (DateTime.create("3/3/1930 0820")).toString());
        assertEquals("16th of February 1966, 7:12pm", (DateTime.create("16/2/1966 1912")).toString());
    }

}
