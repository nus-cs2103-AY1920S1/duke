package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewGuiTest {

    public void chooseTable_splitFirstWord_success (){
        NewGui newGui = new NewGui();
        newGui.chooseTableVIew("findnotes this is just a test");
        String strtest = newGui.getStr();
        assertEquals("findnotes",strtest);
    }
}
