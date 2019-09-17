import tagModule.AbstractTagCommandPost;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;


public class AbstractTagCommandPostTest {


    @Test
    public void addTaggedItem_test() {
        AbstractTagCommandPost<String> store1 = new AbstractTagCommandPost<>() {
            @Override
            public String tagCode(String str) {
                return str.toString();
            }
        };
        store1.addTaggedItem("#loveyou3000", "tonystark");
        assertTrue(store1.containsRelation("#loveyou3000","tonystark"));
        store1.addTaggedItem("#iamironman", "tonystark");
        assertTrue(store1.containsRelation("#loveyou3000","tonystark"));
        assertTrue(store1.containsRelation("#iamironman","tonystark"));
        store1.addTaggedItem("#avengers", "steverodgers");
        assertTrue(store1.containsRelation("#loveyou3000","tonystark"));
        assertTrue(store1.containsRelation("#iamironman","tonystark"));
        assertTrue(store1.containsRelation("#avengers","steverodgers"));





        AbstractTagCommandPost<TaskInterface> store2 = new AbstractTagCommandPost<>() {
            @Override
            public String tagCode(TaskInterface task) {
                return task.getSaveFormat();
            }
        };

        store2.addTaggedItem("#timeheist", new ToDosImplementation("find time stone", true));
        assertTrue(store2.containsRelation("#timeheist", new ToDosImplementation("find time stone", true)));

        store2.addTaggedItem("#timeheist", new DeadLinesImplementation("find sceptre", "12/05/2012 1200", true));
        assertTrue(store2.containsRelation("#timeheist", new ToDosImplementation("find time stone", true)));
        assertTrue(store2.containsRelation("#timeheist", new DeadLinesImplementation("find sceptre", "12/05/2012 1200", true)));

        store2.addTaggedItem("@stark", new EventsImplementation("find tesseract", "12/05/2012 1201", false));
        assertTrue(store2.containsRelation("#timeheist", new ToDosImplementation("find time stone", true)));
        assertTrue(store2.containsRelation("#timeheist", new DeadLinesImplementation("find sceptre", "12/05/2012 1200", true)));
        assertTrue(store2.containsRelation("@stark", new EventsImplementation("find tesseract", "12/05/2012 1201", false)));
        assertFalse(store2.containsRelation("@stark", new DeadLinesImplementation("find tesseract", "12/05/2012 1201", false)));
        assertFalse(store2.containsRelation("@stark", new EventsImplementation("find tesseract", "12/05/2012 1201", true)));




    }
}
