package Util;

import Exception.DukeException;
import Tasks.*;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Handles the User Interface for the program where the user will interact with
 * by inputting data into the console. The class will then take this input and
 * interact with the associated TaskList and Storage classes
 */
public class Ui {

    private Parser parser;
    private TaskList tasksList;
    private Storage storage;

    /**
     * To store the several suffixed that will be used in a month starting from
     * 0 ending at 31. (i.e 2nd, 11th, 23rd)
     */
    static String[] suffixes =
            { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                    "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    "th", "st" };

    public Ui(TaskList taskList, Storage storage) {
        this.tasksList = taskList;
        this.storage = storage;
    }

    /**
     * take in input from user and then perform relevant methods to read/write
     * into local storage and save into TaskList
     * Takes in only the follow inputs (bye, list, done, delete, todo, deadline, event)
     */
    public String readInput(String input) throws Exception, DukeException {
        String toReturn = "";
            if(input.equals("bye")) {
               toReturn += showExit();
                return toReturn;
            }
            if(input.equals("list")) {
                toReturn += tasksList.showAllTasks();
                return toReturn;
            }
            if((input.length() > 5) && input.substring(0, 5).equals("done ")) {
                toReturn += showDone(input);
                storage.rewriteWriter(tasksList);
                return toReturn;
            }
            if((input.length() > 7) && input.substring(0, 7).equals("delete ")) {
                toReturn += showDelete(input);
                storage.rewriteWriter(tasksList);
                return toReturn;
            }
            if((input.length() > 5) && input.substring(0, 5).equals("find ")) {
                toReturn += showFind(input);
                return toReturn;
            }
            else {
                //handles the todo, deadline and event tasks
                if(input.substring(0,4).equals("todo")) {
                    try {
                        input.substring(5,6);
                    } catch(Exception e) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, todo cannot be empty");
                    }
                    toReturn += showTodo(input);
                }
                if(input.substring(0,8).equals("deadline")) {
                    try {
                        input.substring(9,10);
                    } catch (Exception e){
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    toReturn += showDeadline(input);
                }
                if(input.substring(0,5).equals("event")) {
                    try {
                        input.substring(6,7);
                    } catch (Exception e){
                        throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
                    }
                    toReturn += showEvent(input);
                }
            }
        if(toReturn.equals("")) toReturn += ("OOPS!!! I don't know what that means");
        return toReturn;
    }

    /**
     * shows Welcome message when called
     */
    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * shows Exit message when called
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * shows done message when called and performs rewrite on storage
     * and done on that task in TaskList
     */
    public String showDone(String input) throws Exception{
        int itemIndex = Integer.parseInt(input.substring(5)) - 1;
        // close if item number don't exist
        if((itemIndex + 1) > tasksList.getTaskList().size()) {
            return ("failed to done item, please try again");
        } else {
            return tasksList.markTaskDone(itemIndex);
        }
    }

    /**
     * shows delete message when called and performs rewrite on storage
     * and delete that task from TaskList
     */
    public String showDelete(String input) throws Exception{
        int itemIndex = Integer.parseInt(input.substring(7)) - 1;
        if((itemIndex + 1) > tasksList.getTaskList().size()) {
            return ("failed to delete item, please try again.");
        } else {
            return tasksList.deleteTask(itemIndex);
        }
    }

    /**
     * shows todo message when called and performs read on storage
     * and appends a todo task in TaskList
     */
    public String showTodo(String input){
        String inputAdd = input.substring(5);
        Todo todo = new Todo(inputAdd, "");
        tasksList.addTask(todo);
        storage.writeTodo(todo);
        String toReturn = "";
        toReturn += ("Got it. I've added this task:\n");
        toReturn += ("  " + todo);
        return toReturn;
    }

    /**
     * shows deadline message when called and performs read on storage
     * and appends a deadline task in TaskList
     */
    public String showDeadline(String input) throws Exception{
        String inputAddArr[] = input.split(" /by ");
        String inputAdd1 = inputAddArr[0].substring(9);
        String inputAdd2 = readDate(inputAddArr[1]);
        Deadline deadline = new Deadline(inputAdd1, inputAdd2);
        tasksList.addTask(deadline);
        storage.writeDeadline(deadline);
        String toReturn = "";
        toReturn += ("Got it. I've added this task:\n");
        toReturn += ("  " + deadline);
        return toReturn;
    }

    /**
     * shows event message when called and performs read on storage
     * and appends a event task in TaskList
     */
    public String showEvent(String input) throws Exception{
        String inputAddArr[] = input.split(" /at ");
        String inputAdd1 = inputAddArr[0].substring(6);
        String inputAdd2 = readDate(inputAddArr[1]);
        Event event = new Event(inputAdd1, inputAdd2);
        tasksList.addTask(event);
        storage.writeEvent(event);
        String toReturn = "";
        toReturn += ("Got it. I've added this task: \n");
        toReturn += ("  " + event);
        return toReturn;
    }

    /**
     * Reads an input in the stipulated format and returns a string of the
     * expected date format
     *
     * @param input format of date input (i.e 2/12/2019 1800)
     * @return a string of a formatted Date. (i.e 2nd of December 2019, 1800hours)
     */
    public String readDate(String input) throws Exception{
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date d = formatter.parse(input);
        DateFormatSymbols dfs = new DateFormatSymbols();
        System.out.println(d);
        String[] dateArr = d.toString().split(" ");
        String day = ((d.getDay() + 1) + suffixes[d.getDay() + 1]);
        String month = (dfs.getMonths()[d.getMonth()]);
        String year = (dateArr[5]);
        String incorrectTime = dateArr[3].substring(0, 5).replace(":", "").trim();
        return day + " of " + month + " " + year + ", " + incorrectTime + "hours";
    }

    /**
     * Shows a list of all tasks holding the same characters of the input.
     * Excludes all excess spacing after last non-space character
     *
     * @param input String for the method to check against the stored taskList
     */
    public String showFind(String input) throws Exception {
        String toMatch = input.substring(5).trim();
        String toReturn = "";
        TaskList tempTaskList = new TaskList(new ArrayList<Task>());
        for(int i = 0; i < tasksList.getTaskList().size(); i++) {
            String desc = tasksList.getTaskList().get(i).getDescription();
            if(desc.contains(toMatch)) {
                tempTaskList.addTask(tasksList.getTaskList().get(i));
            } else {
                continue;
            }
        }
        for(int i = 0; i < tempTaskList.getTaskList().size(); i++) {
            toReturn += ((i + 1) + ".");
            toReturn += tempTaskList.getTaskList().get(i);
        }
        return toReturn;
    }
}
