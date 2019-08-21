import java.util.*;
public class Duke {
    private static void print(String message){
        System.out.println(">>"+message);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc= new Scanner(System.in);
        while(sc.hasNext()){
            String input=sc.next();
            if(input.equals("bye")) {
                break;
            }else{
                print(input);
            }
        }
        String exitmsg="Bye. Hope to see you again.";
        print(exitmsg);
    }
}
