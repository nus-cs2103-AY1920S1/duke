import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
class ToDosImplementationTest {
    @Test
    public void toStringConversion() {
        ToDosImplementation d = new ToDosImplementation("Summon Cthulhu", true);
        assertEquals("[T][\u2713] Summon Cthulhu", d.toString());


        ToDosImplementation e = new ToDosImplementation("Find Nyarlatothep a new footrest", false);
        assertEquals("[T][\u2718] Find Nyarlatothep a new footrest", e.toString());

    }

    @Test
    public void completeTaskConversion() {
        ToDosImplementation e = (ToDosImplementation) 
            new ToDosImplementation("Find Nyarlatothep a new footrest", false).completeTask();
        assertEquals("[T][\u2713] Find Nyarlatothep a new footrest", e.toString());
    }

    @Test
    public void getIsDone_test() {
        ToDosImplementation e = new ToDosImplementation("Find Nyarlatothep a new footrest", false);
        assertFalse(e.getIsDone());

        ToDosImplementation d = new ToDosImplementation("Summon Cthulhu", true);
        assertTrue(d.getIsDone());

        ToDosImplementation f = new ToDosImplementation("Find Nyarlatothep a new footrest", false);
        assertTrue(((ToDosImplementation) f.completeTask()).getIsDone());
    }

    @Test
    public void getSaveFormat_test() {
        ToDosImplementation d = new ToDosImplementation("Summon Cthulhu", true);
        assertEquals("T | 1 | Summon Cthulhu", d.getSaveFormat());


        ToDosImplementation e = new ToDosImplementation("Find Nyarlatothep a new footrest", false);
        assertEquals("T | 0 | Find Nyarlatothep a new footrest", e.getSaveFormat());


        ToDosImplementation f = (ToDosImplementation) new ToDosImplementation("Climb Mountain of Madness", false);
        assertEquals("T | 1 | Climb Mountain of Madness", f.completeTask().getSaveFormat());

    }

}
