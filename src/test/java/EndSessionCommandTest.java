import commands.EndSessionCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndSessionCommandTest {
    @Test
    void isExit() {
        EndSessionCommand c = new EndSessionCommand();
        assertTrue(c.isExit());
    }
}
