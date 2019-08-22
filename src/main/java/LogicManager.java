public class LogicManager {
    private TaskList taskList;
    public void executeCommand(TaskList taskList, String command) {
        this.taskList = taskList;
        command = command.trim();
        String[] commandDescription = command.split("\\s+", 2);
        try {
            switch (commandDescription[0]) {
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
     * @throws IncompleteCommandError
     */
    private void addTaskWithoutDate(String[] commandDescription) throws IncompleteCommandError {
        this.checkCommandEmpty(commandDescription);
        this.taskList.add(commandDescription[1]);
    }

    /**
     * Adds new task (with date) into list
     * @param commandDescription - array of strings containing command description
     * @throws UnknownCommandException
     */
    private void addTaskWithDate(String[] commandDescription) throws UnknownCommandException {
        this.checkCommandEmpty(commandDescription);
        try {
            String[] taskArray = commandDescription[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String date = taskArray[1].split("\\s+", 2)[1];
            this.taskList.add(commandDescription[0], taskName, date);
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandError("incomplete", commandDescription[0]);
        }
    }

    /**
     * Deletes existing task from list
     * @param commandDescription - array of strings containing command description
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandError
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
        this.taskList.done(idx-1);
    }

    /**
     * Prints out contents of list according to order of insertion
     * @throws InvalidCommandError
     */
    private void printList(String command) throws InvalidCommandError {
        if (!command.equals("list")) { throw new InvalidCommandError(command); }
        this.taskList.print();
    }

    /**
     * Throws error if the given command is empty
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError
     */
    private void checkCommandEmpty(String[] commandDescription) throws IncompleteCommandError {
        if(commandDescription.length == 1) {
            throw new IncompleteCommandError("empty", commandDescription[0]);
        }
    }
}
