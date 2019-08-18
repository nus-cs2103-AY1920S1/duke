import java.util.Scanner;

class TaskManager {
  private Scanner sc;

  public TaskManager() {
    sc = new Scanner(System.in);
  }

  public void initializeTasks() {
    while(sc.hasNextLine()) {
      String input = sc.nextLine();
      if(input.equals("bye")) {
        System.out.println("Bye. Hope to see you again soon!");
        return;
      } else {
        System.out.println(input);
      }
    }
  }
}