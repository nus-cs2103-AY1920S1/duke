import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTest {

    @Test
    public void getBy_valid_success() {
        try {
            Date d1 = new SimpleDateFormat("ddMMyy").parse("070417");
            Event ev1 = new Event("Project meeting", d1);
            assertEquals(ev1.getAt(), d1);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
}