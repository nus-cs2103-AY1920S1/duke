package duke.command;

import java.util.ArrayList;

/**
 * Makes sense of user commands and holds and ArrayList containing information of how to process commands.
 */
public class Parser {
    private boolean valid = true;
    private ArrayList<String> list = new ArrayList<>();
    private String type;

    /**
     * Creates a Parser object that takes a command and processes an ArrayList containing information to be used to
     * declare and initialize Tasks.
     * @param command command provided by user.
     * @throws DukeException exception returned when format of command not right.
     */
    public Parser(String command) throws DukeException {
        command = command.trim();
        String[] commandWords = command.split(" ");
        String order = commandWords[0];
        boolean addedTask = true;
        if (command.split(order  + " ").length == 1
                && (order.equals("event") || order.equals("todo") || order.equals("deadline"))) {
            throw new DukeException(" :( OOPS!!! The description of " + order + "s cannot be empty.");
        }
        if (order.equals("todo")) {
            type = "todo";
            if (command.split("todo ").length > 1) {
                list.add(command.split("todo ")[1]);
            } else {
                throw new DukeException(" :( OOPS!!! Please provide description.");
            }
        } else if (order.equals("event")) {
            if (command.split("/at").length == 1) {
                throw new DukeException(" :( OOPS!!! An event must have a start and end time. Use '/at'.");
            }
            type = "event";
            String instruction = command.split("event ")[1];
            String[] details = instruction.split(" /at ");
            if (details.length != 2) {
                throw new DukeException(" :( OOPS!!! Invalid Format. Either Description or Date"
                        + " and/or start and end time not provided.");
            }
            String[] dateAndTime = details[1].split(" ");
            if (dateAndTime.length != 2) {
                throw new DukeException(" :( OOPS!!! Invalid format. Please enter 'DD/MM/YYYY HHMM-HHMM'");
            }
            if (dateAndTime[1].split("-").length != 2) {
                throw new DukeException(" :( OOPS!!! Invalid format. Must enter start and end time.");
            }
            int startTime = 0;
            int endTime = 0;
            try {
                startTime = Integer.parseInt(dateAndTime[1].split("-")[0]);
                endTime = Integer.parseInt(dateAndTime[1].split("-")[1]);
            } catch (NumberFormatException nfe) {
                throw new DukeException(" :( OOPS!!! Invalid Format. Please enter numbers for date and time in "
                        + "'DD/MM/YYYY HHMM-HHMM' format");
            }
            int startHours = startTime / 100;
            assert startHours >= 0 && startHours <= 24 : "Hours should be in 0-24 range!";
            int endHours = endTime / 100;
            assert endHours >= 0 && endHours <= 24 : "Hours should be in 0-24 range!";
            int startMinutes = startTime % 100;
            assert startMinutes >= 0 && startMinutes <= 60 : "Minutes should be in 0-60 range!";
            int endMinutes = endTime % 100;
            assert endMinutes >= 0 && endMinutes <= 60 : "Minutes should be in 0-60 range!";
            String[] dateArray = dateAndTime[0].split("/");
            String day = dateArray[0];
            String month = dateArray[1];
            String year = dateArray[2];
            assert Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12 : "Month should be in 1-12 range!";
            assert Integer.parseInt(year) > 0 : "Year should be positive (restricted to A.D.)";
            assert Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31 : "Day should be restricted in 1-31 range";
            list.add(details[0]);
            list.add(day);
            list.add(month);
            list.add(year);
            list.add("" + startHours);
            list.add("" + startMinutes);
            list.add("" + endHours);
            list.add("" + endMinutes);
        } else if (order.equals("deadline")) {
            type = "deadline";
            if (command.split("/by").length == 1) {
                throw new DukeException(" :( OOPS!!! An deadline must have date and time. Use '/by'.");
            }
            String instruction = command.split("deadline ")[1];
            String[] details = instruction.split(" /by ");
            if (details.length != 2) {
                throw new DukeException(" :( OOPS!!! Invalid Format. Either Description or DateAndTime not provided.");
            }
            String[] dateAndTime = details[1].split(" ");
            if (dateAndTime.length != 2) {
                throw new DukeException(" :( OOPS!!! Invalid format for date and time. Please enter 'DD/MM/YYYY HHMM'");
            }
            int time = 0;
            try {
                time = Integer.parseInt(dateAndTime[1]);
            } catch (NumberFormatException nfe) {
                throw new DukeException(" :( OOPS!!! Invalid Format. Please enter numbers for date and time in "
                        + "'DD/MM/YYYY HHMM' format");
            }
            int hours = time / 100;
            int minutes = time % 100;
            String[] dateArray = dateAndTime[0].split("/");
            assert hours >= 0 && hours <= 24 : "Hour should be within 0-24 range!";
            assert minutes >= 0 && minutes <= 60 : "Minutes should be within 0-60 range!";
            String day = dateArray[0];
            String month = dateArray[1];
            String year = dateArray[2];
            assert Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12 : "Month should be in 1-12 range!";
            assert Integer.parseInt(year) > 0 : "Year should be positive (restricted to A.D.)";
            assert Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31 : "Day should be restricted in 1-31 range";
            list.add(details[0]);
            list.add(dateArray[0]);
            list.add(dateArray[1]);
            list.add(dateArray[2]);
            list.add("" + hours);
            list.add("" + minutes);
        } else if (order.equals("done") || order.equals("delete")) {
            type = order;
            list.add(command.split(order + " ")[1]);
        } else if (order.equals("list") || order.equals("bye") || order.equals("deletedone")) {
            type = order;
        } else if (order.equals("find")) {
            type = "find";
            list.add(command.split("find ")[1]);
        } else {
            valid = false;
        }
    }

    /**
     * Returns the type of command to execute.
     * @return type of command according to which Duke's run can perform certain roles.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns information about the command to be executed.
     * @return ArrayList that contains particular command information at each index.
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * Determines whether the command is Valid or not.
     * @return boolean to determine if illegal command provided by user.
     */
    public boolean isValid() {
        return valid;
    }
}
