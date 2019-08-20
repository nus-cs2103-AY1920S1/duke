package seedu.duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static final String PROMPT = "> ";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr)) {
            mainLoop:
            while (true) {
                System.out.print(PROMPT);
                System.out.flush();
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                String[] tok = line.split(" ", 2);
                if (tok.length < 1) {
                    break;
                }

                switch (line.toLowerCase()) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break mainLoop;
                default:
                    System.out.println(line);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IO error.");
            e.printStackTrace();
        }
    }
}
