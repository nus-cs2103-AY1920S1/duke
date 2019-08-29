import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SheetTest {
    @Test
    public void addTest() {
        try {
            List<Task> lst1 = new ArrayList<>();
            List<Task> lst2 = new ArrayList<>();
            Task t1 = new Todo("task1");
            Task t2 = new Todo("task2");
            lst1.add(t1);
            lst2.add(t1);
            lst2.add(t2);

            Sheet sh1 = new Sheet(lst1);
            Sheet sh2 = new Sheet(lst2);
            sh1.add(t2);
            assertEquals(sh2.toString(), sh1.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
