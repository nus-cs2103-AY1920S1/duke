package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {

    public void testParseDate(){
        assertEquals("Sun Dec 15 12:10:00 SGT 1996", new DateParser().parseDate("15/12/1996 1210").toString());
    }

    void testParseDate_invalidFormat_null(){
        assertEquals(null, new DateParser().parseDate("15121996 1210"));
    }
}
