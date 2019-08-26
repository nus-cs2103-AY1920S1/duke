import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ListManager {
    ArrayList<Task> actualList;

    public ListManager() {
        this.actualList = new ArrayList<>();
    }

    public ListManager(ArrayList<Task> taskList) {
        this.actualList = taskList;
    }

    public void add(String fullCommand, String[] splitCommand) {
        if(splitCommand[0].equals("todo")) {
            String[] stringBreaker = fullCommand.split("todo",2);
            if (!stringBreaker[1].equals("")) {
                ToDos todo = new ToDos(stringBreaker[1]);
                actualList.add(todo);
            } else {
                //Should throw exception here
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (splitCommand[0].equals("deadline")) {
            String newString = fullCommand.substring(9);
            String[] stringBreaker = newString.split("/by",2);
            Deadlines deadline = new Deadlines(stringBreaker[0], stringBreaker[1]);
            actualList.add(deadline);
        } else if (splitCommand[0].equals("event")) {
            String newString = fullCommand.substring(6);
            String[] stringBreaker = newString.split("/at",2);
            Events event = new Events(stringBreaker[0], stringBreaker[1]);
            actualList.add(event);
        } else {
            //Throw exception here
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void iterate() {
        if (this.actualList.isEmpty()) {
            System.out.println("\tYou have nothing on your to-do list!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for(int i = 0; i < actualList.size(); i++) {
                System.out.print('\t');
                System.out.print(i+1 + ".");
                System.out.println(actualList.get(i));
            }
        }
    }

    public void done(int index) {
        if (index <= actualList.size()) {
            actualList.get(index - 1).done = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [✓] " + actualList.get(index - 1).name);
        } else {
            System.out.println("\tTask does not exist!");
        }
    }

    public void delete(int index) {
        Task removed = this.actualList.remove(index - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + removed);
        System.out.println("\tNow you have " + actualList.size() + " tasks in the list.");
    }
}
