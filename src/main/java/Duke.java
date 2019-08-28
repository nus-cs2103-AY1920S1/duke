import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?"); // greet user
        Scanner scanner = new Scanner(System.in); // reads user input
        List<String> toDos = new ArrayList<>(); // instantiate array list of strings
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                for (int i = 1; i < toDos.size() + 1; i++) {
                    System.out.println(i + ". " + toDos.get(i - 1));
                }
            }
            else {
                toDos.add(line); // add user input to array list
                System.out.println("added: " + line); // echos user input
            }
        }
    }
}
