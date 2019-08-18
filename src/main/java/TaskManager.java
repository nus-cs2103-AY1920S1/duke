import java.util.Scanner;
import java.util.ArrayList;

class TaskManager {
  private Scanner sc;
  private ArrayList<String> list;

  public TaskManager() {
    sc = new Scanner(System.in);
    list = new ArrayList<String>();
  }

  public void initializeTasks() {
    while(sc.hasNextLine()) {
      String input = sc.nextLine();
      instantiateTask(input);
      if(input.equals("bye")) {
        break;
      }
    }
  }

  private void instantiateTask(String input) {
    switch(input) {
      case "list" :
        for(int i = 0; i < list.size(); i++) {
          System.out.println((i + 1) + ". " + list.get(i));
        }
        break;
      case "bye" :
        System.out.println("Bye. Hope to see you again soon!");
        break;
      default :
        list.add(input);
        System.out.println("Added: " + input);
        break;
    }
  }
}