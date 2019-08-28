import org.junit.jupiter.api.Test;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parser_parseline_success() {
        assertEquals(new Todo("eat food").toString(),
                     Parser.parseLine("todo,eat food,false,null").toString());
    }

    @Test
    public void parser_date_value() {
        assertEquals(new Date(995).toString(), Parser.parseDate("01/01/1970 0730").toString());
    }

}
