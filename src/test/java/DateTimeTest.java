import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    void testGetRawForm() {
        assertEquals("12/12/1999 1830", new DateTime("12/12/1999 1830").getRawForm());
        assertEquals("2/12/2019 1800", new DateTime("2/12/2019 1800").getRawForm());
        assertEquals("2/5/2000 1159", new DateTime("2/5/2000 1159").getRawForm());
        assertEquals("3/5/2005 0932", new DateTime("3/5/2005 0932").getRawForm());
    }

    @Test
    void testAppendOrdinal() {
        DateTime dateTime = new DateTime("12/12/1999 1830");
        String[] expectedResults = {"1st", "2nd", "3rd", "4th", "5th","6th",
                "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th",
                "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd",
                "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th",
                "31st"};
        int index = 1;
        for (String expected : expectedResults) {
            assertEquals(expected, dateTime.appendOrdinal(index));
            index++;
        }
    }

    @Test
    void testTo12HourTime() {
        DateTime dateTime = new DateTime("12/12/2019 1300");
        assertEquals("1pm", dateTime.to12HourTime(13, 0));
        assertEquals("1.25pm", dateTime.to12HourTime(13, 25));
        assertEquals("9am", dateTime.to12HourTime(9,0));
        assertEquals("1am", dateTime.to12HourTime(1,0));
        assertEquals("4.59pm", dateTime.to12HourTime(16, 59));
        assertEquals("10.32am", dateTime.to12HourTime(10,32));
        assertEquals("10.32pm", dateTime.to12HourTime(22, 32));
    }

    @Test
    void testToString() {
        assertEquals("2nd of December 2019, 6pm", new DateTime("2/12/2019 1800").toString());
        assertEquals("3rd of December 2019, 6pm", new DateTime("3/12/2019 1800").toString());
        assertEquals("4th of December 2019, 6pm", new DateTime("4/12/2019 1800").toString());
        assertEquals("11th of December 2019, 6pm", new DateTime("11/12/2019 1800").toString());
        assertEquals("12th of December 2019, 6pm", new DateTime("12/12/2019 1800").toString());
        assertEquals("13th of December 2019, 6pm", new DateTime("13/12/2019 1800").toString());
        assertEquals("21st of December 2019, 6pm", new DateTime("21/12/2019 1800").toString());
        assertEquals("22nd of December 2019, 6pm", new DateTime("22/12/2019 1800").toString());
        assertEquals("23rd of December 2019, 6pm", new DateTime("23/12/2019 1800").toString());
        assertEquals("24th of December 2019, 6pm", new DateTime("24/12/2019 1800").toString());
    }
}