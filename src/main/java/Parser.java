import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {
    public static void handleInput(String type, String userInput) throws EmptyFieldException, InvalidInputException, ParseException {
        if (type.equals("done")) {
            if (userInput.substring(4).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The task number cannot be empty.");
            }
            int taskNum = Integer.parseInt(userInput.substring(5));
            if (taskNum > TaskList.getTaskListSize()) {
                throw new InvalidInputException("☹ OOPS!!! You do not have that many tasks.");
            } else if (taskNum <= 0) {
                throw new InvalidInputException("☹ OOPS!!! Your task number is invalid.");
            }
        } else if (type.equals("delete")) {
            if (userInput.substring(6).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The task number cannot be empty.");
            }
            int taskNum = Integer.parseInt(userInput.substring(7));
            if (taskNum > TaskList.getTaskListSize()) {
                throw new InvalidInputException("☹ OOPS!!! You do not have that many tasks.");
            } else if (taskNum <= 0) {
                throw new InvalidInputException("☹ OOPS!!! Your task number is invalid.");
            }
        } else if (type.equals("todo")) {
            if (userInput.substring(4).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (type.equals("deadline")) {
            if (userInput.substring(8).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The new deadline cannot be empty.");
            } else if (!userInput.contains("/by")) {
                throw new EmptyFieldException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
            }
            String description = userInput.substring(9, userInput.indexOf('/'));
            if (description.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (type.equals("by")) {
            if (userInput.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
            }
            String formatString = "dd/mm/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(userInput.split(" ")[0]);
        } else if (type.equals("event")) {
            if (userInput.substring(5).isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The new event cannot be empty.");
            } else if (!userInput.contains("/at")) {
                throw new EmptyFieldException("☹ OOPS!!! The 'at' field of an event cannot be empty.");
            }
            String description = userInput.substring(6, userInput.indexOf('/'));
            if (description.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else if (type.equals("at")) {
            if (userInput.isBlank()) {
                throw new EmptyFieldException("☹ OOPS!!! The 'at' field of an event cannot be empty.");
            }
            String formatString = "dd/mm/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(userInput.split(" ")[0]);
        } else if (type.isBlank() && userInput.isBlank()) {
            throw new EmptyFieldException("☹ OOPS!!! I'm sorry, but you have to input something");
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
