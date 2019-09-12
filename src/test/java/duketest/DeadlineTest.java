import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineTest {

    private static final String CIRCLE = "O";    
    private static final String CROSS = "X";
    
    @Test
    public void instanceTest() {
        // Test for Date, Time and Both
        try {   
            new Deadline("This is a Deadline.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    LocalTime.parse("22:22", Task.TIME_FORMATTER));
        } catch (Exception e) {
            fail();
        }

        try {
            new Deadline("This is a Deadline.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    null);
        } catch (Exception e) {
            fail();
        }

        try {
            new Deadline("This is a Deadline.", 
                    null,
                    LocalTime.parse("22:22", Task.TIME_FORMATTER));
        } catch (Exception e) {
            fail();
        }
        
        // Test for wrong time, date or both
        try {
            new Deadline("This is a Deadline.", 
                    LocalDate.parse("99/99/9999", Task.DATE_FORMATTER),
                    LocalTime.parse("99:99", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Deadline("This is a Deadline.", 
                    LocalDate.parse("99/99/9999", Task.DATE_FORMATTER),
                    null);
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Deadline("This is a Deadline.", 
                    null,
                    LocalTime.parse("99:99", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }

        try {
            new Deadline("This is a Deadline.", 
                    LocalDate.parse("11/11/2018", Task.DATE_FORMATTER),
                    LocalTime.parse("66:66", Task.TIME_FORMATTER));
            fail();
        } catch (Exception e) {
            // Pass
        }
        
    }

    @Test
    public void toStringTest() {
        Deadline deadline;
        try {
            deadline = new Deadline("This is a Deadline.", 
                            LocalDate.parse("14/05/2020", Task.DATE_FORMATTER),
                            null);
        } catch (Exception e) {
            deadline = null;
            fail();
        }
        assertEquals("[D][" + CROSS + "] This is a Deadline. (by: 14/05/2020)", 
                deadline.toString());
        deadline.setCompleted(true);    
        assertEquals("[D][" + CIRCLE + "] This is a Deadline. (by: 14/05/2020)", 
                deadline.toString());
    }


}