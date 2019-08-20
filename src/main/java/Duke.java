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
                for(int i = 0; i < list.size(); i++){
                    System.out.println((i+1)+"."+list.get(i));
                }
            }
            else if(echo.startsWith("done ")){
                String[] echoSplit = echo.split(" ");
                int item = Integer.parseInt(echoSplit[1]) - 1;
                list.get(item).setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(item));
            }
            else{
                len++;
                if(echo.startsWith("todo ")){
                    list.add(new Todo(echo.substring(5), len));
                }
                else if(echo.startsWith("deadline ")){
                    echo = echo.substring(9);
                    String[] echoSplit = echo.split(" /by ");
                    list.add(new Deadline(echoSplit[0], len, echoSplit[1]));
                }
                else if(echo.startsWith("event ")){
                    echo = echo.substring(6);
                    String[] echoSplit = echo.split(" /at ");
                    list.add(new Event(echoSplit[0], len, echoSplit[1]));
                }
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(len-1));
                System.out.println("Now you have "+len+" tasks in the list.");
            }
            echo = s.nextLine();
        }
    }
}
