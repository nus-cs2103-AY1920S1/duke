import java.util.Optional;

public class Parser {
    private TaskList taskList;
    public Optional<Command> executeCommand(TaskList taskList, String fullCommand) {
        this.taskList = taskList;
        String[] commandDescription = fullCommand.trim().split("\\s+", 2);
        commandDescription[0] = commandDescription[0].toLowerCase();
        String taskName = commandDescription[0];
        try {
            switch (taskName) {
                case "list":
                    return this.printList(fullCommand);
                case "delete":
                    return this.deleteTask(commandDescription);
                case "done":
                    return this.markTaskDone(commandDescription);
                case "todo":
                    return this.addTaskWithoutDate(commandDescription);
                case "deadline":
                case "event":
                    return this.addTaskWithDate(commandDescription);
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
     * Adds new task (without date) into list
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> addTaskWithoutDate(String[] commandDescription) throws IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        return Optional.of(new AddCommand(new ToDoTask(commandDescription[1])));
    }

    /**
     * Adds new task (with date) into list
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in correct format
     */
    private Optional<Command> addTaskWithDate(String[] commandDescription) throws UnknownCommandException {
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
     * Deletes existing task from list
     * @param commandDescription - array of strings containing command description
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private Optional<Command> deleteTask(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]);
        idx--;
        if(idx >= this.taskList.size()) { throw new IndexOutOfBoundsException(); }
        return Optional.of(new DeleteCommand(idx));
    }

    /**
     * Marks specified command as done based on idx of command
     * @param commandDescription - array of strings containing command description
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     */
    private Optional<Command> markTaskDone(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]);
        idx--;
        if(idx >= this.taskList.size()) { throw new IndexOutOfBoundsException(); }
        return Optional.of(new DoneCommand(idx));
    }

    /**
     * Prints out contents of list according to order of insertion
     * @throws InvalidCommandError - throws error if the command is in wrong format
     */
    private Optional<Command> printList(String command) throws InvalidCommandError {
        if (!command.toLowerCase().equals("list")) { throw new InvalidCommandError(command); }
        return Optional.of(new ListCommand());
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
