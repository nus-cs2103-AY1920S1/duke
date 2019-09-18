package driver;

import command.*;
import exception.DukeException;
import exception.IncompleteInputException;

/**
 * Parser checks the user input and converts it into command format so that Duke cna execute the program
 */

public class Parser {

    /**
     * Returns Command to execute based on user input
     *
     * @param userInput String of what the user wants to do
     * @return Command so that Duke can carry out the program.
     * @throws DukeException if input does not match any of the standard commands
     * @throws IncompleteInputException if command is to add tasks but it not complete
     */


    public static Command parse(String userInput) throws DukeException, IncompleteInputException {
                 if (userInput.equalsIgnoreCase("list")) {
                    return new ListCommand();
                } else if (userInput.contains("done")) {
                    String[] numTasks = userInput.split(" ");
                    String numberAsString = numTasks[1];
                    int number = Integer.parseInt(numberAsString);
                    return new DoneCommand(number-1);
                } else if (userInput.contains("delete")) {
                     String[] numTasks = userInput.split(" ");
                     String numberAsString = numTasks[1];
                     int number = Integer.parseInt(numberAsString);
                     return new DeleteCommand(number - 1);
                 } else if (userInput.contains("find")) {
                     String mystery = userInput.replace("find ", "");
                     return new SearchCommand(mystery);
                 } else if (userInput.equalsIgnoreCase("stats")) {
                     return new StatisticsCommand();
                } else {
                     if((userInput.contains("todo")&&(userInput.length()>5))||
                              (userInput.contains("event")&&(userInput.length()>6)&&userInput.contains("/"))||
                             (userInput.contains("deadline")&&userInput.length()>9)) {
                            return new AddCommand(userInput);
                     } else if(userInput.contains("todo")) {
                         throw new IncompleteInputException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                     } else if(userInput.contains("deadline")&&(!(userInput.contains("/")))) {
                         throw new IncompleteInputException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                     } else if (userInput.contains("event")&&(!(userInput.contains("/")))) {
                         throw new IncompleteInputException("\u2639 OOPS!!! The description of an event cannot be empty.");
                     } else {
                         throw new DukeException();
                     }

        }
    }
}


