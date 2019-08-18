import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greet() {
        String input;
        ArrayList<String> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("_____________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________________");
                return;
            } else if (input.equals("list")) {
                System.out.println("_____________________________________________________");
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i+1 + ". " + arr.get(i));
                }
                System.out.println("_____________________________________________________");
            } else {
                System.out.println("_____________________________________________________");
                arr.add(input);
                System.out.println("added: " + input);
                System.out.println("_____________________________________________________");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("_____________________________________________________");
        Duke.greet();
    }
}

