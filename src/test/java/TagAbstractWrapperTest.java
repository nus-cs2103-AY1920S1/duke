//import tagModule.TagAbstractWrapper;
//import tagModule.TagInterface;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class TagAbstractWrapperTest {


    
    
    @Test
    public void hashCodetest() {
        HashMap<TagAbstractWrapper, String> store1 = new HashMap<>();
        TagAbstractWrapper taw1 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };
        store1.put(taw1, "jackalope");
        assertEquals("jackalope", store1.get(taw1));

        HashMap<TagAbstractWrapper, String> store2 = new HashMap<>();
        TagAbstractWrapper taw2 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "apple";
                }
            };
        TagAbstractWrapper taw3 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "apple";
                }
            };
        store2.put(taw2, "pineapple");
        assertEquals("pineapple", store2.get(taw3));

        HashMap<TagAbstractWrapper, String> store3 = new HashMap<>();
        TagAbstractWrapper taw4 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "apple";
                }
            };
        TagAbstractWrapper taw5 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "appl";
                }
            };
        store3.put(taw4, "pineapple");
        assertNotEquals("pineapple", store3.get(taw5));
    }

    @Test
    public void equalsTest() {
        TagAbstractWrapper taw1 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };
        TagAbstractWrapper taw2 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };

        assertTrue(taw1.equals(taw2));

        TagAbstractWrapper taw3 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };
        assertTrue(taw3.equals(taw3));

        TagAbstractWrapper taw4 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };
        TagAbstractWrapper taw5 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footres";
                }
            };
        assertFalse(taw4.equals(taw5));

        TagAbstractWrapper taw6 = new TagAbstractWrapper() {
                public String tagCode() {
                    return "footrest";
                }
            };
        TagAbstractWrapper taw7 = new TagAbstractWrapper() {
                public String tagCode() {
                    return null;
                }
            };
        assertFalse(taw6.equals(taw7));
    }
}
