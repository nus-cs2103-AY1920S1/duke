import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String inputMessage = sc.nextLine();
            System.out.println(lines);
            if (!inputMessage.equals("bye")) {
                System.out.println("     " + inputMessage);
                System.out.println(lines);
                System.out.println();
                continue;
            }
            System.out.println("     Bye. Hope to see you again soon!");
            System.out.println(lines);
            System.exit(0);
        }
    }
}
