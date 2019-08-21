import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        String indent = "    ";
        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Duke.\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);

        Scanner sc = new Scanner(System.in);
        boolean chat = true;
        while(chat) {
            String command = sc.next();
            switch (command) {
                case "bye":
                    System.out.println(indent + line);
                    System.out.println(indent + "Bye! Hope to see you again soon.");
                    System.out.println(indent + line);
                    chat = false;
                    break;
                default:
                    System.out.println(indent + line);
                    System.out.println(indent + command);
                    System.out.println(indent + line);
            }
        }

    }
}
