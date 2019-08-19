import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

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
        default:
            Task taskToAdd = new Task(command);
            this.list.add(taskToAdd);
            this.printStream.printf("    added: %s\n", taskToAdd);
        }
        return false;
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
}
