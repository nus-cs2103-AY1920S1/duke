import org.junit.jupiter.api.Test;
import task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals(new Event("Dance competition", "15/08/2019", "1600").toString(),
                "[E][-] Dance competition (at: 15th August 2019 4pm)");
    }
    @Test
    public void toDataFormatTest() {
        assertEquals(new Event("Date at art museum", "16/08/2019", "1500").toDataFormat(),
                "E | - | Date at art museum | 16/08/2019 | 1500");
    }
}
