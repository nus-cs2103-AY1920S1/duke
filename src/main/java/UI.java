import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents the items that affect the user interface of the program and handles the interaction with the user.
 */
public class UI {

    protected boolean isRunning;
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a UI object.
     */
    public UI() {
        this.isRunning = true;
    }

    /**
     * Prints out a welcome statement when users first open the Duke program.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints out a goodbye statement when the users enter bye, before the program ends.
     */
    public void goodbye() {

        System.out.println("Bye. Hope to see you again soon!");
        this.isRunning = false;

    }

    /**
     * Prompts the user to enter into the program.
     *
     * @return the string that the user entered.
     */
    public String promptEntry() {
        return sc.next();
    }

    /**
     * Returns the formatted date and time. If it is in the wrong format, it will return the string entered.
     *
     * @param dateAndTime String value of the date and time.
     * @return the formatted date and time, if it is in the correct format of dd/MM/yyyy hhmm
     */
    public static String getFormattedDate(String dateAndTime) {
        String result = dateAndTime;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(dateAndTime);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String ordinalIndicator;

            int int_day = Integer.parseInt(day);
            if (int_day >= 11 && int_day <= 13) {
                ordinalIndicator = "th";
            } else if (int_day % 10 == 1) {
                ordinalIndicator = "st";
            } else if (int_day % 10 == 2) {
                ordinalIndicator = "nd";
            } else if (int_day % 10 == 3) {
                ordinalIndicator = "rd";
            } else {
                ordinalIndicator = "th";
            }

            result = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Handles the commands entered by the user.
     *
     * @param command that the user entered.
     * @param taskList the current tasklist, to be updated with every command.
     */
    public void handleCommand (String command, TaskList taskList) {

        while (!command.equals("bye")) {

            try {
                switch (command) {

                    case "list":
                        taskList.printList();
                        break;

                    case "todo":
                        String task = sc.nextLine().trim();
                        if(!task.isEmpty()) {
                            ToDo newTodo = new ToDo(task);
                            taskList.addTask(newTodo);
                        } else {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;

                    case "deadline":
                        String wholeTask = sc.nextLine().trim();
                        int index = wholeTask.indexOf('/');
                        if(index > 0) {
                            //what the task is
                            String description = wholeTask.substring(0, index).trim();
                            //when it is due by
                            String date = wholeTask.substring(index + 4).trim();
                            String f = getFormattedDate(date);
                            if(description.isEmpty() || date.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                        " It must be in the format <description> /by <date/time> ");
                            } else {
                                Deadline newDeadlineTask = new Deadline(description, f);
                                taskList.addTask(newDeadlineTask);
                            }
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                    " It must be in the format <description> /by <date/time> ");
                        }
                        break;

                    case "event":
                        String eventAndDate = sc.nextLine();
                        int index2 = eventAndDate.indexOf('/');
                        if(index2 > 0) {
                            //what the task is
                            String eventDescr = eventAndDate.substring(0, index2).trim();
                            //when it is due by
                            String dateAndTime = eventAndDate.substring(index2 + 4).trim();
                            String f1 = getFormattedDate(dateAndTime);
                            if(eventDescr.isEmpty() || dateAndTime.isEmpty()) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                        " It must be in the format <description> /at <start and end of specific time> ");
                            } else {
                                Event newEventTask = new Event(eventDescr, f1);
                                taskList.addTask(newEventTask);
                            }
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                    " It must be in the format <description> /at <start and end of specific time> ");
                        }
                        break;

                    case "delete":
                        int taskToBeDeleted = sc.nextInt() -1;
                        taskList.deleteTask(taskToBeDeleted);
                        break;

                    case "done":
                        int taskNumber = sc.nextInt() - 1;
                        taskList.completeTask(taskNumber);
                        break;

                    case "find":
                        String keyword = sc.nextLine().trim();
                        ArrayList<Task> found = taskList.find(keyword);
                        int n = 1;

                        if(found.isEmpty()){
                            System.out.println("None found");
                        } else {
                            for (Task item : found) {
                                System.out.println(n + "." + item);
                                n++;
                            }
                        }
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }

            } catch (DukeException e) {
                System.out.println(e);
            }

            command = promptEntry();

        }
        //code exits here when the user enters the command "bye"
    }




}
