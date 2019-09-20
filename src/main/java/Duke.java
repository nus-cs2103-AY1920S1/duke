public class Duke {
    private TaskList tasks;
    private Parser parser;
    private MainWindow mw;

    /**
     * parses and responds to the given input directly
     * @implNote did not use the return String implementation as it
     * limits duke's UX flexibility to 1 dialogBox per user input
     * @param input
     */
    void getResponse(String input) {
        assert input != null;
        if (!parser.parse(input, tasks, mw)) {
            mw.closeSequence();
        }
    }

    /**
     * loads stored taskList, and creates a reference to the calling MainWindow controller
     * for the direction of output
     * @implNote called when MainWindow.setDuke() is run
     * @param mw
     */
    void initialize(MainWindow mw) {
        assert mw != null;
        this.mw = mw;
        try {
            tasks = Storage.load();
        } catch (Exception e) {
            mw.dukeSays(e.getMessage());
            tasks = new TaskList();
        }
        mw.dukeSays(Ui.printLogo());
        mw.dukeSays(Ui.printHello());
        mw.dukeSays(Ui.listCommands());
    }

    public Duke() {
        parser = new Parser();
    }
}