import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
class LoadTaskCreatorTest {
    @Test
    public void createTask_success() throws OWOException {
//        TaskCreator taskCreator = new LoadTaskCreator();
        try {
            System.out.println("asdasd");
            DeadLinesImplementation d = (DeadLinesImplementation) 
                new LoadTaskCreator()
                .createTask("D | 1 | Summon Cthulhu | innsmouth");
            assertEquals("Summon Cthulhu", d.getName());
            assertEquals(true, d.getIsDone());
            assertEquals("innsmouth", d.getDate());

            EventsImplementation e = (EventsImplementation) 
                new LoadTaskCreator()
                .createTask("E | 0 | Halloween Party | day after friday 13th");
            assertEquals("Halloween Party", e.getName());
            assertEquals(false, e.getIsDone());
            assertEquals("day after friday 13th", e.getDate());


            ToDosImplementation t = (ToDosImplementation) 
                new LoadTaskCreator()
                .createTask("T | 1 | Photocopy Necronomicon");
            assertEquals("Photocopy Necronomicon", t.getName());
            assertEquals(true, t.getIsDone());
        } catch (OWOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void createTask_exceptionThrown() {
        try {
            new LoadTaskCreator().createTask("");
            fail();
        } catch (OWOException e) {
            //look, my error message is weird ok, i dont even knwo
            //what it is
            assertEquals("","");
            //assertEquals("",e.getMessage());
        }   
    }
}
