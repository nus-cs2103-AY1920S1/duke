import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
class EventsImplementationTest {
    @Test
    public void toStringConversion() {
        EventsImplementation d = new EventsImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertEquals("[E][\u2713] Summon Cthulhu (at: Innsmouth Harvest Festival)", d.toString());


        EventsImplementation e = new EventsImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertEquals("[E][\u2718] Find Nyarlatothep a new footrest (at: Completetion of underground pyramid)", e.toString());

    }

    @Test
    public void completeTaskConversion() {
        EventsImplementation e = (EventsImplementation) new EventsImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false).completeTask();
        assertEquals("[E][\u2713] Find Nyarlatothep a new footrest (at: Completetion of underground pyramid)", e.toString());
    }

    @Test
    public void getIsDone_test() {
        EventsImplementation e = new EventsImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertFalse(e.getIsDone());

        EventsImplementation d = new EventsImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertTrue(d.getIsDone());

        EventsImplementation f = new EventsImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertTrue(((EventsImplementation) f.completeTask()).getIsDone());
    }

    @Test
    public void getSaveFormat_test() {
        EventsImplementation d = new EventsImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertEquals("E | 1 | Summon Cthulhu | Innsmouth Harvest Festival", d.getSaveFormat());


        EventsImplementation e = new EventsImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertEquals("E | 0 | Find Nyarlatothep a new footrest | Completetion of underground pyramid", e.getSaveFormat());


        EventsImplementation f = (EventsImplementation) new EventsImplementation("Climb Mountain of Madness", "Winter", false);
        assertEquals("E | 1 | Climb Mountain of Madness | Winter", f.completeTask().getSaveFormat());

    }

}
