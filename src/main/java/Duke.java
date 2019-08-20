import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String startingMessage = "Hello! I'm Duke\nWhat can I do for you?" ;
        System.out.println(startingMessage);
        List <Task> listOfStuff = new ArrayList <Task>();
        
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
            }else if(line.contains("done")){
                for (int i = 0; i < listOfStuff.size(); i++) {
                    Integer indexOfTask = i + 1;
                    String stringFromUser = line.replaceAll("\\D+","");
                    Integer indexFromUser = Integer.parseInt(stringFromUser);
                    if(indexOfTask == indexFromUser ){
                        Task currentTask = listOfStuff.get(i);
                        currentTask.setStatus(true);
                        String message = "Nice! I've marked this task as done:\n" + currentTask;
                        System.out.println(message);
                    }
                  }
            }else{
                Task newTask = new Task (false, line);
                listOfStuff.add(newTask);
                System.out.println(line);
            }
        }
    }
}