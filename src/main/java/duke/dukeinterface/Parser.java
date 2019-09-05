package duke.dukeinterface;

import textfiles.WriteFile;

import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.SearchCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.StringJoiner;
import java.lang.StringBuilder;

/**
 * Validates the input commands from the user.
 */
public class Parser {
    /**
     * This field combines strings together and form Duke's replies to user's inputs.
     */
    private StringBuilder sb;

    /**
     * Validates the input that the user gave to Duke.
     * @param commandArr Input that the user gave to Duke.
     * @throws DukeException Exception thrown when an invalid command is given.
     */
    public void checkCommand(String... commandArr) throws DukeException {
        if (!commandArr[0].matches("todo|deadline|event|done|list|bye|delete|find")) {
            throw new DukeException(
                    "    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n");
        }

        if (commandArr[0].matches("list|bye")) {
            if (commandArr.length > 1) {
                throw new DukeException(
                        "    ____________________________________________________________\n"
                        + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "    ____________________________________________________________\n");
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
        String[] month = {"NIL", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
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
                    throw new DukeException(
                            "    ____________________________________________________________\n"
                            + "     ☹ OOPS!!! Please type in a valid date: DD/MM/YYYY\n"
                            + "    ____________________________________________________________\n");
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
                    throw new DukeException(
                            "    ____________________________________________________________\n"
                            + "     ☹ OOPS!!! Please type in a valid 24hrs timing\n"
                            + "    ____________________________________________________________\n");
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

    /**
     * Prompts the user that the input number they have given is invalid.
     * @return numberErrorMessage which tells that the input is invalid.
     */
    private String numberErrorMessage() {
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("     ☹ OOPS!!! Please type in a valid index from 1 to 100\n");
        sb.append(printLine());
        return sb.toString();
    }

    /**
     * Prompts the user that the index they have given is invalid.
     * @param len takes in the length of the current task list.
     * @return indexErrorMessage which tells that the number is invalid.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    private String indexErrorMessage(int len) {
        sb = new StringBuilder();
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("     ☹ OOPS!!! Index out of bounds for task list of length " + len + "\n");
        sb.append(printLine());
        return sb.toString();
    }

    /**
     * Run Duke and generate a reply based on the user's input.
     * @param command takes in the user's input.
     * @param taskList takes in the current task list of the user.
     * @param data takes in the file path to write the data.
     * @return a string consisting of Duke's reply to the user's input.
     */
    public String run(String command, Tasklist taskList, WriteFile data) {
        String[] commandArr = command.split(" ");
        String result = "";
        try {
            checkCommand(commandArr);
            String filename = "data/duke.txt";
            switch (commandArr[0]) {
            case "list":
                result = taskList.printArray();
                break;

            case "done":
                duke.command.Command c = new DoneCommand();
                int indexDone = Integer.parseInt(commandArr[1]) - 1;
                Task currTask = taskList.get(indexDone);
                currTask.markAsDone();
                result = ((DoneCommand) c).taskComplete(currTask);
                data.replaceNthLine(filename, indexDone, currTask);
                break;

            case "delete":
                c = new DeleteCommand();
                int index = Integer.parseInt(commandArr[1]) - 1;
                result = ((DeleteCommand) c).deleteComplete(taskList.size(),
                        taskList.get(index));
                taskList.remove(index);
                data.removeNthLine(filename, index);
                break;

            case "find":
                c = new SearchCommand();
                result = ((SearchCommand) c).searchKeyword(taskList, commandArr);
                break;

            case "todo":
                c = new AddCommand();
                result = ((AddCommand) c).getDescription(commandArr);
                Task todo = new ToDo(result);
                taskList.add(todo);
                data.writeToFile("T | ✘ | " + result);
                result = taskList.printTask(todo, taskList.size());
                break;

            case "deadline":
                c = new AddCommand();
                String deadlineDescription = ((AddCommand) c).getDescription(commandArr);
                String deadlineTime = ((AddCommand) c).getTime(commandArr);
                deadlineTime = checkTime(deadlineTime);
                Task deadline = new Deadline(deadlineDescription, deadlineTime);
                taskList.add(deadline);
                data.writeToFile("D | ✘ | " + deadlineDescription + " | "
                        + deadlineTime);
                result = taskList.printTask(deadline, taskList.size());
                break;

            case "event":
                c = new AddCommand();
                String eventDescription = ((AddCommand) c).getDescription(commandArr);
                String eventTime = ((AddCommand) c).getTime(commandArr);
                eventTime = checkTime(eventTime);
                Task event = new Event(eventDescription, eventTime);
                taskList.add(event);
                data.writeToFile("E | ✘ | " + eventDescription + " | " + eventTime);
                result = taskList.printTask(event, taskList.size());
                break;

            case "exit":
                c = new ExitCommand();
                result = ((ExitCommand) c).exit();
                break;

            default:
                break;
            }
        } catch (DukeException ex) {
            result = (ex.getMessage()) + "\n";
        } catch (IndexOutOfBoundsException ex) {
            result = indexErrorMessage(taskList.size());
        } catch (NumberFormatException ex) {
            result = numberErrorMessage();
        } catch (IOException ex) {
            result = data.ioErrorMessage();
        }
        return result;
    }

    /**
     * Gives a string as a border.
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
