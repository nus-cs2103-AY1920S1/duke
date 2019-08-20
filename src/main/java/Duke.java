import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.error.DukeException;
import duke.error.InvalidCommandException;
import duke.error.InvalidTaskArgumentException;
import duke.error.InvalidIndexException;

public class Duke {
    private List<Task> list;
    private PrintStream printStream;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Hello I'm Duke\nWhat can I do for you\n");
        Duke duke = new Duke();
        while (true) {
            String command = sc.nextLine();
            if (duke.handleCommand(command)) {
                break;   
            }
        }
    }

    /**
     * Constructor.
     */
    public Duke() {
        this.list = new ArrayList<Task>();
        this.printStream = this.initPrintStream();
    }

    /**
     * Initialize printStream.
     * @return printStream PrintStream
     */
    PrintStream initPrintStream() {
        PrintStream printStream; 
        try {
            printStream = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException error) {
            printStream = new PrintStream(System.out, true);
        }
        return printStream;
    }

    /**
     * Handles the various commands.
     * @param command String
     * @return boolean
     */
    boolean handleCommand(String command) { 
        try {
            String keyword = command.split(" ")[0];
            switch (keyword) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return true;
            case "list":
                this.handleListCommand();
                break;
            case "done":
                this.handleDoneCommand(command);
                break;
            case "todo":
                this.handleTodoCommand(command);
                break;
            case "deadline":
                this.handleDeadlineCommand(command);
                break;
            case "event":
                this.handleEventCommand(command);
                break;
            case "delete":
                this.handleDeleteCommand(command);
                break;
            default:
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return false;
        } catch (DukeException e) {
            this.printStream.println(e);
            return false;
        }
    }

    /**
     * Send addTask acknowledgement.
     */
    void sendAddTaskAck() {
        System.out.println("    Got it. I've added this task:");
        this.printStream.printf("      %s\n", this.list.get(this.list.size() - 1));
        System.out.printf("    Now you have %d tasks in the list.\n", this.list.size());
    }

    /**
     * Handle List command.
     */
    void handleListCommand() {
        System.out.printf("    Here are the tasks in your list:\n");
        int length = this.list.size();
        for (int i = 0; i < length; i++) {
            this.printStream.printf("    %d.%s\n", i + 1, this.list.get(i));
        }
    }

    /**
     * Handle Done command.
     * @param command String
     * @throws InvalidIndexException if the task to modify is invalid
     * @throws InvalidCommandException if the Done command is not correct
     */
    void handleDoneCommand(String command) throws InvalidIndexException, InvalidCommandException {
        String[] doneArr = command.split(" ");
        if (doneArr.length != 2) {
            throw new InvalidCommandException("☹ OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(doneArr[1]);
        if (indexToEdit > this.list.size() || indexToEdit < 1) {
            throw new InvalidIndexException("☹ OOPS!!! Trying to modify invalid task");
        }
        Task task = this.list.get(indexToEdit - 1);
        task.markDone();
        System.out.printf("    Nice! I've marked this task as done:\n");
        this.printStream.printf("      %s\n", task); 
    }

    /**
     * Handle Delete command.
     * @param commandString
     * @throws InvalidIndex
     */
    void handleDeleteCommand(String command) throws InvalidIndexException, InvalidCommandException {
        String[] deleteArr = command.split(" ");
        if (deleteArr.length != 2) {
            throw new InvalidCommandException("☹ OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(deleteArr[1]);
        if (indexToEdit > this.list.size() || indexToEdit < 1) {
            throw new InvalidIndexException("☹ OOPS!!! Trying to delete invalid task");
        }
        Task removedTask = this.list.remove(indexToEdit - 1);
        System.out.println("    Noted. I've removed the task:");
        this.printStream.printf("      %s\n", removedTask); 
        System.out.printf("    Now you have %d tasks in the list.\n", this.list.size()); 
    }

    /**
     * Handle Deadline command.
     * @param command String
     * @throws InvalidTaskArgumentException if Deadline arguments are invalid
     */
    void handleDeadlineCommand(String command) throws InvalidTaskArgumentException {
        String[] commandArr = command.replace("deadline", "").trim().split("/by ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("☹ OOPS!!! Deadline must have a description and date");
        }
        Task taskToAdd = new Deadline(commandArr[0], commandArr[1]);
        this.list.add(taskToAdd);
        this.sendAddTaskAck();        
    }

    /**
     * Handle Event command.
     * @param command String
     * @throws InvalidTaskArgumentException if Event arguments are invalid
     */
    void handleEventCommand(String command) throws InvalidTaskArgumentException {
        String[] commandArr = command.replace("event", "").trim().split("/at ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("☹ OOPS!!! Event must have a description and date");
        }
        Task taskToAdd = new Event(commandArr[0], commandArr[1]);
        this.list.add(taskToAdd);
        this.sendAddTaskAck();        
    }

    /**
     * Handle ToDo command.
     * @param command String
     * @throws InvalidTaskArgumentException if ToDo arguments are invalid
     */
    void handleTodoCommand(String command) throws InvalidTaskArgumentException {
        String name = command.replace("todo", "").trim();
        if ("".equals(name)) {
            throw new InvalidTaskArgumentException("☹ OOPS!!! The description of a todo cannot be empty."); 
        }
        this.list.add(new ToDo(name));
        this.sendAddTaskAck();
    }
}
