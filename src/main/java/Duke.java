import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final String lineSpace = "_______________________________\n";
        String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println(startMessage);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList();
        String taskName;
        Task task;
        while(sc.hasNext()){
            String userCmd = sc.next();
            if(userCmd.equals("bye")){
                System.out.println(lineSpace + "Bye. Hope to see you again soon!\n" + lineSpace);
                break;
            }
            switch(userCmd) {
                case "list":
                    System.out.println(lineSpace + "Here are the tasks in your list:");
                    for(int i=0; i < list.size(); i++){
                        System.out.println(i+1 + "." + list.get(i));
                    }
                    System.out.print(lineSpace);
                    break;
                case "todo":
                    taskName = sc.nextLine();
                    taskName = taskName.trim();
                    task = new Task(taskName);
                    list.add(task);
                    System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                            + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                    break;
                case "deadline":
                    taskName = sc.nextLine();
                    String[] userWords = taskName.trim().split("/by");
                    task = new Deadline(userWords[0].trim(), userWords[1].trim());
                    list.add(task);
                    System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                            + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                    break;
                case "event":
                    taskName = sc.nextLine();
                    userWords = taskName.trim().split("/at");
                    task = new Event(userWords[0].trim(), userWords[1].trim());
                    list.add(task);
                    System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                            + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                    break;
                case "done":
                    int taskNo = sc.nextInt();
                    list.get(taskNo-1).markAsDone();
                    System.out.println(lineSpace + "Nice! I've marked this task as done:\n"
                            + list.get(taskNo-1) + "\n" + lineSpace);
                    break;
                default:
                    list.add(new Task(userCmd));
                    System.out.println(lineSpace + "added: " + userCmd + "\n" + lineSpace);
                //Level-1 code
                //System.out.println(lineSpace + "\n" + userInput + "\n" + lineSpace);
            }
        }

    }
}
