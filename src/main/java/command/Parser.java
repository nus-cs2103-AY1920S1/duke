package command;

import task.TaskList;
import duke.DukeException;
import java.util.Scanner;

public class Parser {


    private Scanner scanner = new Scanner(System.in);
    private Ui ui = new Ui();

    public Parser() {
    }

    public void parseDuke(Storage storage, TaskList tasks, String input) {
        Parser parser = new Parser();
        while (!input.equals("bye")) {

            // Get entire line of input from command-line
            input = scanner.nextLine().trim(); //Remove blank space

            // Store whatever text entered, except "bye", exit loop
            if (input.equals("bye")) {
                break;
            }
            ui.separator();

            if (input.equals("list")) {
                ui.showList();
                tasks.printList();
            } else if (input.contains("done")) {

                try {
                    //Mark task as done
                    tasks.setDone(input);
                } catch (NullPointerException err) {
                    ui.invalidEntry();
                    continue;
                }

            } else if (input.contains("delete")) {

                try {
                    //Mark task as done
                    tasks.deleteTask(input);
                } catch (NullPointerException err) {
                    ui.invalidEntry();
                    continue;
                }
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {

                try {
                    String action = parser.parseAction(input);
                    ui.addTask();
                    tasks.addTask(action, input);
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }

            } else if (input.contains("find")) {
                String keyword = parser.parseDescription("find", input);
                ui.matchingList();
                tasks.getList(keyword);
            } else {
                // Do not fit any commands
                try {
                    throw new DukeException(ui.invalidCommand());
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                    ui.separator();
                    System.out.println("");
                    continue;
                }
            }

            ui.separator();
            System.out.println();

            try {
                storage.saveFile(tasks, storage.getFilePath());
            } catch (DukeException e) {
                ui.saveError();
            }
        }
    }
    /**
     * Parse the input to return the user action as a String.
     * Ensure that the input has a valid action.
     * If invalid, throw DukeException, indicate action is not recognised.
     * If action is valid but description is blank, ask for description.
     *
     * @param input Entire input command from the user
     * @return action Return the action command of the user
     * @throws DukeException If action or input is invalid
     */
    public String parseAction(String input) throws DukeException {
        String[] substrings = input.split(" ");
        String action = substrings[0];

        if (substrings.length == 0) { // Invalid command
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (substrings.length == 1) { // No description
            throw new DukeException("    ☹ OOPS!!! The description of a " + action + " cannot be empty.");
        }

        return action;
    }

    /**
     * Parse the description from the input as a String.
     * For task.Event or task.Deadline Tasks, important to split substrings into
     * description and date/time, while for Todo Tasks and "find" command not required.
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return description/substring The description of the task, without action and dateTime
     */
    public String parseDescription(String action, String input) {
        String substring = input.replace(action, "");
        //Split task and date or time
        String[] parts = substring.split("\\/..");
        String description;
        if (action.equals("todo") || action.equals("find")) {
            return substring.trim(); //no date or time
        } else {
            description = parts[0].trim(); // Remove blank spaces
        }
        return description;
    }

    /**
     * Parse the date or time as a String.
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return dateTime The date or time as a String
     */
    public String parseDateTime(String action, String input) {
        String substring = input.replace(action, "");
        //Split task and date time
        String[] parts = substring.split("\\/..");
        String dateTime = parts[1].trim(); // Remove blank spaces

        return dateTime;
    }


}
