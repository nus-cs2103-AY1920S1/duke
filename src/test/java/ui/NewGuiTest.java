package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewGuiTest {

    /**
     * Tests whether the split string method correctly retrieves the first word of the command input.
     */
    public void chooseTable_splitFirstWord_success() {
        NewGui newGui = new NewGui();
        newGui.chooseTableView("findnotes this is just a test");
        String strtest = newGui.getStr();
        assertEquals("findnotes",strtest);
    }
}
