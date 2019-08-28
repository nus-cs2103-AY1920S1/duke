import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private AddCommand cd;

    @Test
    public void commandTest() {
        cd = new AddCommand("event task /at 12345".split(" ", 2));
        assertEquals(false, AddCommand.isValidTime("12345"));
    }
}
