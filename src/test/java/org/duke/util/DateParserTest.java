package org.duke.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void exactDateTimeTest() {
        assertEquals(DateParser.parse("1 May 2019 12AM"),
                LocalDate.of(2019, Month.MAY, 1)
                        .atTime(LocalTime.MIDNIGHT));
    }
}
