package duke.DirectProcessor;

import duke.Commands.*;

/**
 * This is a class that recognizes the user's input and calls the corresponding command.
 * It only recognizes the first word of the user input and key words like "/at", "/by".
 * Both its methods are static, so there is no necessity to initialize one.
 */
public class Parser {

    /**
     * It recognizes the user input and call the corresponding method.
     * @param s The user's input as a string.
     * @return The recognized command.
     */
    public static Command parse(String s) {
        String[] splitInput = splitInput(s);

        // Call list command
        if (splitInput[0].equals("list")) return new ListCommand();

        // Call exit command
        else if (splitInput[0].equals("bye")) return new ExitCommand();

        // Call add command: adding todo
        else if (splitInput[0].equals("todo")) {
            String taskName = "";
            for (int i = 1; i < splitInput.length; i++) {
                taskName = taskName + splitInput[i];
                if (i != splitInput.length - 1) taskName = taskName + " ";
            }
            return new AddCommand("T", taskName);
        }

        // Call add command adding event
        else if (splitInput[0].equals("event")) {
            String taskName = "";
            String taskTime = "";
            int timeStartFrom = 0;
            for (int i = 1; i < splitInput.length; i++) {
                if (splitInput[i].equals("/at")) {
                    timeStartFrom = i + 1;
                    break;
                }
                if (i != 1) taskName = taskName + " ";
                taskName = taskName + splitInput[i];
            }
            if (timeStartFrom != 0) {
                for (int i = timeStartFrom; i < splitInput.length; i++) {
                    taskTime = taskTime + splitInput[i];
                    if (i != splitInput.length - 1) taskTime = taskTime + " ";
                }
            }
            return new AddCommand("E", taskName, taskTime);
        }

        // Call add command adding deadline
        else if (splitInput[0].equals("deadline")) {
            String taskName = "";
            String taskTime = "";
            int timeStartFrom = 0;
            for (int i = 1; i < splitInput.length; i++) {
                if (splitInput[i].equals("/by")) {
                    timeStartFrom = i + 1;
                    break;
                }
                if (i != 1) taskName = taskName + " ";
                taskName = taskName + splitInput[i];
            }
            if (timeStartFrom != 0) {
                for (int i = timeStartFrom; i < splitInput.length; i++) {
                    taskTime = taskTime + splitInput[i];
                    if (i != splitInput.length - 1) taskTime = taskTime + " ";
                }
            }
            return new AddCommand("D", taskName, taskTime);
        }

        // Call finish command
        else if (splitInput[0].equals("done")) return new FinishCommand(Integer.parseInt(splitInput[1]));

        // Call delete command
        else if (splitInput[0].equals("delete")) return new DeleteCommand(Integer.parseInt(splitInput[1]));

        else if (splitInput[0].equals("find")) {
            String target = "";
            for (int i = 1; i < splitInput.length; i++) {
                target = target + splitInput[i];
                if (i != splitInput.length - 1) target = target + " ";
            }
            return new FindCommand(target);
        }

        else return new FakeCommand();
    }

    private static String[] splitInput(String s) {
        return s.split(" ");
    }
}
