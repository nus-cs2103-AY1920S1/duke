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
            taskName = scanner.nextLine();
            command = taskName.split(" ");
            switch (command[0]) {
                case "bye":
                    displayQuit();
                    isContinue = false;
                    break;
                case "list":
                    list(tasks);
                    break;
                case "done":
                    done(tasks, (int)command[1].charAt(0));
                    break;
                case "delete":
                    deleteTask(tasks, (int)command[1].charAt(0));
                    break;
                case "todo":
                    try {
                        taskName = taskName.substring(5);
                        task = new Todo (taskName);
                        addTaskIn(task,tasks);

                    } catch (StringIndexOutOfBoundsException ex1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } finally {
                        break;
                    }


                case "deadline":
                    try {
                        taskName = taskName.substring(9);
                        date = taskName.split("/");
                        task = new Deadline (date[0],date[1].substring(3));
                        addTaskIn(task,tasks);
                    } catch (StringIndexOutOfBoundsException ex2){
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } catch (IndexOutOfBoundsException ex3){
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The end time of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } finally {
                        break;
                    }

                case "event":
                    try {
                        taskName = taskName.substring(6);
                        date = taskName.split("/");
                        task = new Event (date[0], date[1].substring(3));
                        addTaskIn(task,tasks);
                    } catch (StringIndexOutOfBoundsException ex4) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________");

                    } catch (IndexOutOfBoundsException ex5){
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
                    } catch (RuntimeException ex6) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println(ex6.getMessage());
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
        System.out.println("    ____________________________________________________________");
        tasks.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
        System.out.println("     Now you have "+tasks.size()+" tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    public static void list (ArrayList<Task> tasks){
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
        tasks.get(index-49).setStatus();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       ["+tasks.get(index-49).getStatus()+"] "+tasks.get(index-49).getTaskName());
        System.out.println("    ____________________________________________________________");
    }

    //delate a task from list
    public static void deleteTask(ArrayList<Task> tasks,int taskIndex) {
        Task task = tasks.remove(taskIndex-49);
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