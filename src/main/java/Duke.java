import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //intialize an arraylist to store strings
        List<Task> store = new ArrayList<Task>();
        Scanner myScanner = new Scanner(System.in);
        String argument = myScanner.nextLine();
        //when user input is not bye
        while (!argument.equals("bye") && !argument.equals("Bye")) {
            if (argument.equals("list")) {
                int num = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task i : store) {
                    System.out.println(+num + ". " + i);
                    num++;
                }
                argument = myScanner.nextLine();
            } else {
                String[] argumentArray = argument.split(" ");
                if (argumentArray[0].equals("done")) {
                    try {
                        if (argumentArray.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a done command cannot be empty.");
                        }

                        int index = Integer.valueOf(argumentArray[1]) - 1;
                        //catch task number not in list error
                        Task taskToModify = store.get(index);
                        taskToModify.markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(taskToModify);
                        //argument = myScanner.nextLine();
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("invalid number not in current list");

                    }catch (DukeException e) {
                        System.out.println(e);
                    } finally {
                        argument = myScanner.nextLine();
                    }
                } else if (argumentArray[0].equals("todo")) {
                    try {
                        //catch empty desc error
                        if (argumentArray.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        //form back string
                        String toDoTaskString = "";
                        for (int i = 1; i < argumentArray.length; i++) {
                            toDoTaskString += argumentArray[i];
                            toDoTaskString += " ";

                        }
                        //.trim() to remove trailing space
                        Task toDoTask = new ToDo(toDoTaskString.trim());
                        store.add(toDoTask);
                        printGotIt();
                        System.out.println(" " + toDoTask.toString());
                        printNow(store.size());
                        //argument = myScanner.nextLine();
                    } catch (DukeException e) {
                        System.out.println(e);
                    } finally {
                        argument = myScanner.nextLine();
                    }
                } else if (argumentArray[0].equals("deadline")) {
                    try {
                        //catch empty desc error
                        if (argumentArray.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        //form back string , description stops at /by
                        //date time starts from /by
                        String deadlineTaskDescriptionString = "";
                        String deadlineTaskDateAndTimeString = "";
                        boolean createDesc = true;
                        //catch error of no specific date time after /by
                        if (argumentArray[argumentArray.length -1].matches("/by")) {
                            throw new DukeException("Oops, no specific date/time supplied");
                        }

                        for (int i = 1; i < argumentArray.length; i++) {
                            if (argumentArray[i].equals("/by")) {
                                createDesc = false;
                            } else if (createDesc) {
                                deadlineTaskDescriptionString += argumentArray[i];
                                deadlineTaskDescriptionString += " ";
                            } else {
                                deadlineTaskDateAndTimeString += argumentArray[i];
                                deadlineTaskDateAndTimeString += " ";

                            }
                        }
                        System.out.println(deadlineTaskDateAndTimeString);
                        deadlineTaskDateAndTimeString = convertStringToDate(deadlineTaskDateAndTimeString);
                        System.out.println(deadlineTaskDateAndTimeString);
                        //use .trim() method to eliminate trailing white space
                        Task deadlineTask = new Deadline(deadlineTaskDescriptionString.trim(), deadlineTaskDateAndTimeString.trim());
                        store.add(deadlineTask);
                        printGotIt();
                        System.out.println(" " + deadlineTask.toString());
                        printNow(store.size());
                        //argument = myScanner.nextLine();
                    } catch (DukeException e) {
                        System.out.println(e);
                    } finally {
                        argument = myScanner.nextLine();
                    }
                } else if (argumentArray[0].equals("event")) {
                    try {
                        //catch empty desc error
                        if (argumentArray.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        //form back string , description stops at /at
                        //date time starts from /at
                        String eventTaskDescriptionString = "";
                        String eventTaskDateAndTimeString = "";
                        boolean createDesc = true;
                        //catch error of no specific date time after /at
                        if (argumentArray[argumentArray.length -1].matches("/at")) {
                            throw new DukeException("Oops, no specific duration supplied");
                        }

                        for (int i = 1; i < argumentArray.length; i++) {
                            if (argumentArray[i].equals("/at")) {
                                createDesc = false;
                            } else if (createDesc) {
                                eventTaskDescriptionString += argumentArray[i];
                                eventTaskDescriptionString += " ";
                            } else {
                                eventTaskDateAndTimeString += argumentArray[i];
                                eventTaskDateAndTimeString += " ";

                            }
                        }

                        eventTaskDateAndTimeString = convertStringToDate(eventTaskDateAndTimeString);
                        //use of .trim() to avoid trailing whitespace
                        Task eventTask = new Event(eventTaskDescriptionString.trim(), eventTaskDateAndTimeString.trim());
                        store.add(eventTask);
                        printGotIt();
                        System.out.println(" " + eventTask.toString());
                        printNow(store.size());
                        //argument = myScanner.nextLine();
                    } catch (DukeException e) {
                        System.out.println(e);
                    } finally {
                        argument = myScanner.nextLine();
                    }
                }else if (argumentArray[0].equals("delete")) {
                        try{
                            if (argumentArray.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description for delete command cannot be empty.");
                            }
                            int index = Integer.valueOf(argumentArray[1]) - 1;
                            //catch task number not in list error

                            Task removed = store.remove(index);
                            printNoted();
                            System.out.println(removed);
                            printNow(store.size());

                        }catch(IndexOutOfBoundsException e) {
                            System.out.println("invalid number not in current list");

                        }catch (DukeException e) {
                            System.out.println(e);
                        } finally {
                            argument = myScanner.nextLine();
                        }

                } else {
                    /*Task incomingTask = new Task(argument);
                    store.add(incomingTask);
                    System.out.println("added: " + argument);
                    argument = myScanner.nextLine();*/
                    try {
                        //handles error for not recognized command
                        throw new DukeException("☹  OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    } finally {
                        argument = myScanner.nextLine();
                    }
                }
            }
        }
        //test
        //exiting program
        System.out.println("Bye. Hope to see you again soon!");
        myScanner.close();
    }


    private static String convertStringToDate(String dateAndTimeString) {
        try {
            String[] arrayOfDateAndTime = dateAndTimeString.split(" ");
            /*for (String i : arrayOfDateAndTime) {
                System.out.println(i);

            }*/
            String date = arrayOfDateAndTime[0];
            String time = arrayOfDateAndTime[1];
            // d/mm/yyyy

            date = formatString(date);
            Integer timeInInt = Integer.valueOf(time);
            time = convertTime(timeInInt);
            dateAndTimeString = date + ", " + time;
            return dateAndTimeString;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("invalid date and time supplied: try d/mm/yyyy 0000 format");
        } catch (DukeException e ) {
            System.out.println(e);
        }

        return " ";
    }

    private static String convertTime(Integer convertedTime) throws  DukeException{
        String time = "";
        if (convertedTime == 0) {
            //midnight
            convertedTime = 12;
        } else if ( convertedTime > 0 &&  convertedTime < 1200) {
            int mins = convertedTime % 100;
            if (mins == 0 ) {
                convertedTime = convertedTime % 100;
            }
            time = convertedTime.toString() + "am";
        } else if (convertedTime < 2359 && convertedTime >= 1300) {
            //1300 / 100 = 13 % 12
            int hrs = (convertedTime / 100) % 12;
            int mins = convertedTime % 100;
            if (mins == 0) {
                time = String.valueOf(hrs) + "pm";
            } else {
                time = String.valueOf(hrs) + String.valueOf(mins) + "pm";
            }

        } else if (convertedTime < 1300 && convertedTime >= 1200 ) {
            int mins = convertedTime % 100;
            if (mins == 0) {
                time = "12pm";
            } else {
                time = "12" + String.valueOf(mins) + "pm";
            }

            time = "12" + String.valueOf(mins) + "pm";
        } else {
            throw new DukeException("invalid time entered");
        }

        return time;
    }

    private static String formatString(String date) {
        //[ 2 , 12, 2019]
        String[] newArr = date.split("/");

        String result = "";
        String num = newArr[0];
        num = getOrdinal(num);
        String month = newArr[1];
        month = getMonth(month);
        return num + " of " + month + " " + newArr[2];
    }

    private static String getMonth(String month) {
        String[] arrMonths = {" bye", "January", "February", "March", "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};
        int temp = Integer.valueOf(month);

        return arrMonths[temp];

    }

    private static String getOrdinal(String num) {
        int temp = Integer.valueOf(num);
        if (temp == 1 || temp == 21 || temp == 31) {
            return String.valueOf(temp) + "st";
        } else if (temp == 2 || temp == 22 ) {
            return String.valueOf(temp) + "nd";
        } else if (temp == 3 || temp == 23) {
            return String.valueOf(temp) + "rd";
        }else {
            return String.valueOf(temp) + "th";
        }
    }

    //print common methods to call
    static void printNoted() {
        System.out.println("Noted. I've removed this task:");
    }
    static void printGotIt() {
        System.out.println("Got it. I've added this task:");
    }

    static void printNow(int length) {
        System.out.println("Now you have " + length + " tasks in the list.");
    }


}
