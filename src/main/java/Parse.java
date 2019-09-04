import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parse {

    public static void scan(Duke duke){
        Scanner scanner = new Scanner(System.in);
        String taskName = "";
        try {
            taskName = scanner.nextLine();      //the whole user command
        } catch (NoSuchElementException nex) {
            System.out.println("     Oops! Target file does not exist!");
        }


        while(duke.getParse().getCommand(duke, taskName, true)) {
            try {
                taskName = scanner.nextLine();
            } catch (NoSuchElementException nex) {
                System.out.println("     Oops! No such thing!");
            }

        }
    }

    public  boolean getCommand(Duke duke, String taskName, Boolean b){
        String key;
        String [] command;
        String [] buffer;
        String date;
        Task task;
        ArrayList<Integer>  searchIndexResult;
        TaskList searchTaskResult = new TaskList();
        int index = duke.getTaskList().size()+1;
        command = taskName.split(" "); //the different parts pf the command
        switch (command[0]) {               //according to the first part of the command
            case "bye":
                duke.getUi().displayQuit();
                return false;
            case "list":
                try {
                    duke.getTaskList().list();
                } catch (RuntimeException exList) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! There is no task in your list.");
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }

            case "done":
                try{
                    done(duke, Integer.parseInt(command[1])-1,true);
                } catch (IndexOutOfBoundsException exDone) {
                    if (b) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      OOPS!!! There is no task "+Integer.parseInt(command[1])+" in the list.");
                        System.out.println("    ____________________________________________________________");
                    }
                } finally {
                    return true;
                }

            case "delete":
                try{
                    duke.getTaskList().deleteTask(Integer.parseInt(command[1])-1);
                } catch (IndexOutOfBoundsException exDelete) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! There is no task "+Integer.parseInt(command[1])+" in the list.");
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }

            case "todo":
                try {
                    taskName = taskName.trim().substring(5);         //it is very important to trim the space at the end
                    task = new Todo (taskName, duke.getTaskList().size()+1);
                    duke.getTaskList().addTaskIn(task, b);

                } catch (StringIndexOutOfBoundsException exTodo) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }


            case "deadline":
                try {
                    Deadline dtask;
                    //remove spaces front/end and get rid of "deadline"
                    taskName = taskName.trim().substring(9);
                    //seperate task name and date
                    buffer = taskName.trim().split("/");
                    //get rid of task name (buffer[0])
                    date = taskName.trim().substring(buffer[0].length()).substring(3);
                    //capsulate in task
                    if (date.trim().matches("\\d{1,2}[/.]\\d{1,2}[/.]\\d{4}\\s\\d{3,4}")){
                        dtask = new Deadline(buffer[0], index, date, stringToDate(date));
                    } else {
                        dtask = new Deadline (buffer[0],index,buffer[1].substring(3));
                    }
                    duke.getTaskList().addTaskIn(dtask, b);
                } catch (StringIndexOutOfBoundsException exDeadline1){
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("    ____________________________________________________________");

                } catch (IndexOutOfBoundsException exDeadline2){
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! The end time of a deadline cannot be empty.");
                    System.out.println("    ____________________________________________________________");

                } finally {
                    return true;
                }

            case "event":
                try {
                    Event etask;
                    //event return book /by 2/12/2019 1800
                    //remove spaces front/end and get rid of "deadline"
                    taskName = taskName.trim().substring(6);
                    //seperate task name and date
                    //return book /by 2/12/2019 1800
                    buffer = taskName.trim().split("/");
                    //get rid of task name (buffer[0])
                    date = taskName.trim().substring(buffer[0].length()).substring(3);
                    //capsulate in task
                    if (date.trim().matches("\\d{1,2}[/.]\\d{1,2}[/.]\\d{4}\\s\\d{3,4}")){
                        etask = new Event(buffer[0],index, date, stringToDate(date));
                    } else {
                        etask = new Event (buffer[0],index,buffer[1].substring(3));
                    }
                    duke.getTaskList().addTaskIn(etask, b);

                } catch (StringIndexOutOfBoundsException exEvent1) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! The description of a event cannot be empty.");
                    System.out.println("    ____________________________________________________________");

                } catch (IndexOutOfBoundsException exEvent2){
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! The start time of a event cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }
            case "find":
                try {
                    //get key word
                    key = taskName.substring(command[0].length()).trim();
                    //System.out.println(key);
                    //System.out.println("ready to search "+key);
                    duke.getTaskList().find(key);
                } catch(StringIndexOutOfBoundsException sex) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      OOPS!!! Key is invalid");
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }

            default:
                //
                try {
                    throw new InvalidCommandException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (RuntimeException exDefault1) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println(exDefault1.getMessage());
                    System.out.println("    ____________________________________________________________");
                } finally {
                    return true;
                }

        }
    }

    public  void done(Duke duke, int index, boolean isDisplay) {
        if (index > duke.getTaskList().getTasks().size()-1) {
            throw new IndexOutOfBoundsException();
        }
        duke.getTaskList().getTasks().get(index).setStatus();
        if (isDisplay) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.println("       ["+duke.getTaskList().getTasks().get(index).getStatus()+"] "+duke.getTaskList().getTasks().get(index).getTaskName());
            System.out.println("    ____________________________________________________________");
        }

    }




    public  GregorianCalendar stringToDate (String str) throws ParseException {
        //2/12/20191800 to date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyyHHmm");
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(simpleDateFormat.parse(str));
        return date;
    }
}
