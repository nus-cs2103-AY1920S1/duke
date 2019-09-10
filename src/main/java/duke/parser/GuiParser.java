package duke.parser;

import duke.exception.DukeException;
import duke.tasklist.GuiTaskList;
import duke.ui.GuiUi;

public class GuiParser {
    GuiTaskList storeTaskList;

    /**
     * Parser class responsible for parsing user input.
     * This includes asking for user input, decoding user input accordingly.
     * Only responsible for making sense of the users command e.g String manipulation.
     *
     * @param list TaskList object at any instance, usually the TaskList read from the hard drive
     */
    public GuiParser(GuiTaskList list) {
        this.storeTaskList = list;
    }

    /**
     * main driver method for Parser class.
     * calls and links to other objects to execute the entire program.
     * returns a String.
     */
    public String readUserInput(String string) {
        String argument = string;
        argument = argument.trim();
        if (argument.equals("list")) {
            String result = storeTaskList.listTask();
            return result;
        } else {
            try {
                String[] inputArray = argument.split(" ");
                inputArray[0] = inputArray[0].toLowerCase();
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
                } else if (inputArray[0].equals("help")) {
                    return GuiUi.helpText();
                } else if (inputArray[0].equals("edit")) {
                    return processEdit(inputArray);
                } else if (inputArray[0].equals("bye")) {
                    System.exit(0);
                } else {
                    //handles error for not recognized command
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IndexOutOfBoundsException e) {
                return "invalid number/num of arguments entered!";
            } catch (DukeException e) {
                return e.toString();
            }
        }
        return "";
    }

    private String processEdit(String[] inputArray) throws DukeException {
        // format for deadline: edit 5 change time t: 02/11/15 0500
        // format for event: edit 2 change event t: 02/11/12 0954-1230
        // format for toDo: edit 1 change task t:
        int indexOfItemToEdit = Integer.parseInt(inputArray[1]);
        String typeOfEditCommand = inputArray[2];
        if (typeOfEditCommand.equals("desc")) {
            return changeDescInput(inputArray, indexOfItemToEdit);
        } else if (typeOfEditCommand.equals("time")) {
            return changeTimeInput(inputArray, indexOfItemToEdit);
        } else {
            return changeDescDateTimeInput(inputArray, indexOfItemToEdit);
        }
    }

    private String changeDescInput(String[] inputArray, int indexOfItemToEdit) {
        try {
            String newDesc = " ";
            for (int i = 3; i < inputArray.length; i++) {
                newDesc = newDesc + inputArray[i] + " ";
            }
            return storeTaskList.editTask(indexOfItemToEdit, newDesc.trim(), false);
        } catch (IndexOutOfBoundsException e) {
            return "invalid input. make sure you enter your command as:\n"
                    +
                    "edit <item num> desc <desc>";
        }
    }

    private String changeTimeInput(String[] inputArray, int indexOfItemToEdit) {
        try {
            String newDateAndTime = "";
            for (int i = 3; i < inputArray.length; i++) {
                newDateAndTime = newDateAndTime + inputArray[i] + " ";
            }
            if (newDateAndTime.contains("-")) {
                newDateAndTime = convertStringToDateEvent(newDateAndTime.trim());
            } else {
                newDateAndTime = convertStringToDate(newDateAndTime.trim());
            }
            //System.out.println(newDateAndTime);
            return storeTaskList.editTask(indexOfItemToEdit, newDateAndTime, true);
        } catch (IndexOutOfBoundsException | DukeException e) {
            return "invalid input. make sure you enter your command as:\n"
                    +
                    "edit <item num> time <dd/mm/yy hhmm-hhmm>";
        }
    }


    private String changeDescDateTimeInput(String[] inputArray, int indexOfItemToEdit) throws DukeException {
        String newDesc = " ";
        int i = 2;
        String temp = inputArray[i];
        while (!temp.equals("t:")) {
            newDesc = newDesc + temp + " ";
            i++;
            temp = inputArray[i];
        }
        //for event and deadline tasks
        String newDateAndTime = " ";
        if (i < inputArray.length) {
            try {
                i++;
                for (int j = i; j < inputArray.length; j++) {
                    newDateAndTime = newDateAndTime + inputArray[j] + " ";
                }
                if (newDateAndTime.contains("-")) {
                    newDateAndTime = convertStringToDateEvent(newDateAndTime.trim());
                } else {
                    newDateAndTime = convertStringToDate(newDateAndTime.trim());
                }
            } catch (IndexOutOfBoundsException e) {
                return "new duration or timing supplied "
                        +
                        "is not in the correct format. Type help to see format of commands";
            }
        }
        return storeTaskList.editTask(indexOfItemToEdit, newDesc.trim(), newDateAndTime);
    }

    private String processDone(String[] inputArray) throws DukeException {
        checkInputEmpty(inputArray);
        int index = Integer.valueOf(inputArray[1]) - 1;
        String result = storeTaskList.doneTask(index);
        return result;
    }

    private String processTodo(String[] inputArray) throws DukeException {
        checkInputEmpty(inputArray);
        //form back string
        String toDoTaskString = "";
        for (int i = 1; i < inputArray.length; i++) {
            toDoTaskString = toDoTaskString + inputArray[i] + " ";
        }
        //.trim() to remove trailing space
        String result = storeTaskList.addToDoTask(toDoTaskString.trim());
        return result;
    }

    private String processDeadline(String[] inputArray) throws DukeException {
        try {
            checkInputEmpty(inputArray);
            //form back string , description stops at /by date time starts from /by
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
            String result = storeTaskList.addDeadlineTask(
                    deadlineTaskDescriptionString.trim(),
                    deadlineTaskDateAndTimeString.trim());
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "Incorrect format detected, enter in the form of: \n deadline <deadline name> /by <dd/mm/yy> <hhmm>";
        }
    }

    private String processEvent(String[] inputArray) throws DukeException {
        try { //catch empty desc error
            checkInputEmpty(inputArray);
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
            String result = storeTaskList.addEventTask(
                    eventTaskDescriptionString.trim(),
                    eventTaskDateAndTimeString.trim());
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "Incorrect format detected, enter in the form of: \n event <event name> /at <dd/mm/yy> <hhmm-hhmm>";
        }
    }

    private String processDelete(String[] inputArray) throws DukeException {
        checkInputEmpty(inputArray);
        int index = Integer.valueOf(inputArray[1]) - 1;
        String result = storeTaskList.deleteTask(index);
        return result;
    }


    /**
     * String manipulation method to return a formatted.
     * from 2/12/2019 1800 to 2nd of December 2019 6pm etc.
     *
     * @param dateAndTimeString given string in the format of d/mm/yyyy HHmm
     * @return formatted date and time
     */
    private static String convertStringToDate(String dateAndTimeString) throws DukeException {
        String[] arrayOfDateAndTime = dateAndTimeString.split(" ");
        //System.out.println(arrayOfDateAndTime);
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
     * converts time to string.
     *
     * @param timeInInt given input e.g 1630
     * @return formatted string e.g 1630 becomes 430pm
     * @throws DukeException exception when the input is not four digits / out of bounds e.g 2500
     */
    private static String convertTime(Integer timeInInt) throws DukeException {
        String time = "";
        if (timeInInt == 0) {
            //midnight
            timeInInt = 12;
        } else if (timeInInt > 0 && timeInInt < 1200) {
            int mins = timeInInt % 100;
            if (mins == 0) {
                timeInInt = timeInInt / 100;
            }
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
     * helper function to convert d/mm/yyyy to the correct format.
     *
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
     * method to return the month in full spelling.
     *
     * @param month given month number as input
     * @return name of the month
     */
    private static String getMonth(String month) {
        String[] arrMonths = {"", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
        int temp = Integer.valueOf(month);
        return arrMonths[temp];
    }

    /**
     * method to find the ordinal of each number e.g 1st, 2nd, 3rd.
     *
     * @param num input num
     * @return formatted number e.g 1 becomes 1st, 21 becomes 21st
     */
    private static String getOrdinal(String num) {
        int temp = Integer.valueOf(num);
        if (temp == 1 || temp == 21 || temp == 31) {
            return temp + "st";
        } else if (temp == 2 || temp == 22) {
            return temp + "nd";
        } else if (temp == 3 || temp == 23) {
            return temp + "rd";
        } else {
            return temp + "th";
        }
    }

    /**
     * Helper function to convert the inputed date for the Event task.
     *
     * @param dateAndTimeString e.g 2/12/2019 1400-1500
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
        dateAndTimeString = date + ", " + timeStarting + "-" + timeEnding;
        return dateAndTimeString;
    }

    private void checkInputEmpty(String[] inputArray) throws DukeException {
        if (inputArray.length == 1) {
            throw new DukeException("OOPS!!! The description of a done command cannot be empty.");
        }
    }

    /**
     * method for testing private static methods.
     *
     * @param test dummy String
     * @return
     */
    public static String accessConvertStringToDate(String test) throws DukeException {
        return convertStringToDate(test);
    }

    /**
     * method for testing private static method.
     *
     * @param convertedTime dummy int
     * @return String String that is converted
     * @throws DukeException thrown if erroneous input
     */
    public static String accessConvertTime(int convertedTime) throws DukeException {
        return convertTime(convertedTime);
    }
}