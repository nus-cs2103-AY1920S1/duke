import java.util.Optional;

public class Parser {
    private TaskList taskList;

    /**
     * Parse given user input and returns Optional containing null or valid command
     * @param taskList - list containing all existing tasks
     * @param fullCommand - command given by user input
     * @return Optional containing either valid command or null (when exception is thrown)
     */
    public Optional<Command> executeCommand(TaskList taskList, String fullCommand) {
        this.taskList = taskList;
        String[] commandDescription = fullCommand.trim().split("\\s+", 2);
        commandDescription[0] = commandDescription[0].toLowerCase();
        String taskName = commandDescription[0];
        try {
            switch (taskName) {
                case "list":
                    return this.parseListCommand(fullCommand);
                case "delete":
                    return this.parseDeleteCommand(commandDescription);
                case "done":
                    return this.parseDoneCommand(commandDescription);
                case "todo":
                    return this.parseAddWithoutDateCommand(commandDescription);
                case "deadline":
                case "event":
                    return this.parseAddWithDateCommand(commandDescription);
                case "find":
                    return this.parseFindCommand(commandDescription);
                default:
                    throw new UnknownCommandException(commandDescription[0]);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            UI.printExceedListMessage(this.taskList.size());
        } catch (NumberFormatException e) {
            UI.printInvalidStatementMessage(fullCommand);
        }
        return Optional.empty();
    }

    /**
     * Parse new task (without date) based on existing format
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> parseAddWithoutDateCommand(String[] commandDescription) throws IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        return Optional.of(new AddCommand(new ToDoTask(commandDescription[1])));
    }

    /**
     * Parse new task (with date) based on existing format
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws IncompleteCommandError - throws error if the command is not in correct format
     */
    private Optional<Command> parseAddWithDateCommand(String[] commandDescription) throws UnknownCommandException {
        this.checkCommandEmpty(commandDescription);
        try {
            String[] taskArray = commandDescription[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String taskType = commandDescription[0].toLowerCase();
            String[] statementAndDate = taskArray[1].split("\\s+", 2);
            DateTimeParser.validateDateFormat(statementAndDate[1]);
            switch(taskType) {
                case "deadline":
                    DeadlineTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                    return Optional.of(new AddCommand(new DeadlineTask(taskName, statementAndDate[1])));
                case "event":
                    EventTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                    return Optional.of(new AddCommand(new EventTask(taskName, statementAndDate[1])));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandError("incomplete", commandDescription[0]);
        }
        return Optional.empty();
    }

    /**
     * Parse delete command based on format
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> parseDeleteCommand(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]) - 1;
        if(idx >= this.taskList.size()) { throw new IndexOutOfBoundsException(); }
        return Optional.of(new DeleteCommand(idx));
    }

    /**
     * Marks specified command based on idx of command and required format
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> parseDoneCommand(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]) - 1;
        if(idx >= this.taskList.size()) { throw new IndexOutOfBoundsException(); }
        return Optional.of(new DoneCommand(idx));
    }

    /**
     * Parse list command based on required format
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws InvalidCommandError - throws error if the command is in wrong format
     */
    private Optional<Command> parseListCommand(String command) throws InvalidCommandError {
        if (!command.toLowerCase().equals("list")) { throw new InvalidCommandError(command); }
        return Optional.of(new ListCommand());
    }

    /**
     * Parse find command based on required format
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> parseFindCommand(String[] commandDescription) throws IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        return Optional.of(new FindCommand(commandDescription[1]));
    }

    /**
     * Throws error if the given command is empty
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private void checkCommandEmpty(String[] commandDescription) throws IncompleteCommandError {
        if(commandDescription.length == 1) {
            throw new IncompleteCommandError("empty", commandDescription[0]);
        }
    }
}
