import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.Calendar;

public class DukeTest {
    @Test
    public void dummyTest() {
        Calendar c = Calendar.getInstance();
        c.set(2019,8,29,18,00);
        Deadline testDeadline = new Deadline("homework", c.getTime());
        System.out.println();
        assertEquals(2, 2);
    }
}
