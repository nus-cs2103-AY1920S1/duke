import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void formatDateTest() {
        assertEquals("23rd of September 1997, 6:27 am",
                new Task("deadline 2103T Project").formatDate("23/09/1997 0627"));
    }
}
