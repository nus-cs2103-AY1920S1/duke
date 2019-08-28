public class DukeGui extends Duke {
    public DukeGui(String filePath) {
        super();
        ui = new UiStringOutput();
        loadStoreFromFile(filePath);
    }


    public String getResponse(String input) {
        //ui.displayWelcome();

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException exc) {
            ui.displayError(exc);
        }

        return ((UiStringOutput) ui).flushBuffer();
    }
}
