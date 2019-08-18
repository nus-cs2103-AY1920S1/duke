import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean bye = false;
        LinkedList<String> lst = new LinkedList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "\n" + "     Hello! I'm Duke\n     What can I do for you?\n" + line);

        while (!bye) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    bye = true;
                    System.out.println(line + "\n" + "     Bye. Hope to see you again soon!\n" + line);
                    break;
                case "list":
                    System.out.println(line);
                    int size = lst.size();
                    for (int i = 1; i <= size; i++) {
                        System.out.printf("     %d. %s\n", i, lst.peek());
                        String temp = lst.removeFirst();
                        lst.addLast(temp);
                    }
                    System.out.println(line);
                    break;
                default:
                    lst.addLast(command);
                    System.out.println(line + "\n     added: " + command + "\n" + line);
                    break;
            }
        }
    }
}
