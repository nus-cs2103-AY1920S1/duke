import java.util.*;

public class Duke {
    public static void main(String[] args) {
        boolean active = true;
        
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "Hello! I'm Duke What can I do for you?";
        System.out.println(intro);

        try(Scanner scanner = new Scanner(System.in)) {
            
            while (active && scanner.hasNext()) {
                String input = scanner.next();

                switch (input) {
                    case "bye":
                    active = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                    default:
                    System.out.println(input);
                }
            }
        };
        
    }
}
