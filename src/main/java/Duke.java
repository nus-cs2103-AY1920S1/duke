/**
 * My <b>class</b>.
 *
 */
import java.util.*;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        runEvents();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds commands to lists and runs required commands
     */
    static void runEvents() {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            taskList.runCommand(command);
            command = sc.nextLine();
        }
    }
}
