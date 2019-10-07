import java.io.IOException;

public class Duke {

    private static UI ui;
    private GuiUI gui;
    private Storage storage;
    private Tasklist tasklist;
    private GuiParser parse;

    /**
     * Constructor for Duke.
     * Creates new Storage instance and Parser
     */

    public Duke() {
        this.gui = new GuiUI();
        this.storage = new Storage();
        this.parse = new GuiParser(storage.getTasks(), gui, storage);

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String out = "";
        try {
            out = parse.guiParserRead(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String startList() {
        return storage.getTasks().printlist();
    }
}
