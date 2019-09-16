package duke.parser;

import duke.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DateParserTest {
    @Test
    public void parse_success() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            assertEquals(DateParser.parse("12/03/2301 1200").toString(),
                         formatter.parse("12/03/2301 1200").toString());
            assertEquals(DateParser.parse("12/03/2301 2359").toString(),
                         formatter.parse("12/03/2301 2359").toString());
            assertEquals(DateParser.parse("01/01/0532 1200").toString(),
                         formatter.parse("01/01/0532 1200").toString());
            assertEquals(DateParser.parse("01/01/0000 0000").toString(),
                         formatter.parse("01/01/0000 0000").toString());
            assertEquals(DateParser.parse("12/31/9999 2359").toString(),
                         formatter.parse("12/31/9999 2359").toString());
        } catch (ParseException pe) {
            fail();
        } catch(InvalidDateTimeException idte) {
            fail();
        }
    }

    @Test
    public void parse_fail_with_ExceptionThrow() {
        Assertions.assertThrows(InvalidDateTimeException.class, () -> DateParser.parse("233555"));
        Assertions.assertThrows(InvalidDateTimeException.class, () -> DateParser.parse(""));
        Assertions.assertThrows(InvalidDateTimeException.class, () -> DateParser.parse("11/02/2931"));
        Assertions.assertThrows(InvalidDateTimeException.class, () -> DateParser.parse("11/02/2931 test"));
        Assertions.assertThrows(NullPointerException.class, () -> DateParser.parse(null));
    }

}
