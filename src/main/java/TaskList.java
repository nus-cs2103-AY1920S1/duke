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

    private void modifyList(String[] commandDescription) throws InvalidCommandError {
        try {
            if(commandDescription.length <= 1) {
                throw new IncompleteCommandError("empty");
            }
            switch(commandDescription[0]) {
                case "done":
                    this.markTaskDone(commandDescription);
                    break;
                case "todo":
                    this.addTodo(commandDescription);
                    printSuccessfulAddMessage();
                    break;
                default:
                    this.addTaskWithDate(commandDescription);
                    printSuccessfulAddMessage();
            }
        } catch(IncompleteCommandError e) {
            System.out.println("☹ OOPS!!! The description of a " + commandDescription[0] + " cannot be "
                    + e.getMessage() + ".");
        }
    }

    /**
     * Adds new task into list
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

    private void addTodo(String[] commandDescription) {
        this.list.add(new ToDoTask(commandDescription[1]));
    }

    /**
     * Marks specified command as done based on idx of command
     * @param commandDescription - array of strings containing command description
     */
    private void markTaskDone(String[] commandDescription) throws InvalidCommandError {
        try {
            int idx = Integer.parseInt(commandDescription[1]);
            this.list.get(idx-1).markDone();
        } catch(NumberFormatException e) {
            throw new InvalidCommandError(commandDescription[0]);
        } catch(IndexOutOfBoundsException e) {
            if(this.list.size() == 1) {
                System.out.println("There is only " + this.list.size() + " item in the list :( ");
            } else {
                System.out.println("There are only " + this.list.size() + " items in the list :( ");
            }
        }
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

    private void printSuccessfulAddMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.list.get(list.size()-1));
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }
}
