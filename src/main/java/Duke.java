import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (command.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else {
                String task = command + sc.nextLine();
                tasks.add(task);
                System.out.println("added: " + task);
            }
        }
    }
}
