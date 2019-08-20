import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message;
        String[] myList = new String[100];
        int idx = 0;

        String upperLine = "____________________________________________________________\n";
        String greet = "Hello! I'm Duke\n"
        + "What can I do for you?\n";

        String lowerLine = "____________________________________________________________\n";
        greet = upperLine + greet + lowerLine;
        System.out.println(greet);
        while(true){
            String cmd = sc.nextLine();
            if(cmd.equals("bye")){
                message = upperLine + "Bye. Hope to see you again soon!\n" + lowerLine;
                System.out.println(message);
                break;
                //below: add and list feature
            }else{
                if(!cmd.equals("list")){
                    myList[idx] = cmd;
                    idx++;
                    message = upperLine + "add: " + cmd + "\n" + lowerLine;
                    System.out.println(message);
                }else{
                    System.out.print(upperLine);
                    for(int i = 0; i < idx; i++){
                        message = myList[i];
                        System.out.println((i+1) + ": " + message);
                    }
                    System.out.println(lowerLine);
                }
            }
        }
    }
}