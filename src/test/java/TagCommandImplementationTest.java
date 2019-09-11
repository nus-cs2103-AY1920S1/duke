import tagModule.TagCommandImplementation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class TagCommandImplementationTest {

    @Test
    public void register_and_notify_test() {
        TagCommandImplementation tagCom = new TagCommandImplementation();
        TagObserverStub tagStub1 = new TagObserverStub(tagCom);
        tagCom.execute("");
        assertEquals(1, tagStub1.getTimesUpdated());

        TagObserverStub tagStub2 = new TagObserverStub(tagCom);
        tagCom.execute("");
        tagCom.execute("pizza");
        assertEquals(2, tagStub2.getTimesUpdated());

        TagObserverStub tagStub3 = new TagObserverStub(tagCom);
        tagCom.execute("");
        tagCom.execute("");
        tagCom.execute("MACDONALDS");
        assertEquals(3, tagStub3.getTimesUpdated());
        //assertTrue(false);

    }
}
