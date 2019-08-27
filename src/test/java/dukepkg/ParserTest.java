package dukepkg;

import org.junit.Test;

import static dukepkg.Parser.formatTime;
import static junit.framework.TestCase.assertEquals;

public class ParserTest {
    @Test
    public void formatTimeTest(){
        assertEquals("12nd of November 2019, 2pm", formatTime("12/11/2019 1400"));
        assertEquals("8th of December 2008, 6pm", formatTime("8/12/2008 1800"));
    }
}