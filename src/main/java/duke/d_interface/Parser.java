package duke.d_interface;

import java.util.StringJoiner;

/**
 * Validates the input commands from the user.
 */
public class Parser {
    /**
     * Validates the input that the user gave to Duke.
     * @param commandArr Input that the user gave to Duke.
     * @throws DukeException Exception thrown when an invalid command is given.
     */
    public void checkCommand(String[] commandArr) throws DukeException {
        if (!commandArr[0].matches("todo|deadline|event|done|list|bye|delete")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________\n");
        }

        if (commandArr[0].matches("list|bye")) {
            if (commandArr.length > 1) {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________\n");
            }
        }
    }

    /**
     * Validates the timing of the task given by the user.
     * @param time Timing of the specific task.
     * @return Formatted timing of the date and time.
     * @throws DukeException Exception thrown when an invalid date or timing is given.
     */
    public String checkTime(String time) throws DukeException {
        String[] timeArr = time.split(" ");
        String[] month = {"NIL", "January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};
        StringJoiner sj = new StringJoiner(" ");

        for (String str : timeArr) {
            if (str.matches("([0-9]?[0-9])/([0-9]?[0-9])/([0-9]{4})")) {
                if (validateDate(str)) {
                    String[] date = str.split("/");
                    int day = Integer.parseInt(date[0]);
                    int last = day % 10;
                    int monthCurr = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);

                    if (last == 1) {
                        sj.add(day + "st of");
                    } else if (last == 2) {
                        sj.add(day + "nd of");
                    } else if (last == 3) {
                        sj.add(day + "rd of");
                    } else {
                        sj.add(day + "th of");
                    }

                    sj.add(month[monthCurr]);
                    sj.add(year + ",");
                } else {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! Please type in a valid date: DD/MM/YYYY\n" +
                            "    ____________________________________________________________\n");
                }
            } else if (str.matches("[0-9]{4}")) {
                if (validateTime(str)) {
                    int hour = Integer.parseInt(str.substring(0, 2));
                    int min = Integer.parseInt(str.substring(2, 4));

                    if (hour == 12) {
                        sj.add(String.format("%d:%02dpm", hour, min));
                    } else if (hour > 12) {
                        sj.add(String.format("%d:%02dpm", hour - 12, min));
                    } else if (hour == 0) {
                        sj.add(String.format("12:%02dam", min));
                    } else {
                        sj.add(String.format("%d:%02dam", hour, min));
                    }
                } else {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! Please type in a valid 24hrs timing\n" +
                            "    ____________________________________________________________\n");
                }
            } else {
                sj.add(str);
            }
        }
        return sj.toString();
    }

    /**
     * Checks whether the timing of the task given by the user is valid.
     * @param str Takes in the timing of the event or deadline task.
     * @return boolean result on whether the timing is in the valid format.
     */
    private boolean validateTime(String str) {
        boolean result = true;
        int hour = Integer.parseInt(str.substring(0, 2));
        int min = Integer.parseInt(str.substring(2, 4));

        if (min < 0 || min > 59) {
            result = false;
        }

        if (hour < 0 || hour > 23) {
            result = false;
        }

        return result;
    }

    /**
     * Checks whether the date of the task given by the user is valid.
     * @param str Takes in the date of thee event or deadline task.
     * @return boolean result on whether the date is in the valid format.
     */
    private boolean validateDate(String str) {
        String[] date = str.split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        boolean result = true;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month > 0 && month < 13) {
            int end = days[Integer.parseInt(date[1])];
            if ((year % 100 == 0 && year % 400 == 0) && year % 4 == 0 && month == 2) {
                end = 29;
            }

            if (day < 0 || day > end) {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
