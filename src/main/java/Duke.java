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

                for (int i = 0; i < tasks.size(); i++){
                    Task task = tasks.get(i);
                    int count = i + 1;
                    String box;

                    if(task.isDone() == true) {
                        box = "[✓]";
                    }else{
                        box = "[✗]";
                    }
                    System.out.println(count + "." + box + " " + task.getTask());
                }

            } else if(input.indexOf("done") != -1){

                String[] words = input.split(" ");
                int index =Integer.parseInt(words[1]);
                Task t = tasks.get(index-1);
                t.completed();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("   [✓] "+ t.getTask());

            } else{

                tasks.add(new Task(input));
                System.out.println("added: " + input);

            }
        }

    }
}
