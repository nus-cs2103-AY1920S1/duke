import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc;
        sc = new Scanner(System.in);
        String response = sc.nextLine();
        while (!response.equals("bye")) {
            System.out.println(response);
            response = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
