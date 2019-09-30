package duke;

public class CmdDuke extends Duke {
    /**
     * Constructs a new copy of the Duke application for the command line.
     *
     * @param filePath File path of the save file to store tasks.
     */
    private CmdDuke(String filePath) {
        super(filePath, new CmdUi());
    }

    /**
     * Runs this Duke application.
     */
    public void run() {
        CmdUi cmdUi = (CmdUi) ui;
        boolean isExit = false;
        while (!isExit && cmdUi.hasNextLine()) {
            isExit = runInput(cmdUi.readCommand());
        }
    }

    public static void main(String[] args) {
        new CmdDuke(Duke.DEFAULT_FILE_PATH).run();
    }
}
