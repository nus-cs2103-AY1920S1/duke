import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner s = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int len = 0;
        String echo = s.nextLine();
        while(true){
            if(echo.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(echo.equals("list")){
                for(String str : list){
                    System.out.println(str);
                }
                echo = s.nextLine();
            }
            else{
                len++;
                list.add(len + ". "+echo);
                System.out.println("added: "+echo);
                echo = s.nextLine();
            }
        }
    }
}
