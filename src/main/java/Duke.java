public class Duke {
    private static DukeLogic dukeLogic;

    /**
     * Initialises and runs the application.
     */
    private static void start() {
        dukeLogic = new DukeLogic();
        dukeLogic.runApplication();
    }

    /**
     * Starts the Duke application.
     * @param args  Standard arguments
     */
    public static void main(String[] args) {
        Duke.start();
    }
}
