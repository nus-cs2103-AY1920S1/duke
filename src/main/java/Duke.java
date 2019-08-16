import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String s;
        whileloop: while (true) {
            s = sc.nextLine();
            switch (s) {
                case "bye"://exit
                    break whileloop;
                case "list"://list
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i+1) + ". " + list.get(i));
                    }
                    break;
                default://Add item
                    list.add(s);
                    System.out.println("added: " + s);
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
