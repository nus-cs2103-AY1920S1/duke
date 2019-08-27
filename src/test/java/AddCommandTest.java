import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void check_getDesc() {
        AddCommand c = new AddCommand("todo", "Project");
        assertEquals("Project", c.getDesc());
    }

    @Test
    public void check_getDateTime() {
        AddCommand c = new AddCommand("event", "Workshop", "23/3/2019 1200");
        assertEquals("23/3/2019 1200", c.getDateTime());
    }

    @Test
    public void check_isExit() {
        AddCommand c = new AddCommand("deadline", "Submit Quiz", "23/3/2019 1200");
        assertEquals(false, c.isExit());
    }
}
