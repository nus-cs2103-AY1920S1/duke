package seedu.duke;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.Parser;
import seedu.duke.cli.commands.ByeCommand;

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
            while (true) {
                System.out.print(PROMPT);
                System.out.flush();
                String line = br.readLine();
                if (line == null) {
                    break;
                } else if (line.isBlank()) {
                    System.out.println("No command entered.");
                    continue;
                }
                try {
                    Command c = Parser.parse(line);
                    if (c == null) {
                        taskList.add(new Task(line));
                        System.out.printf("added: %s%n", line);
                    } else if (c instanceof ByeCommand) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else {
                        c.execute(taskList);
                    }
                } catch (CommandException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("IO error.");
            e.printStackTrace();
        }
    }
}
