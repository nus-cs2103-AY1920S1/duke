import java.util.Scanner;

public class dukeBot {
    public void start() {
        Scanner sc =  new Scanner(System.in);

        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye") || command.equals("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else { System.out.println(command); }
        }
    }
}
