import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scanner  = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
