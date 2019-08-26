package seedu.duke;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.Parser;
import seedu.duke.cli.commands.ByeCommand;
import seedu.duke.tasks.TaskList;

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

        TaskList taskList = Serialisation.deserialise();
        if (taskList == null) {
            System.out.println("No saved list loaded.");
            taskList = new TaskList();
        } else {
            System.out.printf("Loaded tasks from %s.%n", Serialisation.DATA_FILE_PATH);
        }

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
                        System.out.println("Unknown command.");
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

        Serialisation.serialise(taskList);
        System.out.printf("Saved tasks to %s.%n", Serialisation.DATA_FILE_PATH);
    }
}
