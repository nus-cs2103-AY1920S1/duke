import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void printList(ArrayList<String> list) {
        int i = 1;
        for (String s : list) {
            System.out.println(i + ". " + s);
            i += 1;
        }
    }
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        
        input = scanner.nextLine();

        while (! input.equals("bye")) {
            if (input.equals("list")) {
                printList(list);
                input = scanner.nextLine();
            } else {
                System.out.println("added: " + input);
                list.add(input);
                input = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");



    }
}
