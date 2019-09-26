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
    public String add(String fullCommand, String[] splitCommand) {
        String output = "";
        switch (splitCommand[0]) {
        case "t": {
            String[] stringBreaker = fullCommand.split("t", 2);
            if (!stringBreaker[1].equals("")) {
                ToDos todo = new ToDos(stringBreaker[1], this.formatter);
                actualList.add(todo);
                output += "\tGot it. I've added this task:\n";
                output += "\t  " + this.actualList.get(this.actualList.size() - 1) + '\n';
                output += "\tNow you have " + this.actualList.size() + " tasks in the list.";
            } else {
                //Should throw exception here
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        }
        case "d": {
            String newString = fullCommand.substring(9);
            String[] stringBreaker = newString.split("/by", 2);
            try {
                Date date = formatter.parse(stringBreaker[1]);
                Deadlines deadline = new Deadlines(stringBreaker[0], formatter, date);
                actualList.add(deadline);
                output += "\tGot it. I've added this task:\n";
                output += "\t  " + this.actualList.get(this.actualList.size() - 1) + '\n';
                output += "\tNow you have " + this.actualList.size() + " tasks in the list.";
            } catch (ParseException e) {
                e.printStackTrace();
            }
            break;
        }
        case "e": {
            String newString = fullCommand.substring(6);
            String[] stringBreaker = newString.split("/at", 2);
            try {
                Date date = formatter.parse(stringBreaker[1]);
                Events event = new Events(stringBreaker[0], formatter, date);
                actualList.add(event);
                output += "\tGot it. I've added this task:\n";
                output += "\t  " + this.actualList.get(this.actualList.size() - 1) + '\n';
                output += "\tNow you have " + this.actualList.size() + " tasks in the list.";
            } catch (ParseException e) {
                e.printStackTrace();
            }
            break;
        }
        default:
            //Throw exception here
            output += "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        }
        return output;
    }

    /**
     * Iterates through the list and prints out each item.
     */
    public String iterate() {
        String output = "";
        if (this.actualList.isEmpty()) {
            output += "\tYou have nothing on your to-do list!";
        } else {
            output += "\tHere are the tasks in your list:\n";
            for (int i = 0; i < actualList.size(); i++) {
                output += '\t';
                output += (i + 1) + ".";
                output += actualList.get(i).toString() + '\n';
            }
        }
        return output;
    }

    public String help() {
        String output = "COMMAND LIST\nt [taskname] : creates a todo \nd [taskname] /by [dd/mm/yyyy hhmm]: creates a new deadline\n" +
                "e [taskname] /at [dd/mm/yyy hhmm]: creates a new event\nlist: displays list of tasks\n" +
                "find [query]: searches for all tasks containing query in taskname\ndelete [index]: deletes task of that index\n" +
                "done [index]: sets task of that index to done\nbye: terminate the program";
        return output;
    }

    /**
     * Sets a task within the list (of given index) as completed.
     * @param index index of task stored within the list.
     */
    public String done(int index) {
        String output = "";
        if (index <= actualList.size()) {
            actualList.get(index - 1).done = true;
            output += "\tNice! I've marked this task as done:\n";
            output += "\t  [✓] " + actualList.get(index - 1).name;
        } else {
            output += "\tTask does not exist!";
        }
        return output;
    }

    /**
     * removes task of given index from the list.
     * @param index index of task stored within the list.
     */
    public String delete(int index) {
        String output = "";
        Task removed = this.actualList.remove(index - 1);
        output += "\tNoted. I've removed this task:\n";
        output += "\t  " + removed.toString() + '\n';
        output += "\tNow you have " + actualList.size() + " tasks in the list.";
        return output;
    }

    /**
     * searches through the list and outputs every task in which its name contains the query.
     * @param query String query to search for tasks.
     */
    public String find(String query) {
        String output = "";
        if (this.actualList.isEmpty()) {
            output += "\tYou have nothing on your to-do list!";
        } else {
            ArrayList<Task> tempList = new ArrayList<>();
            for (int i = 0; i < actualList.size(); i++) {
                if (actualList.get(i).name.contains(query)) {
                    tempList.add(actualList.get(i));
                }
            }
            if (tempList.isEmpty()) {
                output += "\tSorry no tasks matched your query!";
            } else {
                for (int j = 0; j < tempList.size(); j++) {
                    output += '\t';
                    output += (j + 1) + ".";
                    output += tempList.get(j).toString() + '\n';
                }
            }
        }
        return output;
    }
}
