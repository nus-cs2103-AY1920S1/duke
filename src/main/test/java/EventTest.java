import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EventTest {

    Date d1 = new SimpleDateFormat("ddMMyy").parse("070417");
    Event e1 = new Event("Finish assignment", d1);
        
    @Test
    public void getAt_valid_success() {
        assertEquals(e1.getAt(), d1);
    }
}