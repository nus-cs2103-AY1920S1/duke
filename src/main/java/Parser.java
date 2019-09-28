/**
 * Takes in user input and processes it to check whether it is valid.
 * If valid, it will perform the specified command.
 */
public class Parser {

    private String[] validTaskTypes;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of the Parser object. This mainly processes the input of the user.
     *
     * @param taskList The task list of the program.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.validTaskTypes = new String[] {"deadline", "event", "todo"};
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Processes the user input and performs an action based on it, if valid.
     *
     * @param command The user's input.
     */
    public void parse(String command) {
        try {
            String[] commandArray = command.split(" ");
            if (command.equals("bye")) {
                parseByeCommand();
            } else if (command.equals("list")) {
                parseListCommand();
            } else if (command.equals("undo")) {
                parseUndoCommand();
            } else if (commandArray[0].equals("find")) {
                parseFindCommand(command);
            } else if (commandArray[0].equals("done")) {
                parseDoneCommand(command);
            } else if (commandArray[0].equals("delete")) {
                parseDeleteCommand(command);
            } else if (isValidTaskType(commandArray[0])) {
                parseToDoCommand(command);
            } else {
                throw new IceBearException("Ice Bear does not know what that means.");
            }
        } catch (IceBearException e) {
            ui.printException(e);
        }
    }

    /**
     * Helper function to parse the bye command.
     */
    private void parseByeCommand() {
        ui.byeMessage();
    }

    /**
     * Helper function to parse the list command.
     */
    private void parseListCommand() {
        ui.printList(taskList.getListItems());
    }

    /**
     * Helper function to parse the undo command.
     *
     * @throws IceBearException Exception thrown if there is nothing left to undo.
     */
    private void parseUndoCommand() throws IceBearException {
        taskList.undo();
    }

    /**
     * Helper function to parse the find command.
     *
     * @param command The original command input by the user.
     * @throws IceBearException Exception thrown if no keywords were specified by the user.
     */
    private void parseFindCommand(String command) throws IceBearException {
        String[] commandArray = command.split(" ");
        if (commandArray.length == 1) {
            throw new IceBearException("Please provide a keyword "
                    + "for Ice Bear to filter the list of tasks with!");
        }
        taskList.find(command.replaceFirst("find ", ""));
    }

    /**
     * Helper function to parse the done command.
     *
     * @param command The original command input by the user.
     * @throws IceBearException Exception thrown if the id was not specified or is invalid.
     */
    private void parseDoneCommand(String command) throws IceBearException {
        String[] commandArray = command.split(" ");
        if (commandArray.length == 1) {
            throw new IceBearException("Ice Bear needs to know which task ID to set as done!");
        } else if (!commandArray[1].matches("\\d+")) {
            throw new IceBearException("The ID of the task must be an integer!");
        }
        int id = Integer.parseInt(commandArray[1]);
        taskList.markAsDone(id);
    }

    /**
     * Helper function to parse the delete command.
     *
     * @param command The original command input by the user.
     * @throws IceBearException Exception thrown if the id was not specified or is invalid.
     */
    private void parseDeleteCommand(String command) throws IceBearException {
        String[] commandArray = command.split(" ");
        if (commandArray.length == 1) {
            throw new IceBearException("Ice Bear needs to know which task ID to delete!");
        } else if (!commandArray[1].matches("\\d+")) {
            throw new IceBearException("The ID of the task must be an integer!");
        }
        int id = Integer.parseInt(commandArray[1]);
        taskList.delete(id);
    }

    /**
     * Helper function to parse the to do command.
     *
     * @param command The original command input by the user.
     * @throws IceBearException Exception thrown if there was no description provided.
     */
    private void parseToDoCommand(String command) throws IceBearException {
        if (command.equals("todo")) {
            throw new IceBearException("The description of a todo cannot be empty.");
        }
        taskList.addTask(command);
    }


    /**
     * Check against the list of valid Task types to see if the command is valid.
     *
     * @param taskType the first argument of the command input.
     * @return True if the first argument of the command input is valid.
     */
    public boolean isValidTaskType(String taskType) {
        for (String s : validTaskTypes) {
            if (taskType.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
