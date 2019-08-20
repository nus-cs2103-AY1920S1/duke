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
                if (tok.length < 1 || tok[0].isBlank()) {
                    System.out.println("No command entered.");
                    continue;
                }

                switch (line.toLowerCase()) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break mainLoop;
                case "list":
                    for (int i = 0; i < taskList.size(); ++i) {
                        System.out.printf("%d. %s%n", i + 1, taskList.get(i));
                    }
                    continue mainLoop;
                }

                switch (tok[0].toLowerCase()) {
                case "done":
                    if (tok.length < 2) {
                        System.out.println("Usage: done <task ID>");
                        break;
                    }
                    try {
                        int index = Integer.parseInt(tok[1]);
                        if (index < 1 || taskList.size() < index) {
                            System.out.println("Invalid task ID.");
                            break;
                        }

                        Task t = taskList.get(index - 1);
                        t.setDone(true);
                        System.out.printf("Nice! I've marked this task as done:%n%s%n", t);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid number provided as task ID.");
                    }
                    break;
                default:
                    taskList.add(new Task(line));
                    System.out.printf("added: %s%n", line);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IO error.");
            e.printStackTrace();
        }
    }
}
