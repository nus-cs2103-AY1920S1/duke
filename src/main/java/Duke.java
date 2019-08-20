import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String startingMessage = "Hello! I'm Duke\nWhat can I do for you?" ;
        System.out.println(startingMessage);

        
        boolean flag = true;

        while(flag){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }else{
                System.out.println(line);
            }
        }
    }
}