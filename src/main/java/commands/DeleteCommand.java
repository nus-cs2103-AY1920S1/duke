package commands;

import components.Storage;
import components.TaskList;
import tasks.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private ArrayList<Integer> indices = new ArrayList<>();

    /**
     * A command to delete dynamically.
     * @param restOfLine the part of the line after the delete keyword.
     * @throws DukeException thrown if number range is invalid.
     */
    public DeleteCommand(String restOfLine) throws DukeException {
        //should parse "2-3,5-7,10,15" as [1,2,4,5,6,9,14] -> expand the hyphen and --1 from each
        Scanner sc = new Scanner(restOfLine).useDelimiter(",");
        while (sc.hasNext()) {
            String next;
            try {
                next = sc.next().trim();
                if (next.contains("-")) {
                    //range
                    Scanner another = new Scanner(next).useDelimiter("-");
                    int start = another.nextInt() - 1;
                    int end = another.nextInt() - 1;
                    if (start > end) {
                        throw new DukeException("You entered an invalid number range.");
                    }
                    for (int i = start; i <= end; i++) {
                        indices.add(i);
                    }
                } else {
                    int index = Integer.parseInt(next) - 1;
                    indices.add(index);
                }
                indices.sort(Comparator.naturalOrder());
            } catch (NumberFormatException e) {
                throw new DukeException("Your numbering format seems incorrect :-(");
            }
        }
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        // String[] t = new String[3];
        // IndexOutOfBoundsException is thrown from components.TaskList
        //copy undeleted tasks into new list in tasklist. Return a list of all deleted tasks.
        ArrayList<Task> deleted = taskList.batchDelete(this.indices);
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Noted. I've removed " + (deleted.size() == 1 ? "this task:" : "these tasks:"));
        for (Task task : deleted) {
            temp.add(task.toString());
        }
        ArrayList<Task> tasks = taskList.getArr();
        storage.save(tasks);
        temp.add("Now you have " + tasks.size()
                    + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        return temp.toArray(new String[0]);
    }
}
