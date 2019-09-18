import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineTest {

    @Test
    public void getBy_valid_success() {
        try {
            Date d1 = new SimpleDateFormat("ddMMyy").parse("070417");
            Deadline dl1 = new Deadline("Finish assignment", d1);
            assertEquals(dl1.getBy(), d1);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

}