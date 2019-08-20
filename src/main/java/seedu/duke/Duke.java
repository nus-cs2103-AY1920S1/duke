package seedu.duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        ArrayList<Task> taskList = new ArrayList<>();

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
                case "list":
                    for (int i = 0; i < taskList.size(); ++i) {
                        System.out.printf("%d. %s%n", i + 1, taskList.get(i).getDescription());
                    }
                    continue mainLoop;
                default:
                    taskList.add(new Task(line));
                    System.out.printf("added: %s%n", line);
                    continue mainLoop;
                }
            }
        } catch (IOException e) {
            System.err.println("IO error.");
            e.printStackTrace();
        }
    }
}
