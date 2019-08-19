import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;

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
        default:
        }
        return false;
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
     */
    void handleDoneCommand(String command) {
        String[] doneArr = command.split(" ");
        int indexToEdit = Integer.parseInt(doneArr[1]);
        Task task = this.list.get(indexToEdit - 1);
        task.markDone();
        System.out.printf("    Nice! I've marked this task as done:\n");
        this.printStream.printf("      %s\n", task); 
    }

    /**
     * Handle Deadline command.
     * @param command String
     */
    void handleDeadlineCommand(String command) {
        String[] commandArr = command.replace("deadline ", "").split("/by ");
        Task taskToAdd = new Deadline(commandArr[0], commandArr[1]);
        this.list.add(taskToAdd);
        this.sendAddTaskAck();        
    }

    /**
     * Handle Event command.
     * @param command String
     */
    void handleEventCommand(String command) {
        String[] commandArr = command.replace("event ", "").split("/at ");
        Task taskToAdd = new Event(commandArr[0], commandArr[1]);
        this.list.add(taskToAdd);
        this.sendAddTaskAck();        
    }

    /**
     * Handle ToDo command.
     * @param command String
     */
    void handleTodoCommand(String command) {
        String name = command.replace("todo ", "");
        this.list.add(new ToDo(name));
        this.sendAddTaskAck();
    }
}
