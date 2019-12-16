import tagModule.TagStore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
public class TagStoreTest {

    @Test
    public void registerObserver_test() {
        //TagStore store = new TagStore();
        assertTrue(true);

    }



    @Test
    public void deleteTag_test() {
        TagStore store1 = new TagStore();
        store1.insertTag("#alive", new TagAbstractStub("loki"));
        store1.insertTag("#alive", new TagAbstractStub("thor"));
        store1.insertTag("#asgard", new TagAbstractStub("loki"));
        store1.insertTag("#asgard", new TagAbstractStub("thor"));
        store1.insertTag("#tessaract", new TagAbstractStub("loki"));
        store1.insertTag("#tessaract", new TagAbstractStub("thor"));
        boolean del1 = store1.deleteTag("#asgard");
        assertTrue(del1);
        assertTrue(store1.containsTagPair("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#tessaract", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#alive", new TagAbstractStub("loki")));
        assertTrue(store1.containsTagPair("#tessaract", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#asgard", new TagAbstractStub("thor")));
    }

    @Test
    public void deleteTaggedObject_test() {
        TagStore store1 = new TagStore();
        store1.insertTag("#alive", new TagAbstractStub("loki"));
        store1.insertTag("#alive", new TagAbstractStub("thor"));
        store1.insertTag("#asgard", new TagAbstractStub("loki"));
        store1.insertTag("#asgard", new TagAbstractStub("thor"));
        store1.insertTag("#tessaract", new TagAbstractStub("loki"));
        store1.insertTag("#tessaract", new TagAbstractStub("thor"));
        boolean del1 = store1.deleteTaggedObject(new TagAbstractStub("loki"));
        assertTrue(del1);
        assertTrue(store1.containsTagPair("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#asgard", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#tessaract", new TagAbstractStub("thor")));
        assertFalse(store1.containsTagPair("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#tessaract", new TagAbstractStub("loki")));
    }



    @Test
    public void replaceTag_test() {
        TagStore store1 = new TagStore();
        store1.insertTag("#alive", new TagAbstractStub("loki"));
        store1.insertTag("#alive", new TagAbstractStub("peterparker"));
        store1.insertTag("#asgard", new TagAbstractStub("loki"));
        store1.insertTag("#neighborhood", new TagAbstractStub("peterparker"));
        store1.insertTag("#tessaract", new TagAbstractStub("loki"));
        store1.insertTag("#edith", new TagAbstractStub("peterparker"));

        boolean rep1 = store1.replaceTag("#alive", "#maybedead");
        assertTrue(rep1);
        assertFalse(store1.containsTagPair("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#alive", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsTagPair("#maybedead", new TagAbstractStub("loki")));
        assertTrue(store1.containsTagPair("#maybedead", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsTagPair("#asgard", new TagAbstractStub("loki")));
        assertTrue(store1.containsTagPair("#neighborhood", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsTagPair("#tessaract", new TagAbstractStub("loki")));
        assertTrue(store1.containsTagPair("#edith", new TagAbstractStub("peterparker")));

    }

    @Test
    public void replaceTagggedObject_test() {
        TagStore store1 = new TagStore();
        store1.insertTag("#alive", new TagAbstractStub("loki"));
        store1.insertTag("#asgard", new TagAbstractStub("loki"));
        store1.insertTag("#tessaract", new TagAbstractStub("loki"));
        boolean rep1 = store1.replaceTaggedObject(new TagAbstractStub("loki"), new TagAbstractStub("thor"));
        assertTrue(rep1);
        assertTrue(store1.containsTagPair("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#asgard", new TagAbstractStub("thor")));
        assertTrue(store1.containsTagPair("#tessaract", new TagAbstractStub("thor")));
        assertFalse(store1.containsTagPair("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsTagPair("#tessaract", new TagAbstractStub("loki")));
    }


    @Test
    public void deleteTagFromObj_test() {
        TagStore store1 = new TagStore();
        store1.insertTag("#spiderman", new TagAbstractStub("peterparker"));
        assertTrue(store1.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        store1.deleteTagFromObject("#spiderman", new TagAbstractStub("peterparker"));
        assertFalse(store1.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));


        TagStore store2 = new TagStore();

        store2.insertTag("#sony", new TagAbstractStub("peterparker"));

        store2.insertTag("#mcu", new TagAbstractStub("peterparker"));
        store2.insertTag("#spiderman", new TagAbstractStub("peterparker"));

        store2.insertTag("#snapped", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));


        // NOTE: test if tag doesnt exist and so cannot be deleted
        boolean del2z = store2.deleteTagFromObject("mcu",new TagAbstractStub("peterparker"));
        assertFalse(del2z);

        boolean del2a = store2.deleteTagFromObject("#mcu",new TagAbstractStub("peterparker"));
        assertTrue(del2a);
        assertFalse(store2.containsTagPair("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));


        boolean del2b = store2.deleteTagFromObject("#sony",new TagAbstractStub("peterparker"));
        assertTrue(del2b);
        assertFalse(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));

        boolean del2c = store2.deleteTagFromObject("#spiderman",new TagAbstractStub("peterparker"));
        assertTrue(del2c);
        assertFalse(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));

        boolean del2d = store2.deleteTagFromObject("#snapped",new TagAbstractStub("peterparker"));
        assertTrue(del2d);
        assertFalse(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));


        boolean del2e = store2.deleteTagFromObject("#snapped",new TagAbstractStub("peterparker"));
        // unable to delete cos peterparker no longer tagged #snapped
        assertFalse(del2e);
        assertFalse(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));
    }

    @Test
    public void insertTag_test() {

        TagStore store1 = new TagStore();
        store1.insertTag("#avengers", new TagAbstractStub("peterparker"));
        store1.insertTag("#snapped", new TagAbstractStub("peterparker"));
        store1.insertTag("#avengers", new TagAbstractStub("tonystark"));
        store1.insertTag("#starkindustries", new TagAbstractStub("tonystark"));
        assertTrue(store1.containsTagPair("#avengers", new TagAbstractStub("peterparker")));
        assertFalse(store1.containsTagPair("#snapped", new TagAbstractStub("tonystark")));
        assertTrue(store1.containsTagPair("#starkindustries", new TagAbstractStub("tonystark")));




        TagStore store2 = new TagStore();

        store2.insertTag("#sony", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));

        store2.insertTag("#mcu", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#mcu", new TagAbstractStub("peterparker")));

        store2.insertTag("#spiderman", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));

        store2.insertTag("#snapped", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsTagPair("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsTagPair("#snapped", new TagAbstractStub("peterparker")));
    }

    @Test
    public void containsTagPair_test() {
        TagAbstractStub tas1a = new TagAbstractStub("tonystark");
        TagAbstractStub tas1b = new TagAbstractStub("tonystark");
        TagStore store1 = new TagStore();
        store1.insertTag("#ironman", tas1a);
        assertTrue(store1.containsTagPair("#ironman", tas1b));

        
        TagAbstractStub tas2a = new TagAbstractStub("romanov");
        TagAbstractStub tas2b = new TagAbstractStub("tonystark");
        TagStore store2 = new TagStore();
        store2.insertTag("#ironman", tas2b);
        assertFalse(store2.containsTagPair("#ironman", tas2a));

        TagStore store3 = new TagStore();
        store3.insertTag("#avengers", new TagAbstractStub("steverodgers"));
        store3.insertTag("#avengers", new TagAbstractStub("tonystark"));
        assertTrue(store3.containsTagPair("#avengers", new TagAbstractStub("steverodgers")));
        assertFalse(store3.containsTagPair("#avengers", new TagAbstractStub("mysterio")));
        assertTrue(store3.containsTagPair("#avengers", new TagAbstractStub("tonystark")));

    }


}
