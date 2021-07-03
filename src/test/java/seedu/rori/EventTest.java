import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {

    private static final String CIRCLE = "O";   
    private static final String CROSS = "X";    

    @Test
    public void instanceTest() {
        // Test for Date, Time and Both
        try {   
            new Event("This is an Event.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    LocalTime.parse("22:22", Task.TIME_FORMATTER));
        } catch (Exception e) {
            fail();
        }

        try {
            new Event("This is an Event.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    null);
        } catch (Exception e) {
            fail();
        }

        try {
            new Event("This is an Event.", 
                    null,
                    LocalTime.parse("22:22", Task.TIME_FORMATTER));
        } catch (Exception e) {
            fail();
        }
        
        // Test for wrong time, date or both
        try {
            new Event("This is an Event.", 
                    LocalDate.parse("99/99/9999", Task.DATE_FORMATTER),
                    LocalTime.parse("99:99", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Event("This is an Event.", 
                    LocalDate.parse("99/99/9999", Task.DATE_FORMATTER),
                    null);
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Event("This is an Event.", 
                    null,
                    LocalTime.parse("99:99", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Event("This is an Event.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    LocalTime.parse("99:99", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }
        
    }

    @Test
    public void toStringTest() {
        Event event;
        try {
            event = new Event("This is an Event.", 
                            LocalDate.parse("14/05/2020", Task.DATE_FORMATTER),
                            null);
        } catch (Exception e) {
            event = null;
            fail();
        }
        assertEquals("[E][" + CROSS + "] This is an Event. (at: 14/05/2020)", 
                event.toString());
        event.setCompleted(true);    
        assertEquals("[E][" + CIRCLE + "] This is an Event. (at: 14/05/2020)", 
                event.toString());
    }


}