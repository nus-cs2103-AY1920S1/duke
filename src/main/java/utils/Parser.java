package utils;

import exception.EmptyDescriptionException;
import task.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Helps to scan user input and process them into information.
 */
public class Parser {

    private Scanner sc;
    private Ui ui;

    public Parser() {
        ui = Ui.getInstance();
    }

    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Clears the current line the scanner is on.
     */
    public void nextLine() {
        sc.nextLine();
    }

    /**
     * Checks whether there is more content for the scanner to read.
     *
     * @return Whether there is next or not.
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Reads and returns the next action in the user input.
     *
     * @return The next action string.
     */
    public String getNextAction() {
        return sc.next();
    }

    /**
     * Reads and breaks down the content after the "event" action keyword.
     * Invalid input argument types or format will be highlighted to the
     * user.
     *
     * @return If successful, an array of String of length 2 consisting of the
     *     Event Task's name and deadline, a String in the format "DD/MM/YYYY
     *     HHmm". Else, a null object.
     */
    public String[] parseEventDetail() {
        try {
            String taskName = sc.nextLine().trim();
            String[] taskInfo = taskName.split("\\s*/at\\s*");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
            sdf.setLenient(false);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(taskInfo[1]));

            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty() || taskInfo[1].equals("")) {
                throw new EmptyDescriptionException();
            }
            return taskInfo;
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! There must be exactly one argument before and "
                    + "one argument after the keyword %s.\n",
                    Ui.SAD_EMOTICON,
                    "/at"));
            return null;

        } catch (ParseException e) {
            ui.addWarningMessage(Ui.SAD_EMOTICON
                    + " OOPS!!! Date must be in the format \"dd/MM/yyyy HHmm\""
                    + " and must be valid.\n");
            return null;

        } catch (EmptyDescriptionException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! The description of a %s cannot be empty.\n",
                    Ui.SAD_EMOTICON,
                    "event"));
            return null;
        }
    }

    /**
     * Reads and breaks down the content after the "deadline" action keyword.
     * Invalid input argument types or format will be highlighted to the user.
     *
     * @return If successful, an array of String of length 2 consisting of the
     *     Deadline Task's name and deadline, a String in the format "DD/MM/YYYY
     *     HHmm". Else, a null object.
     */
    public String[] parseDeadlineDetail() {
        try {
            String taskName = sc.nextLine().trim();
            String[] taskInfo = taskName.split("\\s*/by\\s*");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
            sdf.setLenient(false);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(taskInfo[1]));

            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return taskInfo;

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! There must be exactly one argument before and "
                    + "one argument after the keyword %s.\n",
                    Ui.SAD_EMOTICON,
                    "/by"));
            return null;

        } catch (ParseException e) {
            ui.addWarningMessage(Ui.SAD_EMOTICON
                    + " OOPS!!! Date must be in the format \"dd/MM/yyyy HHmm\" "
                    + "and must be valid.\n");
            return null;

        } catch (EmptyDescriptionException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! The description of a %s cannot be empty.\n",
                    Ui.SAD_EMOTICON,
                    "deadline"));
            return null;
        }
    }

    /**
     * Reads and breaks down the content after the "todo" action keyword.
     * Invalid input argument types or format will be highlighted to the
     * user.
     *
     * @return If successful, a String representing the Task's name.
     *     Else, a null object.
     */
    public String parseTodoDetail() {
        try {
            String taskName = sc.nextLine().trim();
            if (taskName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return taskName;
        } catch (EmptyDescriptionException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! The description of a %s cannot be empty.\n",
                    Ui.SAD_EMOTICON,
                    "todo"));
            return null;
        }
    }

    /**
     * Reads the next integer from user input where the integer must be
     * a valid index for the tasks in TaskList.
     *
     * @return If successful, an Integer representing the task index. Else, a null object.
     */
    public Integer getTaskIdx() {

        TaskList taskList = TaskList.newInstance();
        try {
            String infoString = sc.nextLine().trim();
            int idx = Integer.parseInt(infoString) - 1;
            taskList.get(idx);
            return idx;

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                ui.addWarningMessage(String.format("%s OOPS!!! You have no task at the moment.\n",
                        Ui.SAD_EMOTICON,
                        1,
                        taskList.size()));
            } else {
                ui.addWarningMessage(String.format("%s OOPS!!! Task index number must be a number from %d to %d.\n",
                        Ui.SAD_EMOTICON,
                        1,
                        taskList.size()));
            }
            return null;

        } catch (NumberFormatException e) {
            ui.addWarningMessage(String.format("%s OOPS!!! Task index number must be a number from %d to %d.\n",
                    Ui.SAD_EMOTICON,
                    1,
                    taskList.size()));
            return null;
        }
    }

    /**
     * Reads the remaining String from Scanner as the keyword for 'Find' action.
     * Invalid input argument types or format will be highlighted to the user.
     *
     * @return If successful, the keyword in String. Else, a null object.
     */
    public String parseFindKeyword() {
        try {
            String keyword = sc.nextLine().trim();
            if (keyword.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return keyword;
        } catch (EmptyDescriptionException e) {
            ui.addWarningMessage(Ui.SAD_EMOTICON + " OOPS!!! The keyword for \"find\" cannot be empty.\n");
            return null;
        }
    }
}
