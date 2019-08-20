import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static boolean isNum(String str){
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner s = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int len = 0;
        String echo = s.nextLine();
        while(true){
            if(echo.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(echo.equals("list")){
                for(Task t : list){
                    System.out.println(t);
                }
            }
            else if(echo.startsWith("done ")){
                String[] echoSplit = echo.split(" ");
                if(echoSplit.length == 2 && isNum(echoSplit[1])){
                    int item = Integer.parseInt(echoSplit[1]) - 1;
                    list.get(item).setAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(item));
                }
                else{
                    len++;
                    list.add(new Task(echo, len));
                    System.out.println("added: "+echo);
                }
            }
            else{
                len++;
                list.add(new Task(echo, len));
                System.out.println("added: "+echo);
            }
            echo = s.nextLine();
        }
    }
}
