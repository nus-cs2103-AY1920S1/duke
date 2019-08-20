import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {

    public static void main(String[] args) {
        String startingMessage = "Hello! I'm Duke\nWhat can I do for you?" ;
        System.out.println(startingMessage);
        List <String> listOfStuff = new ArrayList <String>();
        
        boolean flag = true;
        while(flag){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }else if(line.equals("list")){
                for (int i = 0; i < listOfStuff.size(); i++) {
                    Integer number = i + 1;
                    String message = number + ". " + listOfStuff.get(i);
                    System.out.println(message);
                  }
            }else{
                listOfStuff.add(line);
                System.out.println(line);
            }
        }
    }
}