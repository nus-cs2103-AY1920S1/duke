package datetime;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void setDeadLine_incorrectFormat_exceptionThrown(){
        try {
            DateTime.setDeadline("30.11.2019 05:00");
            fail();
        } catch (DukeException e){
            System.out.println(e.getMessage());
            assertEquals("Incorrect Deadline date/time format." + System.lineSeparator() +
                    "    Please key in date(d/M/yyyy) time(HHmm)", e.getMessage());
        }
    }
    @Test
    public void readDeadLineTest(){
        DateTime deadline = DateTime.readDeadLine("30/11/2019 0500");
        assertEquals("30/11/2019 0500", deadline.toString());
    }
    @Test
    public void setEventTime_noEndTime_exceptionThrown(){
        try {
            DateTime.setEventTime("30/11/2019 0500-");
            fail();
        } catch (DukeException e){
            assertEquals("Need to have end time.", e.getMessage());
        }
    }
    @Test
    public void setEventTime_incorrectFormat_exceptionThrown(){
        try {
            DateTime.setEventTime("30.11.2019 05:00-05:30");
            fail();
        } catch (DukeException e){
            assertEquals("Incorrect Event date/time format." + System.lineSeparator() +
                    "    Please key in date(d/M/yyyy) start_time(HHmm)-end_time(HHmm)", e.getMessage());
        }
    }
    @Test
    public void readEventTimeTest(){
        try {
            DateTime eventTime = DateTime.readEventTime("30/11/2019 0500-0600");
            assertEquals("30/11/2019 0500-0600", eventTime.toString());
        } catch (DukeException e){
            fail();
        }
    }
    @Test
    public void readEventTime_noEndTime_exceptionThrown(){
        try {
            DateTime eventTime = DateTime.readEventTime("30/11/2019 0500-");
            fail();
        } catch (DukeException e){
            assertEquals("Need to have end time.", e.getMessage());
        }
    }
}

