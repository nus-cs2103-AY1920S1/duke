import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    static List<String> lst = new ArrayList<>();
    static String tab = "\t____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        print("Hello! I'm Duke\n\tWhat can I do for you?");
        boolean quit = false;
        while (!quit) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    quit = true;
                    break;
                case "list":
                    System.out.println(tab);
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println("\t "  + (i + 1) + ". " + lst.get(i));
                    }
                    System.out.println(tab + "\n");
                    break;
                default:
                    print(" added: " + input);
                    lst.add(input);
                    break;
            }
        }
    }

    static void print(String txt) {
        System.out.println(tab);
        System.out.println("\t" + txt);
        System.out.println(tab + "\n");
    }
}
