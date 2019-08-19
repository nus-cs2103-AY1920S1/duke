import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String command = "";

        while (true) {
            command = sc.nextLine();
            if (!command.equals("bye")) {
                System.out.println(command);
                continue;
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
