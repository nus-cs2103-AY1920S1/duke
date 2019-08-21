import java.util.*;
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
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int pos = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                int k = 0;
                while (k != pos) {
                    int bullet = k + 1;
                    System.out.println(bullet + ". " + list[k]);
                    k++;
                }
            } else {
                list[pos] = input;
                System.out.println("added: " + input);
                pos++;
            }

        }

    }

}
