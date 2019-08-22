import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = sc.next();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.next();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
        }
    }
}
