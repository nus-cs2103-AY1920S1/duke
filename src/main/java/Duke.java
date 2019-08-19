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
                default:
                    Task task = new Task (taskName);
                    addTaskIn(task,tasks);

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
        System.out.println("     added: "+task.getTaskName());
        System.out.println("    ____________________________________________________________");
    }

    public static void list (ArrayList<Task> tasks){
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     "+(i+1)+"."+"["+ tasks.get(i).getStatus()+"]"+tasks.get(i).getTaskName());
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


