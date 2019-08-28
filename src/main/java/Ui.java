import java.util.ArrayList;
import java.util.Scanner;

public class Ui{
    String userInput;
    Scanner scanner = new Scanner(System.in);

    //////////////  read method   ////////////
     String read() {
         userInput = scanner.nextLine();
         return userInput;
    }
    ////////////   End of READ method   ///////////////

    ///////////// print method for delete //////////////////
    void print_delete(String instruct, int no_of_task, Task t){
         if(instruct.contains("Delete")){
             System.out.println("Noted. I've removed this task:");
             System.out.println("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.timeframe + ")");
             System.out.println("Now you have " + no_of_task + " tasks in the list.");
         }
    }
    ////////////// End of "delete" print method //////////////

    ///////////////  print method for find  /////////////////
    void print_find(int num, ArrayList<Task> taskList, int i, int n){
         if(n==1){
             System.out.println("Here are the matching tasks in your list: ");
         }
         else{
             System.out.println(num + ".[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " " + taskList.get(i).timeframe);
         }
    }
    /////////////// End of "find" print method /////////////
}

