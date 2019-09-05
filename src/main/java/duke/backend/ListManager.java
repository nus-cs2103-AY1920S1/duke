package duke.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import duke.task.Task;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;

public class ListManager {
    public ArrayList<Task> actualList;
    private SimpleDateFormat formatter;

    public ListManager(SimpleDateFormat formatter) {
        this.actualList = new ArrayList<>();
        this.formatter = formatter;
    }

    public ListManager(ArrayList<Task> taskList, SimpleDateFormat formatter) {
        this.actualList = taskList;
        this.formatter = formatter;
    }

    /**
     * Add Command that creates the different Tasks based on the first word.
     * @param fullCommand full String command.
     * @param splitCommand output of java.split(fullCommand).
     */
    public void add(String fullCommand, String[] splitCommand) {
        switch (splitCommand[0]) {
        case "todo": {
            String[] stringBreaker = fullCommand.split("todo", 2);
            if (!stringBreaker[1].equals("")) {
                ToDos todo = new ToDos(stringBreaker[1], this.formatter);
                actualList.add(todo);
            } else {
                //Should throw exception here
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        }
        case "deadline": {
            String newString = fullCommand.substring(9);
            String[] stringBreaker = newString.split("/by", 2);
            try {
                Date date = formatter.parse(stringBreaker[1]);
                Deadlines deadline = new Deadlines(stringBreaker[0], formatter, date);
                actualList.add(deadline);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            break;
        }
        case "event": {
            String newString = fullCommand.substring(6);
            String[] stringBreaker = newString.split("/at", 2);
            try {
                Date date = formatter.parse(stringBreaker[1]);
                Events event = new Events(stringBreaker[0], formatter, date);
                actualList.add(event);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            break;
        }
        default:
            //Throw exception here
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    /**
     * Iterates through the list and prints out each item.
     */
    public void iterate() {
        if (this.actualList.isEmpty()) {
            System.out.println("\tYou have nothing on your to-do list!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < actualList.size(); i++) {
                System.out.print('\t');
                System.out.print((i + 1) + ".");
                System.out.println(actualList.get(i));
            }
        }
    }

    /**
     * Sets a task within the list (of given index) as completed.
     * @param index index of task stored within the list.
     */
    public void done(int index) {
        if (index <= actualList.size()) {
            actualList.get(index - 1).done = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [✓] " + actualList.get(index - 1).name);
        } else {
            System.out.println("\tTask does not exist!");
        }
    }

    /**
     * removes task of given index from the list.
     * @param index index of task stored within the list.
     */
    public void delete(int index) {
        Task removed = this.actualList.remove(index - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + removed);
        System.out.println("\tNow you have " + actualList.size() + " tasks in the list.");
    }

    /**
     * searches through the list and outputs every task in which its name contains the query.
     * @param query String query to search for tasks.
     */
    public void find(String query) {
        if (this.actualList.isEmpty()) {
            System.out.println("\tYou have nothing on your to-do list!");
        } else {
            ArrayList<Task> tempList = new ArrayList<>();
            for (int i = 0; i < actualList.size(); i++) {
                if (actualList.get(i).name.contains(query)) {
                    tempList.add(actualList.get(i));
                }
            }
            if (tempList.isEmpty()) {
                System.out.println("\tSorry no tasks matched your query!");
            } else {
                for (int j = 0; j < tempList.size(); j++) {
                    System.out.print('\t');
                    System.out.print((j + 1) + ".");
                    System.out.println(tempList.get(j));
                }
            }
        }
    }
}
