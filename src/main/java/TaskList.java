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
    public void runCommand(String command) {
        command = command.trim();
        if (command.equals("list")) {
            this.printList();
        } else {
            String[] strArray = command.split("\\s+", 2);
            try {
                this.modifyList(strArray);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("There is/are only " + this.list.size() + " item(s) in the list :( ");
            }
        }
    }

    /**
     * Performs any modification on list - add/delete/markDone
     * @param commandDescription - Command input given by user
     */
    private void modifyList(String[] commandDescription) throws UnknownCommandException, IndexOutOfBoundsException {
        if(!this.isValidCommand(commandDescription[0])) { throw new UnknownCommandException("Unknown Command");}
        if (commandDescription.length <= 1) {
            throw new IncompleteCommandError("empty", commandDescription[0]);
        }
        try {
            switch (commandDescription[0]) {
                case "delete":
                    this.deleteTask(commandDescription);
                    break;
                case "done":
                    this.markTaskDone(commandDescription);
                    break;
                case "todo":
                    this.addTaskWithoutDate(commandDescription);
                    printSuccessfulAddMessage();
                    break;
                case "deadline":
                case "event":
                    this.addTaskWithDate(commandDescription);
                    printSuccessfulAddMessage();
                    break;
                default:
                    throw new UnknownCommandException(commandDescription[0]);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandError(commandDescription[0]);
        }
    }

    /**
     * Adds new task (without date) into list
     * @param commandDescription - array of strings containing command description
     */
    private void addTaskWithoutDate(String[] commandDescription) {
        this.list.add(new ToDoTask(commandDescription[1]));
    }

    /**
     * Adds new task (with date) into list
     * @param commandDescription - array of strings containing command description
     */
    private void addTaskWithDate(String[] commandDescription) throws IncompleteCommandError {
        try {
            String[] taskArray = commandDescription[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String date = taskArray[1].split("\\s+", 2)[1];
            if (commandDescription[0].equals("deadline")) {
                this.list.add(new DeadlineTask(taskName, date));
            } else {
                this.list.add(new EventTask(taskName, date));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandError("incomplete", commandDescription[0]);
        }
    }

    /**
     * Deletes existing task from list
     * @param commandDescription - array of strings containing command description
     */
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
    private void markTaskDone(String[] commandDescription) throws NumberFormatException {
        int idx = Integer.parseInt(commandDescription[1]);
        this.list.get(idx-1).markDone();
    }

    /**
     * Prints out contents of list according to order of insertion
     */
    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + "." + this.list.get(i));
        }
    }

    /**
     * Returns True if input is a valid command, else returns false
     * @param command - command given by user
     */
    private boolean isValidCommand(String command) {
        return Arrays.asList(Command.values()).toString().contains(command);
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
