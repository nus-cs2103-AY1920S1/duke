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

    private boolean isActive;
    
    /**
     * Constructor for DukeManager, which instantiates several other classes as well.
     * 
     */
    public DukeManager() throws DukeException {
        this.uiManager = new Ui();
        this.storeManager = new Storage("Tasks.sav");
        this.parseManager = new Parser();
        this.isActive = false;
        this.taskList = this.storeManager.retrieve();
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

    /**
     * Initializes the Duke program, and recursively reads input
     * and produces and output, unless encountered with a DukeException.
     * 
     * <p>There are 8 different Commands the user can input:
     * list, done, delete, todo, deadline, event, help, bye.
     * This method will end when the user inputs 'bye' or anything else which causes a DukeException.
     * 
     * <p>This method is only used for the console version of Duke, and is not
     * used in the javaFX version. For console version, look at runDuke instead.
     * 
     * @throws DukeException When parsing user's input and executing commands.
     * @see DukeManager#runDuke(String)
     */
    public void initializeDuke() throws DukeException {
        uiManager.printWelcome();
        this.taskList = this.storeManager.retrieve();
        this.isActive = true;
        while (isActive) {
            uiManager.printWhatToDo();
            String input = uiManager.readLine();
            Command command = parseManager.parseToCommand(input);
            command.execute(this.uiManager, this.taskList, this.storeManager);
            if (command instanceof ExitCommand) {
                isActive = false;
            }
            uiManager.printEmpty();
        }
    }
}