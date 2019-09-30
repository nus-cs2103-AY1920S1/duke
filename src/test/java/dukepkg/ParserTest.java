package dukepkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dukepkg.Parser.formatTime;

class ParserTest {
    @Test
    public void formatTimeTest(){
        Assertions.assertEquals("12nd of November 2019, 2pm", formatTime("12/11/2019 1400"));
        Assertions.assertEquals("8th of December 2008, 6pm", formatTime("8/12/2008 1800"));
    }
}