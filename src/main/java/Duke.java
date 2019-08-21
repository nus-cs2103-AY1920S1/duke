import java.util.Scanner;

public class Duke {
    /**
     * Prints DUKE.
     */
    private static void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints a greeting and echoes user input.
     *
     * @param args takes in arguments.
     */
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        print("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[100];
        int index = 0;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                for (int i = 0; i < index; i++) {
                    print((i + 1) + ". " + arr[i]);
                }
            } else {
                arr[index++] = str;
                print("added: " + str);
            }

        }
    }

}
