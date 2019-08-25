package DirectProcessor;

import Commands.*;

public class Parser {

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

        else return new FakeCommand();
    }

    private static String[] splitInput(String s) {
        return s.split(" ");
    }
}
