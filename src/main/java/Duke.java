import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        runUI(tasks);

    }

    public static void runUI(ArrayList<Task> tasks){
        String taskName;
        Scanner scanner = new Scanner(System.in);
        String [] command;
        String [] date;
        Task task;
        boolean isContinue = true;

        displayWelcome();
        while(isContinue) {
            taskName = scanner.nextLine();      //the whole user command
            command = taskName.split(" "); //the different parts pf the command
            switch (command[0]) {               //according to the first part of the command
                case "bye":
                    displayQuit();
                    isContinue = false;
                    break;
                case "list":
                    try {
                        list(tasks);
                    } catch (RuntimeException exList) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! There is no task in your list.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }

                case "done":
                    try{
                        done(tasks, Integer.parseInt(command[1])-1);
                    } catch (IndexOutOfBoundsException exDone) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! There is no task "+Integer.parseInt(command[1])+" in the list.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }

                case "delete":
                    try{
                        deleteTask(tasks, Integer.parseInt(command[1])-1);
                    } catch (IndexOutOfBoundsException exDelete) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! There is no task "+Integer.parseInt(command[1])+" in the list.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }

                case "todo":
                    try {
                        taskName = taskName.trim().substring(5);         //it is very important to trim the space at the end
                        task = new Todo (taskName);
                        addTaskIn(task,tasks);

                    } catch (StringIndexOutOfBoundsException exTodo) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }


                case "deadline":
                    try {
                        taskName = taskName.trim().substring(9);
                        date = taskName.split("/");
                        task = new Deadline (date[0],date[1].substring(3));
                        addTaskIn(task,tasks);
                    } catch (StringIndexOutOfBoundsException exDeadline1){
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } catch (IndexOutOfBoundsException exDeadline2){
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The end time of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } finally {
                        break;
                    }

                case "event":
                    try {
                        taskName = taskName.trim().substring(6);
                        date = taskName.split("/");
                        task = new Event (date[0], date[1].substring(3));
                        addTaskIn(task,tasks);
                    } catch (StringIndexOutOfBoundsException exEvent1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } catch (IndexOutOfBoundsException exEvent2){
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The start time of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }

                default:
                    //
                    try {
                        throw new InvalidCommandException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (RuntimeException exDefault1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println(exDefault1.getMessage());
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }

            }
        }
    }

    public static void displayWelcome(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void displayQuit(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }



    //Add new task
    public static void addTaskIn(Task task,ArrayList<Task> tasks) {

        if(task == null ){
            throw new StringIndexOutOfBoundsException();
        }

        System.out.println("    ____________________________________________________________");
        tasks.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
        System.out.println("     Now you have "+tasks.size()+" tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    public static void list (ArrayList<Task> tasks){
        if(tasks.size() == 0){
            throw new RuntimeException();
        }
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("     "+(i+1)+"."+"["+tasks.get(i).getType()+"]"+"["+ tasks.get(i).getStatus()+"] "+tasks.get(i).getTaskName()+" ");
            switch (tasks.get(i).getType()){
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    //Forced type casting
                    Deadline dtask = (Deadline) tasks.get(i);
                    System.out.println("(by: "+dtask.getBy()+")");
                    break;
                case 'E':
                    Event etask = (Event) tasks.get(i);
                    System.out.println("(by: "+etask.getStart()+")");
                    break;
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void done(ArrayList<Task> tasks, int index) {
        if (index > tasks.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(index).setStatus();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       ["+tasks.get(index).getStatus()+"] "+tasks.get(index).getTaskName());
        System.out.println("    ____________________________________________________________");
    }

    //delate a task from list
    public static void deleteTask(ArrayList<Task> tasks,int index) {
        if (index > tasks.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.remove(index);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
        System.out.println("     Now you have "+tasks.size()+" tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

}



    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/