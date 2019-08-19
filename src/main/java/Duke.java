//level 2
import java.util.Scanner;

import java.util.Scanner;

public class Duke {
    static String[] memory = new String[100];
    static int index = 1;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        activeChat();
    }

    /*
    This method queries for user input and returns the desired result.
     */
    public static void activeChat() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else {
                memory[index - 1] = input;
                System.out.println("added: " + input);
                index++;
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    public static void printList() {
        for (int i = 0; i < index - 1; i++) {
            int id = i + 1;
            System.out.println(id + ". " + memory[i]);
        }
    }
}
