import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineGetBy() {
        Deadline dd = new Deadline("splashdown poster", new Date(1000000000));
        assertEquals(new Date(1000000000), dd.getBy());
    }

}
