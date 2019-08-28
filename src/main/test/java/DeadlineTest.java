import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineTest {

    Date d1 = new SimpleDateFormat("ddMMyy").parse("070417");
    Deadline dl1 = new Deadline("Finish assignment", d1);
        
    @Test
    public void getBy_valid_success() {
        assertEquals(dl1.getBy(), d1);
    }
}