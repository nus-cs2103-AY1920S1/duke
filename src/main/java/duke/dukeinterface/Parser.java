package duke.dukeinterface;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.SearchCommand;
import textfiles.WriteFile;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
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
     *
     * @param commandArr Input that the user gave to Duke.
     * @throws DukeException Exception thrown when an invalid command is given.
     */
    public void checkCommand(String... commandArr) throws DukeException {
        if (!commandArr[0].matches("todo|deadline|event|done|list|bye|delete|find|help"
                + "|morehelp|l|t|d|e|f|do|dd|bb")) {
            throw new DukeException(
                    printLine()
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + printLine());
        }

        if (commandArr[0].matches("list|bye|help|morehelp|l|bb")) {
            if (commandArr.length > 1) {
                throw new DukeException(
                        printLine()
                        + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + printLine());
            }
        }
    }

    /**
     * Validates the timing of the task given by the user.
     *
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
                    int monthCurr = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);

                    sj.add(formatDate(day));
                    sj.add(month[monthCurr]);
                    sj.add(year + ",");
                } else {
                    throw new DukeException(
                            printLine()
                            + "     ☹ OOPS!!! Please type in a valid date: DD/MM/YYYY\n"
                            + printLine());
                }
            } else if (str.matches("[0-9]{4}")) {
                if (validateTime(str)) {
                    int hour = Integer.parseInt(str.substring(0, 2));
                    int min = Integer.parseInt(str.substring(2, 4));

                    sj.add(formatTime(hour, min));
                } else {
                    throw new DukeException(
                            printLine()
                            + "     ☹ OOPS!!! Please type in a valid 24hrs timing\n"
                            + printLine());
                }
            } else {
                sj.add(str);
            }
        }
        return sj.toString();
    }

    /**
     * Format the date using ordinal indicator.
     *
     * @param day day of the date indicated by the user.
     * @return string which contains the format date.
     */
    private String formatDate(int day) {
        int last = day % 10;
        String result;

        if (last >= 1 && last <= 3 && day / 10 != 1) {
            if (last == 1) {
                result = "st of";
            } else if (last == 2) {
                result = "nd of";
            } else {
                result = "rd of";
            }
        } else {
            result = "th of";
        }

        return day + result;
    }

    /**
     * Format the time based on the 24hr clock specified by the user.
     *
     * @param hour hour of the time indicated by the user.
     * @param min minute of the time indicated by the user.
     * @return string which contains the formatted time.
     */
    private String formatTime(int hour, int min) {
        if (hour > 12) {
            hour -= 12;
        } else if (hour == 0) {
            hour = 12;
        }

        return String.format("%d:%02dam", hour, min);
    }

    /**
     * Checks whether the timing of the task given by the user is valid.
     *
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
     *
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
     *
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
     *
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
     *
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
            String helpname = "help/index.html";
            switch (commandArr[0]) {
            case "list":
            case "l":
                result = taskList.printArray();
                break;

            case "help":
                duke.command.Command c = new HelpCommand();
                result = ((HelpCommand) c).helpCommand();
                break;

            case "morehelp":
                c = new HelpCommand();
                java.awt.Desktop.getDesktop().browse(URI.create("https://ngzhaoming.github.io/duke/"));
                result = ((HelpCommand) c).moreHelpCommand();
                break;

            case "done":
            case "do":
                c = new DoneCommand();
                int indexDone = Integer.parseInt(commandArr[1]) - 1;
                assert indexDone >= 0 : "Index of the list cannot be less than 0";
                Task currTask = taskList.get(indexDone);
                currTask.markAsDone();
                result = ((DoneCommand) c).taskComplete(currTask);
                data.replaceNthLine(filename, indexDone, currTask);
                break;

            case "delete":
            case "dd":
                c = new DeleteCommand();
                int index = Integer.parseInt(commandArr[1]) - 1;
                assert index >= 0 : "Index of the list cannot be less than 0";
                result = ((DeleteCommand) c).deleteComplete(taskList.size(),
                        taskList.get(index));
                taskList.remove(index);
                data.removeNthLine(filename, index);
                break;

            case "find":
            case "f":
                c = new SearchCommand();
                result = ((SearchCommand) c).searchKeyword(taskList, commandArr);
                break;

            case "todo":
            case "t":
                c = new AddCommand();
                result = ((AddCommand) c).getDescription(commandArr);
                Task todo = new ToDo(result);
                taskList.add(todo);
                data.writeToFile("T | ✘ | " + result);
                result = taskList.printTask(todo, taskList.size());
                break;

            case "deadline":
            case "d":
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
            case "e":
                c = new AddCommand();
                String eventDescription = ((AddCommand) c).getDescription(commandArr);
                String eventTime = ((AddCommand) c).getTime(commandArr);
                eventTime = checkTime(eventTime);
                Task event = new Event(eventDescription, eventTime);
                taskList.add(event);
                data.writeToFile("E | ✘ | " + eventDescription + " | " + eventTime);
                result = taskList.printTask(event, taskList.size());
                break;

            case "bye":
            case "bb":
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
     * Direct the user to the detailed help page.
     *
     * @param helpname file path to the help page.
     */
    private void fileRead(String helpname) {
        try {
            File file = new File(helpname);
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
