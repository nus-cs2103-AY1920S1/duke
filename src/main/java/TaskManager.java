import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;

class TaskManager {
  public static final String TICK = "\u2713";
  public static final String CROSS = "\u2717";

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
      String[] inputArr = input.split(" ", 2);
      if(inputArr.length == 1) {
        switch(inputArr[0]) {
          case "list" :
            printList();
            break;
          case "bye" :
            System.out.println("Bye. Hope to see you again soon!");
            return;
          default :
            checkInputError(inputArr[0]);
            break;
        }
      } else {
        String givenTask = inputArr[1];
        String[] taskArr;
        switch(inputArr[0]) {
          case "todo" :
            Todo todo = new Todo(givenTask);
            list.add(todo);
            printTask(todo);
            break;
          case "deadline" :
            taskArr = givenTask.split(" /by ", 2);
            if(taskArr.length == 1) {
              throwTaskError(inputArr[0]);
              break;
            }
            Deadline deadline = new Deadline(taskArr[0], taskArr[1]);
            list.add(deadline);
            printTask(deadline);
            break;
          case "event" :
            taskArr = givenTask.split(" /at ", 2);
            if(taskArr.length == 1) {
              throwTaskError(inputArr[0]);
              break;
            }
            Event event = new Event(taskArr[0], taskArr[1]);
            list.add(event);
            printTask(event);
            break;
          case "done" :
            try {
              Integer.parseInt(givenTask);
            } catch (Exception e) {
              System.out.println("Oof. Done requires a number behind");
              break;
            }
            Integer taskNum = Integer.parseInt(givenTask);
            try {
              list.get(taskNum - 1);
            } catch (Exception e) {
              System.out.println("Oof. The given task number is not found");
              break;
            }
            Task task = list.get(taskNum - 1);
            task.complete();
            System.out.println("Nice! I've marked this task as done: ");
            ps.println("  " + task.toString());
        }
      }
      System.out.println();
    }
  }

  private void printTask(Task task) {
    System.out.println("Got it. I've added this task: ");
    ps.println("  " + task.toString());
    System.out.println("Now you have " + this.list.size() +  " tasks in the list.");
  }

  // Prints the stored data given by the user.
  private void printList() {
    System.out.println("Here are the tasks in your list:");
    for(int i = 0; i < this.list.size(); i++) {
      if(this.list.get(i).isComplete()) {
        ps.println((i + 1) + ". " + this.list.get(i));
      } else {
        ps.println((i + 1) + ". " + this.list.get(i));
      }
    }
  }

  private void checkInputError(String input) {
    switch(input) {
      case "todo" :
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        break;
      case "deadline" :
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        break;
      case "event" :
        System.out.println("OOPS!!! The description of a event cannot be empty.");
        break;
      case "done" :
        System.out.println("Oof. Done requires a number behind");
        break;
      default :
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        break;
    }
  }

  private void throwTaskError(String type) {
    switch(type) {
      case "deadline" :
        System.out.println("Deadline requires a specific time using \'/\'");
        break;
      case "event" :
        System.out.println("Event requires a specific time using \'/\'");
        break;
      default :
        // Not supposed to happen
        throw new java.lang.Error("Task Error thrown was of: " + type + " type");
    }
  }

}