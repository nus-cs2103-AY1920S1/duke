public class Duke {
    private static final String saveLoadFilePath = "listSaveData.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private MainWindow mw;

    /**
     * parses and responds to the given input directly
     * @implNote did not use the return String implementation as it
     * limits duke's UX flexibility to 1 dialogBox per user input
     * @param input
     */
    void getResponse(String input) {
        if (!parser.parse(input, tasks, ui, storage, mw)) {
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
        this.mw = mw;
//        mw.dukeSays("initializing");
//        mw.dukeSays("loading storage");
        try {
            tasks = storage.load();
        } catch (Exception e) {
            mw.dukeSays(e.getMessage());
            tasks = new TaskList();
        }
        mw.dukeSays(ui.printLogo());
        mw.dukeSays(ui.printHello());
        mw.dukeSays(ui.listCommands());
    }

    public Duke() {
        this(saveLoadFilePath);
    }

    public Duke(String filepath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
    }
}