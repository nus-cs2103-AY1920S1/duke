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
        addList();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds commands to lists and prints them out when requested
     */
    static void addList() {
        List<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            if(command.equals("list")) {
                for(int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
            } else {
                list.add(command);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
    }
}
