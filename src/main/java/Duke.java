import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while(!(command = sc.nextLine()).equals("bye")) {
            System.out.println(command);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
