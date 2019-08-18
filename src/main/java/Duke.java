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
        String lines = "    ______________________________________________";
        System.out.println(lines);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();

        String str = sc.nextLine();
        while(!str.equals("bye")) {
            if (str.equals("list")) {
                int i = 1;
                System.out.println(lines);
                for (String s : arr) {
                    System.out.println("    " + i + ". " + s);
                    i++;
                }
                System.out.println(lines);
            } else {
                arr.add(str);
                System.out.println(lines);
                System.out.println("    " + str);
                System.out.println(lines);
            }
            str = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
