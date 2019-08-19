import java.util.ArrayList;
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
        ArrayList<String> items = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + ". " + items.get(i));
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again!");
                return;
            } else {
                items.add(input);
                System.out.println("added: " + input);
        
            }
        }
    }
}
