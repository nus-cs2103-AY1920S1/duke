import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printList(ArrayList<String> list) {
        int idx = 1;
        for (String s : list) {
            System.out.println(idx + ". " + s);
            idx++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<String> commands = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String command;
        while(!(command = sc.nextLine()).equals("bye")) {
            if(command.equals("list")) {
                printList(commands);
            } else {
                commands.add(command);
                System.out.println("added: " + command);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
