import java.util.Scanner;

public class Parser {

    public static Command parse(String command) {
        String[] commandArr = command.split(" ");
        String directive = commandArr[0];
        if (directive.equals("list")) {
            return new ReadCommand();
        } else if (directive.equals("bye")) {
            return new ExitCommand();
        } else if (directive.equals("done")) {
            int position = Integer.valueOf(commandArr[1]);
            return new UpdateCommand("done", position);
        } else if (directive.equals("delete")) {
            int position = Integer.valueOf(commandArr[1]);
            return new DeleteCommand("delete", position);
        } else if (isTask(directive)) {
            if (commandArr.length < 2) {
                System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", directive);
                return null;
            }
            if (directive.equals("todo")) {
                return new CreateCommand(directive, commandArr[1]);
            } else {
                try {
                    String taskDetails = directive.equals("deadline") ? command.substring(9) : command.substring(6);
                    String description = taskDetails.split("/")[0].trim();
                    String addOns = directive.equals("deadline")
                            ? taskDetails.substring(taskDetails.lastIndexOf("by") + 3).trim()
                            : taskDetails.substring(taskDetails.lastIndexOf("at") + 3).trim();
                    return new CreateCommand(directive, description, addOns);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Invalid event input");
                    return null;
                }
            }
        }
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        return null;
    }

    private static boolean isTask(String directive) {
        return directive.equals("todo") || directive.equals("deadline") || directive.equals("event");
    }
}