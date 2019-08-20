import java.util.*;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Performs required action based on command
     * @param command - Input given by user
     */
    public void runCommand(String command) throws UnknownCommandException {
        if(command.equals("list")) {
            this.printList();
        } else {
            String[] strArray = command.split("\\s+", 2);
            if(!isKnownCommand(strArray[0])) {
                throw new UnknownCommandException("Unknown command");
            }
            try {
                this.modifyList(strArray);
            } catch(InvalidCommandError e) {
                System.out.println("☹ OOPS!!! The statement: \"" + command + "\" is invalid. ");
            }
        }
    }

    /**
     * Performs any modification on list - add/delete/markDone
     * @param commandDescription - Command input given by user
     */
    private void modifyList(String[] commandDescription) throws InvalidCommandError {
        try {
            if(commandDescription.length <= 1) {
                throw new IncompleteCommandError("empty");
            }
            switch(commandDescription[0]) {
                case "delete":
                case "done":
                    this.modifyTask(commandDescription);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    this.addTask(commandDescription);
                    printSuccessfulAddMessage();
                    break;
                default:
                    throw new InvalidCommandError(commandDescription[0]);
            }
        } catch(IncompleteCommandError e) {
            System.out.println("☹ OOPS!!! The description of a " + commandDescription[0] + " cannot be "
                    + e.getMessage() + ".");
        }
    }

    /**
     * Performs any modification on task - delete/markDone
     * @param commandDescription - Input given by user
     */
    private void modifyTask(String[] commandDescription) throws InvalidCommandError {
        if(commandDescription.length != 2) {
            throw new InvalidCommandError(commandDescription[0]);
        }
        try {
            switch(commandDescription[0]) {
                case "delete":
                    this.deleteTask(commandDescription);
                    break;
                case "done":
                    this.markTaskDone(commandDescription);
            }
        } catch(NumberFormatException e) {
            throw new InvalidCommandError(commandDescription[0]);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("There is/are only " + this.list.size() + " item(s) in the list :( ");
        }
    }

    /**
     * Performs any addition of task on list
     * @param commandDescription - Input given by user
     */
    private void addTask(String[] commandDescription) throws IncompleteCommandError {
        switch(commandDescription[0]) {
            case "todo":
                this.list.add(new ToDoTask(commandDescription[1]));
                break;
            default:
                addTaskWithDate(commandDescription);
        }
    }

    /**
     * Adds new task (with date) into list
     * @param commandDescription - array of strings containing command description
     */
    private void addTaskWithDate(String[] commandDescription) throws IncompleteCommandError {
        String[] taskArray = commandDescription[1].split("/", 2);
        // Get String name
        if(taskArray.length <= 1) {throw new IncompleteCommandError("incomplete");}
        String taskName = taskArray[0].trim();
        try {
            String date = taskArray[1].split("\\s+", 2)[1];
            if(commandDescription[0].equals("deadline")) {
                this.list.add(new DeadlineTask(taskName, date));
            } else {
                this.list.add(new EventTask(taskName, date));
            }
        } catch(IndexOutOfBoundsException e) {
            throw new IncompleteCommandError("incomplete");
        }
    }

    private void deleteTask(String[] commandDescription) throws NumberFormatException, IndexOutOfBoundsException {
        int idx = Integer.parseInt(commandDescription[1]);
        Task task = this.list.get(idx-1);
        this.list.remove(idx-1);
        printDeleteTaskMessage(task);
    }

    /**
     * Marks specified command as done based on idx of command
     * @param commandDescription - array of strings containing command description
     */
    private void markTaskDone(String[] commandDescription) throws InvalidCommandError {
        int idx = Integer.parseInt(commandDescription[1]);
        this.list.get(idx-1).markDone();
    }

    /**
     * Returns true if command is a valid command, else returns false
     * @param command - String containing command phrase
     */
    private boolean isKnownCommand(String command) {
        return Arrays.stream(Command.values()).anyMatch((t) -> t.name().equals(command));
    }

    /**
     * Prints out contents of list according to order of insertion
     */
    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + "." + this.list.get(i));
        }
    }

    /**
     * Prints out message after successful addition of task
     */
    private void printSuccessfulAddMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.list.get(list.size()-1));
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    /**
     * Prints out message after successful deletion of task
     * @param task - Task that has been successfully deleted
     */
    private void printDeleteTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }
}
