import javafx.application.Platform;

/**
 * Represents a manager to manage all of the different classes, and act's as the
 * initialization stage of the program.
 */
class DukeManager {
    private Ui uiManager;
    private Storage storeManager;
    private Parser parseManager;
    private TaskList taskList;
    
    private boolean firstTime;
    
    /**
     * Constructor for DukeManager, which instantiates several other classes as well.
     * 
     */
    public DukeManager() throws DukeException {
        this.uiManager = new Ui();
        this.storeManager = new Storage("Tasks.sav");
        this.parseManager = new Parser();
        this.taskList = this.storeManager.retrieve(this);
    }

    /**
     * Returns a String that is going to be output to the user, and the given input results
     * in an ExitCommand, it will close the Duke program.
     * 
     * <p>runDuke is for 1 iteration/command and is used only in javaFx.
     * For the console version, please check initializeDuke()
     * 
     * @param input The user's input.
     * @param duke The instance of duke program.
     * @return a String going to be output to the user.
     * @throws DukeException When there is an error in one fo the commands.
     */
    public String runDuke(String input) throws DukeException {
        Command command = parseManager.parseToCommand(input);
        assert command != null : "Command is invalid";
        command.execute(this.uiManager, this.taskList, this.storeManager);
        String output = uiManager.getString();
        assert output != "" : "Output is invalid";
        if (command instanceof ExitCommand) {
            Platform.exit();
        }
        return output;
    }

    public String welcomeMessage() {
        return uiManager.printWelcome();
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public boolean getFirstTime() {
        return this.firstTime;
    }

    public String getTutorial() {
        return uiManager.printWantTutorial();
    }

    public String getTutorialResponse(String input) {
        try {
            return parseManager.parseTutorialResponse(input, this, this.uiManager);    
        } catch (DukeException e) {
            return e.getMessage();   
        }
    }
}