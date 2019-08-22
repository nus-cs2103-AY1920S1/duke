import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while(true) {
            try {
                run();
            } catch(IllegalStateException | NoSuchElementException e) {
                break;
            }
        }

    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        switch(next) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            default: System.out.println(next);
        }
    }
}