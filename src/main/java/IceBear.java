/**
 * Creates a Duke window that displays the conversation between the user and Duke.
 */
public class IceBear {
    private Ui ui;
    private Parser parser;

    /**
     * The Constructor to make a Duke object.
     * @param filepath The path to the save file duke.txt
     */
    public IceBear(String filepath) {
        try {
            this.ui = new Ui();
            this.parser = new Parser(new TaskList(new Storage(filepath), ui), ui);
        } catch (IceBearException e) {
            e.printStackTrace();
        }
    }

    protected Ui getUi() {
        return ui;
    }

    /**
     * Perform the command and gives a response.
     */
    protected String getResponse(String input) {
        parser.parse(input);
        return ui.getResponse();
    }
}
