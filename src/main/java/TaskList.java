import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

  private ArrayList<Task> taskList = new ArrayList<>();

  public TaskList(Storage s) throws FileNotFoundException, DukeException, IOException {
    try {
      Scanner sc = new Scanner(s.load());
      while (sc.hasNextLine()) {
        String command = sc.next();
        String description = sc.nextLine().stripLeading();

        Parser p = new Parser(command, description);
        p.executeOnly(this);
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File is not found!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  public ArrayList<Task> getTaskList() {
    return taskList;
  }

  public void printTasks() {
    System.out.println("\t____________________________________________________________");
    System.out.println("\n\tHere are the tasks in your list: ");
    for (int i = 0; i < taskList.size(); i++) {
      System.out.println("\n\t" + (i + 1) + ". " + taskList.get(i).toString());
    }
    System.out.println("\t____________________________________________________________\n");
  }

  public void setTaskList(ArrayList<Task> tasks) {
    this.taskList = tasks;
  }
}