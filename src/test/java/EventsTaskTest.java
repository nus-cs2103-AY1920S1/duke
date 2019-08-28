import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventsTaskTest {

    @Test
    public void testToFileFormat() {
        DateTime startingTime = new DateTimeStubSixthAugustTwopm();
        DateTime endingTime = new DateTimeStubSixthAugustFourpm();
        
        assertEquals("E|0|project meeting|6 Aug 2019 14 00 16 00", 
        new EventsTask(false, "project meeting", startingTime, endingTime).toString());
    }

    
}