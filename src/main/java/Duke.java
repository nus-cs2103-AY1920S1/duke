import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean is_bye = false;
    private  static  ArrayList<Task> tasks = new ArrayList<Task>() ;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scan = new Scanner(System.in);

        while(!is_bye){
            System.out.print("input command here: ");
            String input = scan.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to " +
                                "see you again soon!");
                is_bye = true;

            } else if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++){
                    Task task = tasks.get(i);
                    int count = i + 1;
                    System.out.println(count + "." + task.toString());
                }

            } else if(input.indexOf("done") != -1){

                String[] words = input.split(" ");
                int index =Integer.parseInt(words[1]);
                Task t = tasks.get(index-1);
                t.completed();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("   [✓] "+ t.getTask());

            } else if(input.indexOf("deadline") != -1){
                String new_str = input.replace("deadline"," ");
                String[] words = new_str.split("/");
                String taskName = words[0].replace("  ", "");
                String deadLine = words[1].replace("by ", "");
                Deadline dl = new Deadline(deadLine, taskName);
                tasks.add(dl);
                dl.printAddedDeadline(tasks.size());

            } else if(input.indexOf("event") != -1){
                String new_str = input.replace("event"," ");
                String[] words = new_str.split("/");
                String taskName = words[0].replace("  ", "");
                String timeDate = words[1].replace("at ", "");
                Events event = new Events(taskName, timeDate);
                tasks.add(event);
                event.printAddedEvent(tasks.size());
            }else if(input.indexOf("todo") != -1){
                String new_str = input.replace("todo", "");
                String taskName = new_str;
                System.out.println(taskName);
                Todo td = new Todo(taskName);
                tasks.add(td);
                td.printAddedTodo(tasks.size());
            }
            else{
                tasks.add(new Task(input));
                System.out.println("added: " + input);

            }
        }

    }
}
