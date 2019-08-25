import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
class DeadLinesImplementationTest {
    @Test
    public void toStringConversion() {
        DeadLinesImplementation d = new DeadLinesImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertEquals("[D][\u2713] Summon Cthulhu (by: Innsmouth Harvest Festival)", d.toString());


        DeadLinesImplementation e = new DeadLinesImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertEquals("[D][\u2718] Find Nyarlatothep a new footrest (by: Completetion of underground pyramid)", e.toString());

    }

    @Test
    public void completeTaskConversion() {
        DeadLinesImplementation e = (DeadLinesImplementation) new DeadLinesImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false).completeTask();
        assertEquals("[D][\u2713] Find Nyarlatothep a new footrest (by: Completetion of underground pyramid)", e.toString());
    }

    @Test
    public void getIsDone_test() {
        DeadLinesImplementation e = new DeadLinesImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertFalse(e.getIsDone());

        DeadLinesImplementation d = new DeadLinesImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertTrue(d.getIsDone());

        DeadLinesImplementation f = new DeadLinesImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertTrue(((DeadLinesImplementation) f.completeTask()).getIsDone());
    }

    @Test
    public void getSaveFormat_test() {
        DeadLinesImplementation d = new DeadLinesImplementation("Summon Cthulhu", "Innsmouth Harvest Festival", true);
        assertEquals("D | 1 | Summon Cthulhu | Innsmouth Harvest Festival", d.getSaveFormat());


        DeadLinesImplementation e = new DeadLinesImplementation("Find Nyarlatothep a new footrest", "Completetion of underground pyramid", false);
        assertEquals("D | 0 | Find Nyarlatothep a new footrest | Completetion of underground pyramid", e.getSaveFormat());


        DeadLinesImplementation f = (DeadLinesImplementation) new DeadLinesImplementation("Climb Mountain of Madness", "Winter", false);
        assertEquals("D | 1 | Climb Mountain of Madness | Winter", f.completeTask().getSaveFormat());

    }

}
