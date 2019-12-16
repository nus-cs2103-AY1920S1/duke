import tagModule.TagStore;
import tagModule.GenericBiMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
public class GenericBiMapTest {


    @Test
    public void deleteLeft_test() {
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#alive", new TagAbstractStub("loki"));
        store1.insertRelation("#alive", new TagAbstractStub("thor"));
        store1.insertRelation("#asgard", new TagAbstractStub("loki"));
        store1.insertRelation("#asgard", new TagAbstractStub("thor"));
        store1.insertRelation("#tessaract", new TagAbstractStub("loki"));
        store1.insertRelation("#tessaract", new TagAbstractStub("thor"));
        boolean del1 = store1.deleteLeft("#asgard");
        assertTrue(del1);
        assertTrue(store1.containsRelation("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#tessaract", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#alive", new TagAbstractStub("loki")));
        assertTrue(store1.containsRelation("#tessaract", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#asgard", new TagAbstractStub("thor")));
    }

    @Test
    public void deleteRight_test() {
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#alive", new TagAbstractStub("loki"));
        store1.insertRelation("#alive", new TagAbstractStub("thor"));
        store1.insertRelation("#asgard", new TagAbstractStub("loki"));
        store1.insertRelation("#asgard", new TagAbstractStub("thor"));
        store1.insertRelation("#tessaract", new TagAbstractStub("loki"));
        store1.insertRelation("#tessaract", new TagAbstractStub("thor"));
        boolean del1 = store1.deleteRight(new TagAbstractStub("loki"));
        assertTrue(del1);
        assertTrue(store1.containsRelation("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#asgard", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#tessaract", new TagAbstractStub("thor")));
        assertFalse(store1.containsRelation("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#tessaract", new TagAbstractStub("loki")));
    }



    @Test
    public void updateLeft_test() {
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#alive", new TagAbstractStub("loki"));
        store1.insertRelation("#alive", new TagAbstractStub("peterparker"));
        store1.insertRelation("#asgard", new TagAbstractStub("loki"));
        store1.insertRelation("#neighborhood", new TagAbstractStub("peterparker"));
        store1.insertRelation("#tessaract", new TagAbstractStub("loki"));
        store1.insertRelation("#edith", new TagAbstractStub("peterparker"));

        boolean rep1 = store1.updateLeft("#alive", "#maybedead");
        assertTrue(rep1);
        assertFalse(store1.containsRelation("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#alive", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsRelation("#maybedead", new TagAbstractStub("loki")));
        assertTrue(store1.containsRelation("#maybedead", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsRelation("#asgard", new TagAbstractStub("loki")));
        assertTrue(store1.containsRelation("#neighborhood", new TagAbstractStub("peterparker")));
        assertTrue(store1.containsRelation("#tessaract", new TagAbstractStub("loki")));
        assertTrue(store1.containsRelation("#edith", new TagAbstractStub("peterparker")));

    }

    @Test
    public void updateRight_test() {
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#alive", new TagAbstractStub("loki"));
        store1.insertRelation("#asgard", new TagAbstractStub("loki"));
        store1.insertRelation("#tessaract", new TagAbstractStub("loki"));
        boolean rep1 = store1.updateRight(new TagAbstractStub("loki"), new TagAbstractStub("thor"));
        assertTrue(rep1);
        assertTrue(store1.containsRelation("#alive", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#asgard", new TagAbstractStub("thor")));
        assertTrue(store1.containsRelation("#tessaract", new TagAbstractStub("thor")));
        assertFalse(store1.containsRelation("#alive", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#asgard", new TagAbstractStub("loki")));
        assertFalse(store1.containsRelation("#tessaract", new TagAbstractStub("loki")));
    }


    @Test
    public void deleteTagFromObj_test() {
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#spiderman", new TagAbstractStub("peterparker"));
        assertTrue(store1.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        store1.deleteRelation("#spiderman", new TagAbstractStub("peterparker"));
        assertFalse(store1.containsRelation("#spiderman", new TagAbstractStub("peterparker")));


        GenericBiMap<String, TagAbstractStub> store2 = new GenericBiMap<>();

        store2.insertRelation("#sony", new TagAbstractStub("peterparker"));

        store2.insertRelation("#mcu", new TagAbstractStub("peterparker"));
        store2.insertRelation("#spiderman", new TagAbstractStub("peterparker"));

        store2.insertRelation("#snapped", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));


        // NOTE: test if tag doesnt exist and so cannot be deleted
        boolean del2z = store2.deleteRelation("mcu",new TagAbstractStub("peterparker"));
        assertFalse(del2z);

        boolean del2a = store2.deleteRelation("#mcu",new TagAbstractStub("peterparker"));
        assertTrue(del2a);
        assertFalse(store2.containsRelation("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));


        boolean del2b = store2.deleteRelation("#sony",new TagAbstractStub("peterparker"));
        assertTrue(del2b);
        assertFalse(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));

        boolean del2c = store2.deleteRelation("#spiderman",new TagAbstractStub("peterparker"));
        assertTrue(del2c);
        assertFalse(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));

        boolean del2d = store2.deleteRelation("#snapped",new TagAbstractStub("peterparker"));
        assertTrue(del2d);
        assertFalse(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));


        boolean del2e = store2.deleteRelation("#snapped",new TagAbstractStub("peterparker"));
        // unable to delete cos peterparker no longer tagged #snapped
        assertFalse(del2e);
        assertFalse(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));
    }

    @Test
    public void insertRelation_test() {

        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#avengers", new TagAbstractStub("peterparker"));
        store1.insertRelation("#snapped", new TagAbstractStub("peterparker"));
        store1.insertRelation("#avengers", new TagAbstractStub("tonystark"));
        store1.insertRelation("#starkindustries", new TagAbstractStub("tonystark"));
        assertTrue(store1.containsRelation("#avengers", new TagAbstractStub("peterparker")));
        assertFalse(store1.containsRelation("#snapped", new TagAbstractStub("tonystark")));
        assertTrue(store1.containsRelation("#starkindustries", new TagAbstractStub("tonystark")));




        GenericBiMap<String, TagAbstractStub> store2 = new GenericBiMap<>();

        store2.insertRelation("#sony", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));

        store2.insertRelation("#mcu", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#mcu", new TagAbstractStub("peterparker")));

        store2.insertRelation("#spiderman", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));

        store2.insertRelation("#snapped", new TagAbstractStub("peterparker"));
        assertTrue(store2.containsRelation("#sony", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#mcu", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#spiderman", new TagAbstractStub("peterparker")));
        assertTrue(store2.containsRelation("#snapped", new TagAbstractStub("peterparker")));
    }

    @Test
    public void containsRelation_test() {
        TagAbstractStub tas1a = new TagAbstractStub("tonystark");
        TagAbstractStub tas1b = new TagAbstractStub("tonystark");
        GenericBiMap<String, TagAbstractStub> store1 = new GenericBiMap<>();
        store1.insertRelation("#ironman", tas1a);
        assertTrue(store1.containsRelation("#ironman", tas1b));

        
        TagAbstractStub tas2a = new TagAbstractStub("romanov");
        TagAbstractStub tas2b = new TagAbstractStub("tonystark");
        GenericBiMap<String, TagAbstractStub> store2 = new GenericBiMap<>();
        store2.insertRelation("#ironman", tas2b);
        assertFalse(store2.containsRelation("#ironman", tas2a));

        GenericBiMap<String, TagAbstractStub> store3 = new GenericBiMap<>();
        store3.insertRelation("#avengers", new TagAbstractStub("steverodgers"));
        store3.insertRelation("#avengers", new TagAbstractStub("tonystark"));
        assertTrue(store3.containsRelation("#avengers", new TagAbstractStub("steverodgers")));
        assertFalse(store3.containsRelation("#avengers", new TagAbstractStub("mysterio")));
        assertTrue(store3.containsRelation("#avengers", new TagAbstractStub("tonystark")));

    }


}
