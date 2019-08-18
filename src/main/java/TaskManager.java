import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;

class TaskManager {
  private static final String TICK = "\u2713";
  private static final String CROSS = "\u2717";
  private Scanner sc;
  private PrintStream ps;
  private ArrayList<Task> list;

  public TaskManager() throws UnsupportedEncodingException {
    this.sc = new Scanner(System.in);
    this.ps = new PrintStream(System.out, true, "UTF-8");
    this.list = new ArrayList<>();
  }

  // Starts the task 
  public void initializeTasks() {
    while(sc.hasNextLine()) {
      String input = sc.nextLine();
      switch(input) {
        case "list" :                                         
          printList();
          break;
        case "bye" :
          System.out.println("Bye. Hope to see you again soon!");
          return;
        default :
          // Splits the string to check if the inital string is a "done" event
          // If it is done, then we check if the done event is valid, otherwise we treat
          // it as a "add" event
          String[] arr = input.split(" ", 2);
          if(arr[0].equals("done")) {
            Integer index;
            try {
              index = Integer.parseInt(arr[1]);
            } catch(Exception e) {
              index = null;
            }
            if(index != null) {
              try {
                list.get(index - 1);
              } catch(Exception e) {
                throw new java.lang.Error("Input index is out of bound");
              }
              Task currTask = list.get(index - 1);
              currTask.complete();
              System.out.println("Nice! I've marked this task as done: ");
              ps.println("  " + "[" + TICK + "] " + currTask);
              continue;
            }
          }
          Task task = new Task(input);
          list.add(task);
          System.out.println("Added: " + task);
          break;
      }
    }
  }

  // Prints the stored data given by the user.
  public void printList() {
    System.out.println("Here are the tasks in your list:");
    for(int i = 0; i < this.list.size(); i++) {
      if(this.list.get(i).isComplete()) {
        ps.println((i + 1) + ". " + "[" + TICK + "] " + this.list.get(i));
      } else {
        ps.println((i + 1) + ". " + "[" + CROSS + "] " + this.list.get(i));
      }
    }
  }

}