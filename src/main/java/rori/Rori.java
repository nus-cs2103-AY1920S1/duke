import javafx.application.Platform;

/**
 * This program is Rori, which stores task for users.
 * 
 * @author Timothy Yu Zhiwen
 */
public class Rori {
    private Ui uiManager;
    private Storage storeManager;
    private Parser parseManager;
    private TaskList taskList;
    
    private boolean firstTime;
    
    /**
     * Constructor for RoriManager, which instantiates several other classes as well.
     */
    public Rori() throws RoriException {
        this.uiManager = new Ui();
        this.storeManager = new Storage("Tasks.sav");
        this.parseManager = new Parser();
        this.taskList = this.storeManager.retrieve(this);
    }

    /**
     * Returns a String that is going to be output to the user, and the given input results
     * in an ExitCommand, it will close the Rori program.
     * 
     * <p>runRori is for 1 iteration/command and is used only in javaFx.
     * For the console version, please check initializeRori()
     * 
     * @param input The user's input.
     * @return a String going to be output to the user.
     * @throws RoriException When there is an error in one fo the commands.
     */
    public String runRori(String input) throws RoriException {
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

    /**
     * Returns the output which is obtained from running the user's input through Rori.
     * @param input The users input.
     * @return The String which is obtained when calling RoriException.
     */
    public String getResponse(String input) throws RoriException {
        assert !input.equals("") : "Empty input";
        String output = runRori(input);
        return output;
    }

    /**
     * Returns a String containing whether the user want's the tutorial.
     * 
     * @param input The user's response
     * @return a String containing whether the user want's the tutorial.
     */
    public String getTutorialResponse(String input) {
        try {
            return parseManager.parseTutorialResponse(input, this, this.uiManager);    
        } catch (RoriException e) {
            return e.getMessage();   
        }
    }
    
}
