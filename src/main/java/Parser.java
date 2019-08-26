import java.util.Date;

public class Parser {
    private TaskList taskList;
    public void executeCommand(TaskList taskList, String command) {
        this.taskList = taskList;
        String[] commandDescription = command.trim().split("\\s+", 2);
        commandDescription[0] = commandDescription[0].toLowerCase();
        String taskName = commandDescription[0];
        try {
            switch (taskName) {
                case "list":
                    this.printList(command);
                    break;
                case "delete":
                    this.deleteTask(commandDescription);
                    break;
                case "done":
                    this.markTaskDone(commandDescription);
                    break;
                case "todo":
                    this.addTaskWithoutDate(commandDescription);
                    break;
                case "deadline":
                case "event":
                    this.addTaskWithDate(commandDescription);
                    break;
                default:
                    throw new UnknownCommandException(commandDescription[0]);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is/are only " + taskList.size() + " item(s) in the list :( ");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! The statement: \"" + command + "\" is invalid. ");
        }
    }

    /**
     * Adds new task (without date) into list
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private void addTaskWithoutDate(String[] commandDescription) throws IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        this.taskList.add(commandDescription[1]);
        Message.printSuccessfulAddMessage(this.taskList.get(taskList.size()-1), this.taskList.size());
    }

    /**
     * Adds new task (with date) into list
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in correct format
     */
    private void addTaskWithDate(String[] commandDescription) throws UnknownCommandException {
        this.checkCommandEmpty(commandDescription);
        try {
            String[] taskArray = commandDescription[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String taskType = commandDescription[0].toLowerCase();
            String[] statementAndDate = taskArray[1].split("\\s+", 2);
            switch(taskType) {
                case "deadline":
                    DeadlineTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                    break;
                case "event":
                    EventTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                    break;
            }
            DateTimeParser.validateDateFormat(statementAndDate[1]);
            this.taskList.add(commandDescription[0], taskName, statementAndDate[1]);
            Message.printSuccessfulAddMessage(this.taskList.get(taskList.size()-1), this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandError("incomplete", commandDescription[0]);
        }
    }

    /**
     * Deletes existing task from list
     * @param commandDescription - array of strings containing command description
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private void deleteTask(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]);
        this.taskList.delete(idx-1);
    }

    /**
     * Marks specified command as done based on idx of command
     * @param commandDescription - array of strings containing command description
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     */
    private void markTaskDone(String[] commandDescription) throws RuntimeException, IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]);
        Task task = this.taskList.get(idx-1);
        this.taskList.done(idx-1);
        Message.printSuccessfulDoneMessage(task);
    }

    /**
     * Prints out contents of list according to order of insertion
     * @throws InvalidCommandError - throws error if the command is in wrong format
     */
    private void printList(String command) throws InvalidCommandError {
        if (!command.toLowerCase().equals("list")) { throw new InvalidCommandError(command); }
        this.taskList.print();
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
