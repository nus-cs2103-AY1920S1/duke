package Util;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Tasks.TaskList;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Exception.DukeException;
import Exception.IncorrectTaskNameException;
import Exception.EmptyDeadlineDescriptionException;
import Exception.EmptyEventDescriptionException;
import Exception.EmptyTodoDescriptionException;

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
    public void readInput() throws Exception, DukeException {
        Scanner scanner = new Scanner(System.in);
        showWelcome();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                showExit();
                break;
            }
            if(input.equals("list")) {
                tasksList.showAllTasks();
                continue;
            }
            if((input.length() > 5) && input.substring(0, 5).equals("done ")) {
                showDone(input);
                continue;
            }
            if((input.length() > 7) && input.substring(0, 7).equals("delete ")) {
                showDelete(input);
                continue;
            }
            if((input.length() > 5) && input.substring(0, 5).equals("find ")) {
                showFind(input);
                continue;
            }
            else {
                // just to catch wrong input
                try {
                    input.substring(0,4).equals("todo");
                    input.substring(0,8).equals("deadline");
                    input.substring(0,5).equals("event");
                } catch(Exception e) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                //handles the todo, deadline and event tasks
                System.out.println("Got it. I've added this task:");

                if(input.substring(0,4).equals("todo")) {
                    try {
                        input.substring(5,6);
                    } catch(Exception e) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, todo cannot be empty");
                    }
                    showTodo(input);
                    continue;
                }
                if(input.substring(0,8).equals("deadline")) {
                    try {
                        input.substring(9,10);
                    } catch (Exception e){
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    showDeadline(input);
                    continue;
                }
                if(input.substring(0,5).equals("event")) {
                    try {
                        input.substring(6,7);
                    } catch (Exception e){
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    showEvent(input);
                    continue;
                }
            }
        }
    }

    /**
     * shows Welcome message when called
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * shows Exit message when called
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * shows done message when called and performs rewrite on storage
     * and done on that task in TaskList
     */
    public void showDone(String input) throws Exception{
        int itemIndex = Integer.parseInt(input.substring(5)) - 1;
        // close if item number don't exist
        if((itemIndex + 1) > tasksList.getTaskList().size()) {
            System.out.println("failed to done item, please try again");
        } else {
            tasksList.markTaskDone(itemIndex);
            storage.rewriteWriter(tasksList);
        }
    }

    /**
     * shows delete message when called and performs rewrite on storage
     * and delete that task from TaskList
     */
    public void showDelete(String input) throws Exception{
        int itemIndex = Integer.parseInt(input.substring(7)) - 1;
        if((itemIndex + 1) > tasksList.getTaskList().size()) {
            System.out.println("failed to delete item, please try again.");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasksList.getTaskList().get(itemIndex));
            tasksList.getTaskList().remove(itemIndex);
            storage.rewriteWriter(tasksList);
            System.out.println("Now you have " + tasksList.getTaskList().size() + " in the list.");
        }
    }

    /**
     * shows todo message when called and performs read on storage
     * and appends a todo task in TaskList
     */
    public void showTodo(String input){
        String inputAdd = input.substring(5);
        Todo todo = new Todo(inputAdd, "");
        tasksList.addTask(todo);
        storage.writeTodo(todo);
        System.out.println("  " + todo);
    }

    /**
     * shows deadline message when called and performs read on storage
     * and appends a deadline task in TaskList
     */
    public void showDeadline(String input) throws Exception{
        String inputAddArr[] = input.split(" /by ");
        String inputAdd1 = inputAddArr[0].substring(9);
        String inputAdd2 = readDate(inputAddArr[1]);
        Deadline deadline = new Deadline(inputAdd1, inputAdd2);
        tasksList.addTask(deadline);
        storage.writeDeadline(deadline);
        System.out.println("  " + deadline);
    }

    /**
     * shows event message when called and performs read on storage
     * and appends a event task in TaskList
     */
    public void showEvent(String input) throws Exception{
        String inputAddArr[] = input.split(" /at ");
        String inputAdd1 = inputAddArr[0].substring(6);
        String inputAdd2 = readDate(inputAddArr[1]);
        Event event = new Event(inputAdd1, inputAdd2);
        tasksList.addTask(event);
        storage.writeEvent(event);
        System.out.println("  " + event);
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

    public void showFind(String input) throws Exception {
        String toMatch = input.substring(5).trim();
        TaskList tempTaskList = new TaskList(new ArrayList<Task>());
        for(int i = 0; i < tasksList.getTaskList().size(); i++) {
            String desc = tasksList.getTaskList().get(i).getDescription();
            if((toMatch.length() < desc.length()) && desc.contains(toMatch)) {
                tempTaskList.addTask(tasksList.getTaskList().get(i));
            } else {
                continue;
            }
        }
        for(int i = 0; i < tempTaskList.getTaskList().size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tempTaskList.getTaskList().get(i));
        }
    }
}
