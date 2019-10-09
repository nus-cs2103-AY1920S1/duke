import duke.parser.Parser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseStoredTime_unusualTimeFormat_success() {
        assertEquals(LocalDateTime.of(1999, 1, 20, 01, 00),
                Parser.parseStoredTime(new String[]{"D", "-", "test case", "20/01/1999 0100"}));
    }
}
