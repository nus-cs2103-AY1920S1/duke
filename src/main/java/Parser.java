import java.util.ArrayList;

/**
 * Makes sense of user commands and holds and arrayList containing information of how to process commands.
 */
public class Parser {
    private boolean valid = true;
    private ArrayList<String> list = new ArrayList<>();
    private String type;

    /**
     * Creates a Parser object that takes a command and processes an arrayList containing information to be used to
     * declare and initialize Tasks.
     * @param command command provided by user.
     * @throws DukeException exception returned when format of command not right.
     */
    public Parser(String command) throws DukeException {
        String[] commandWords = command.split(" ");
        String order = commandWords[0];
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
                        + "and start and end time not provided.");
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
            int startMinutes = startTime % 100;
            int endHours = endTime / 100;
            int endMinutes = endTime % 100;
            String[] dateArray = dateAndTime[0].split("/");
            list.add(details[0]);
            list.add(dateArray[0]);
            list.add(dateArray[1]);
            list.add(dateArray[2]);
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
            String[] dateArray = dateAndTime[0].split("/");
            int time = 0;
            try {
                time = Integer.parseInt(dateAndTime[1]);
            } catch (NumberFormatException nfe) {
                throw new DukeException(" :( OOPS!!! Invalid Format. Please enter numbers for date and time in "
                        + "'DD/MM/YYYY HHMM' format");
            }
            int hours = time / 100;
            int minutes = time % 100;
            list.add(details[0]);
            list.add(dateArray[0]);
            list.add(dateArray[1]);
            list.add(dateArray[2]);
            list.add("" + hours);
            list.add("" + minutes);
        } else if (order.equals("done") || order.equals("delete")) {
            type = order;
            list.add(command.split(order + " ")[1]);
        } else if (order.equals("list") || order.equals("bye")) {
            type = order;
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
     * @return arrayList that contains particular command information at each index.
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
