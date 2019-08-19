import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/
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
                case "todo":
                    taskName = taskName.substring(5);
                    task = new Todo (taskName);
                    addTaskIn(task,tasks);
                    break;

                case "deadline":
                    taskName = taskName.substring(9);
                    date = taskName.split("/");
                    task = new Deadline (taskName,date[1]);
                    addTaskIn(task,tasks);
                    break;

                case "event":
                    taskName = taskName.substring(6);
                    date = taskName.split("/");
                    task = new Event (taskName, date[1]);
                    addTaskIn(task,tasks);
                    break;
                default:
                    //
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

    /*public static void echo(String str) {
        System.out.println("    ____________________________________________________________");
        System.out.println(str);
        System.out.println("    ____________________________________________________________");
    }*/

    //Add new task
    public static void addTaskIn(Task task,ArrayList<Task> tasks) {
        System.out.println("    ____________________________________________________________");
        tasks.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
        //System.out.println("     added: "+task.getTaskName());
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

}


