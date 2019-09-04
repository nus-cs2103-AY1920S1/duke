import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.task.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetDateFromFile() throws ParseException {
        String date = "Mon Sep 02 16:00:00 SGT 2019";
        Date expected = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(date);
        assertEquals(expected, Parser.getDateFromFile(date));
    }

    @Test
    public void testGetDateFromUser() throws ParseException {
        String date = "01/09/2019 0000";
        Date expected = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        assertEquals(expected, Parser.getDateFromUser(date));
    }
}
