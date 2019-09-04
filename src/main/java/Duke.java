
public class Duke {
    private static Ui uiManager = new Ui();

    /**
     * manages user inputs.
     *
     *
     */
    public void run() {
        uiManager.takeUserCommand();
    }

    /**
     * runs Duke.
     *
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
