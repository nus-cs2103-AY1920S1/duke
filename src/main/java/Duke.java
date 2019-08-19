/**
 * My <b>class</b>.
 *
 */
import java.util.*;
public class Duke {
    /**
     * The long and detailed explanation what the method does.
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
