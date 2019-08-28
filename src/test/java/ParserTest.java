import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    @Test
    public void testDateTimeParse_success() throws Exception {
        assertEquals(LocalDateTime.of(1998,12,21,12,50),
                Parser.parseDateTime("21/12/1998 1250"));
    }

    @Test
    public void testDateTimeParse_fail() {
        try {
            assertEquals(LocalDateTime.of(1998,12,21,12,50),
                    Parser.parseDateTime("INVALID INPUT"));
            fail();
        } catch (Exception e) {}
    }
}
