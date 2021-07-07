package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.expense.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

/**
 * Represents a Storage for the list of tasks. A <code>Storage</code> object
 * handles the saving and loading of the data before the exit of Duke and
 * when Duke is being started up, respectively.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the task list from the file.
     * @return task list.
     */
    public ListStoreModel load() {
        ListStoreModel lists = new ListStoreModel();
        try (FileReader reader = new FileReader(this.filepath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                lists = handleInput(line, lists);
            }
            br.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return lists;
    }

    /**
     * Handles the string input from the storage file and adds a task or person respectively.
     * @param input given string from the storage file.
     * @param lists taskList and peopleList.
     * @return lists after being populated.
     */
    private ListStoreModel handleInput(String input, ListStoreModel lists) {
        String[] attr = input.split(" \\| ");
        if (attr.length == 2) {
            Person person = parsePerson(attr);
            lists.peopleList.add(person);
        } else if (attr.length == 3 || attr.length == 4) {
            Task task = parseTask(attr);
            lists.taskList.add(task);
        }
        return lists;
    }

    /**
     * Reads the given string and processes it accordingly to return
     * an appropriate Task.
     * @param attr attributes of the given string.
     * @return matching task as implied from the given string.
     */
    private Task parseTask(String[] attr) {
        String text = attr[2];
        String taskType = attr[0];
        boolean done = attr[1].equals("1");
        switch (taskType) {
        case "T":
            assert attr.length == 3 : String.format(" Wrong number of arguments: expected 3, got %d", attr.length);
            return new Todo(text, done);
        case "D":
            assert attr.length == 4 : String.format(" Wrong number of arguments: expected 4, got %d", attr.length);
            return new Deadline(text, done, new Datetime(attr[3]));
        case "E":
            assert attr.length == 4 : String.format(" Wrong number of arguments: expected 4, got %d", attr.length);
            return new Event(text, done, new Datetime(attr[3]));
        default:
            return null;
        }
    }

    /**
     * Reads the given string and processes it accordingly to return
     * an appropriate Person.
     * @param attr attributes of the given string.
     * @return matching Person as implied from the given string.
     */
    private Person parsePerson(String[] attr) {
        assert attr.length == 2 : String.format(" Wrong number or arguments: expected 2, got %d", attr.length);
        String name = attr[0];
        double amount = Double.parseDouble(attr[1]);
        return new Person(name, amount);
    }

    /**
     * Saves the current task list into the file.
     * @param tasks task list to be saved.
     */
    public void save(List<Task> tasks, List<Person> people) {
        try {
            File file = new File(this.filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            String line;
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    line = String.format("%s | %d | %s\n", task.getType(), task.isDone() ? 1 : 0, task.getText());
                } else {
                    line = String.format("%s | %d | %s | %s\n", task.getType(), task.isDone() ? 1 : 0, task.getText(),
                            task.getDatetime().getRawDatetime());
                }
                bw.write(line);
            }
            for (Person person : people) {
                line = String.format("%s | %.2f\n", person.getName(), person.getAmountOwed());
                bw.write(line);
            }
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
