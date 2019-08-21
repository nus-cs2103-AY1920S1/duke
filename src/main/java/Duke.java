import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        String input;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            input = br.readLine();
            while (!input.equals("bye")) {
                echo(input);
                input = br.readLine();
            }
        }

        if (input.equals("bye")) {
            exit();
        }
    }

    private static void exit() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

}
