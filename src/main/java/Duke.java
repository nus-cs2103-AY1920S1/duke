import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");

        //Echo
        Scanner sc = new Scanner(System.in);
        String input;
        String check = "dummy";
        ArrayList<String> taskStorage = new ArrayList<>();

        while (check.equals("bye") == false) {
            input = sc.nextLine();
            check = input.toLowerCase();
            if (check.equals("list")) {
                for (int i = 1; i <= taskStorage.size(); i++) {
                    System.out.println(i + ". " + taskStorage.get(i - 1));
                }
                System.out.println();
                continue;
            }

            if (check.equals("bye") == false) {
                taskStorage.add(input);
                System.out.println("added: " + input + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
