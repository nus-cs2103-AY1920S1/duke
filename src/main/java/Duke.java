import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean is_bye = false;
    private  static  ArrayList<String> tasks = new ArrayList<String>() ;

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

            }else if(input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++){
                    String task = tasks.get(i);
                    int count = i + 1;
                    System.out.println(count + ". " + task);
                }
            } else{
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }

    }
}
