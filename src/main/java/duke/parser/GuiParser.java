package duke.parser;

import duke.exception.DukeException;
import duke.tasklist.GuiTaskList;

import java.util.Scanner;

public class GuiParser  {
    GuiTaskList storeTaskList;


    /**
     * Parser class responsible for parsing user input.
     * This includes asking for user input, decoding user input accordingly.
     * Only responsible for making sense of the users command e.g String manipulation
     * @param list TaskList object at any instance, usually the TaskList read from the hard drive
     */
    public GuiParser(GuiTaskList list) {
        this.storeTaskList = list;
    }

    /**
     * main driver method for Parser class
     * calls and links to other objects to execute the entire program
     * returns a String
     */
    public String readUserInput(String string) {
        String argument = string;
        if (argument.equals("list")) {
            String result = storeTaskList.listTask();
            return result;
        } else {
            try {
                String[] inputArray = argument.split(" ");
                //start of toDo,Event,Deadline
                if (inputArray[0].equals("done")) {
                    return processDone(inputArray);
                } else if (inputArray[0].equals("todo")) {
                    return processTodo(inputArray);
                } else if (inputArray[0].equals("deadline")) {
                    return processDeadline(inputArray);
                } else if (inputArray[0].equals("event")) {
                    return processEvent(inputArray);
                } else if (inputArray[0].equals("delete")) {
                    return processDelete(inputArray);
                } else if (inputArray[0].equals("find")) {
                    String result = storeTaskList.findTask(inputArray[1]);
                    return result;
                } else if (inputArray[0].equals("bye")) {
                    System.exit(0);
                } else {
                    //handles error for not recognized command
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IndexOutOfBoundsException e) {
                return "invalid number not in current list , please enter input again";
            } catch (DukeException e) {
                return e.toString();
            }
        }
        return "";
    }

    private String processDone(String[] inputArray) throws DukeException{
        if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description of a done command cannot be empty.");
        }
        int index = Integer.valueOf(inputArray[1]) - 1;
        String result = storeTaskList.doneTask(index);
        return result;
    }

    private String processTodo(String[] inputArray) throws DukeException {
        //catch empty desc error
        if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        //form back string
        String toDoTaskString = "";
        for (int i = 1; i < inputArray.length; i++) {
            toDoTaskString += inputArray[i];
            toDoTaskString += " ";
        }
        //.trim() to remove trailing space
        String result = storeTaskList.addToDoTask(toDoTaskString.trim());
        return result;
    }

    private String processDeadline(String[] inputArray) throws DukeException {
         //catch empty desc error
         if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        //form back string , description stops at /by
        //date time starts from /by
        String deadlineTaskDescriptionString = "";
        String deadlineTaskDateAndTimeString = "";
        boolean createDesc = true;
        //catch error of no specific date time after /by
        if (inputArray[inputArray.length - 1].matches("/by")) {
            throw new DukeException("Oops, no specific date/time supplied");
        }
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i].equals("/by")) {
                createDesc = false;
            } else if (createDesc) {
                deadlineTaskDescriptionString += inputArray[i];
                deadlineTaskDescriptionString += " ";
            } else {
                deadlineTaskDateAndTimeString += inputArray[i];
                deadlineTaskDateAndTimeString += " ";
            }
        }
        deadlineTaskDateAndTimeString = convertStringToDate(deadlineTaskDateAndTimeString);
        String result = storeTaskList.addDeadlineTask(deadlineTaskDescriptionString.trim(), deadlineTaskDateAndTimeString.trim());
        return result;
    }

    private String processEvent(String[] inputArray) throws DukeException {
        //catch empty desc error
        if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        //form back string , description stops at /at date time starts from /at
        String eventTaskDescriptionString = "";
        String eventTaskDateAndTimeString = "";
        boolean createDesc = true;
        //catch error of no specific date time after /at
        if (inputArray[inputArray.length - 1].matches("/at")) {
            throw new DukeException("Oops, no specific duration supplied");
        }
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i].equals("/at")) {
                createDesc = false;
            } else if (createDesc) {
                eventTaskDescriptionString += inputArray[i];
                eventTaskDescriptionString += " ";
            } else {
                eventTaskDateAndTimeString += inputArray[i];
                eventTaskDateAndTimeString += " ";
            }
        }
        eventTaskDateAndTimeString = convertStringToDateEvent(eventTaskDateAndTimeString);
        String result = storeTaskList.addEventTask(eventTaskDescriptionString.trim(), eventTaskDateAndTimeString.trim());
        return result;
    }

    private String processDelete(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description for delete command cannot be empty.");
        }
        int index = Integer.valueOf(inputArray[1]) - 1;
        String result = storeTaskList.deleteTask(index);
        return result;
    }


    /**
     * String manipulation method to return a formatted
     * from 2/12/2019 1800 to 2nd of December 2019 6pm etc
     * @param dateAndTimeString given string in the format of d/mm/yyyy HHmm
     * @return formatted date and time
     */
    private static String convertStringToDate(String dateAndTimeString) throws DukeException {
            String[] arrayOfDateAndTime = dateAndTimeString.split(" ");
            String date = arrayOfDateAndTime[0];
            String time = arrayOfDateAndTime[1];
            // d/mm/yyyy
            date = formatString(date);
            Integer timeInInt = Integer.valueOf(time);
            time = convertTime(timeInInt);
            dateAndTimeString = date + ", " + time;
            return dateAndTimeString;
    }


    /**
     * converts time to string
     * @param convertedTime given input e.g 1630
     * @return formatted string e.g 1630 becomes 430pm
     * @throws DukeException exception when the input is not four digits / out of bounds e.g 2500
     */
    private static String convertTime(Integer timeInInt) throws  DukeException{
        String time = "";
        if (timeInInt == 0) {
            //midnight
            timeInInt = 12;
        } else if (timeInInt > 0 && timeInInt < 1200) {
            time = String.valueOf(timeInInt) + "am";
        } else if (timeInInt < 2359 && timeInInt >= 1300) {
            //1300 / 100 = 13 % 12
            int hrs = (timeInInt / 100) % 12;
            int mins = timeInInt % 100;
            if (mins == 0) {
                time = hrs + "pm";
            } else {
                time = String.valueOf(hrs) + mins + "pm";
            }
        } else if (timeInInt < 1300 && timeInInt >= 1200) {
            int mins = timeInInt % 100;
            if (mins == 0) {
                time = "12pm";
            } else {
                time = "12" + mins + "pm";
            }
        } else {
            throw new DukeException("invalid time entered");
        }
        return time;
    }

    /**
     *
     * helper function to convert d/mm/yyyy to the correct format
     * @param date inputdate etc 2/12/2019
     * @return formatted date
     */

    private static String formatString(String date) {
        String[] newArr = date.split("/");
        String result = "";
        String num = newArr[0];
        num = getOrdinal(num);
        String month = newArr[1];
        month = getMonth(month);
        return num + " of " + month + " " + newArr[2];
    }

    /**
     * method to return the month in full spelling
     * @param month given month number as input
     * @return name of the month
     */
    private static String getMonth(String month) {
        final String[] arrMonths = {"entry to pad", "January", "February", "March", "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};
        int temp = Integer.valueOf(month);
        return arrMonths[temp];
    }

    /**
     * method to find the ordinal of each number e.g 1st, 2nd, 3rd
     * @param num input num
     * @return formatted number e.g 1 becomes 1st, 21 becomes 21st
     */
    private static String getOrdinal(String num) {
        int temp = Integer.valueOf(num);
        if (temp == 1 || temp == 21 || temp == 31) {
            return temp + "st";
        } else if (temp == 2 || temp == 22 ) {
            return temp + "nd";
        } else if (temp == 3 || temp == 23) {
            return temp + "rd";
        }else {
            return temp + "th";
        }
    }

    /**
     * Helper function to convert the inputed date for the Event task
     * @param dateAndTimeString  e.g 2/12/2019 1400-1500
     * @return formatted string
     */
    private static String convertStringToDateEvent(String dateAndTimeString) throws DukeException {
            String[] arrayOfDateAndTime = dateAndTimeString.split(" ");
            String date = arrayOfDateAndTime[0];
            String temp = arrayOfDateAndTime[1];
            String[] arrayOfTime = temp.split("-");
            String timeStarting = arrayOfTime[0];
            String timeEnding = arrayOfTime[1];
            date = formatString(date);
            timeStarting = convertTime(Integer.valueOf(timeStarting));
            timeEnding = convertTime(Integer.valueOf(timeEnding));
            dateAndTimeString = date + ", " + timeStarting + "-" +timeEnding;
            return dateAndTimeString;
    }

    /**
     * method for testing private static methods
     * @param test dummy String
     * @return
     */
    public static String accessConvertStringToDate(String test) throws DukeException {
        return convertStringToDate(test);
    }

    /**
     * method for testing private static method
     * @param convertedTime dummy int
     * @return
     * @throws DukeException
     */
    public static String accessConvertTime(int convertedTime) throws DukeException {
        return convertTime(convertedTime);
    }
}