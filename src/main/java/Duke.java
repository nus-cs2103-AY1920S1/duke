import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                int count = 1;
                for (String s: list) {
                    System.out.println(count + ". " + s);
                    count++;
                }
            } else {
                System.out.println("added: " + input);
                list.add(input);
            }
        }
    }
}
