package duke.storage;

import duke.exception.DukeException;
import duke.person.Person;
import duke.person.PersonList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private static final String outputNoSave = "Tasks are not able to be saved into .txt file.";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads the history of Duke.
     *
     * @return return an arraylist of tasks
     * @throws DukeException when loading encounters exception
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splits = line.split("[|]");
                assert splits.length >= 3 : "tasks.txt should follow the format with |";
                int completed = Integer.parseInt(splits[1]);
                Task newTask;
                switch (splits[0]) {
                case "T":
                    newTask = parseTodo(splits);
                    break;
                case "E":
                    newTask = parseEvent(splits);
                    break;
                case "D":
                    newTask = parseDeadline(splits);
                    break;
                default:
                    newTask = new Task("");
                }
                if (completed == 1) {
                    newTask.changeStatus();
                }
                list.add(newTask);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new DukeException();
        }
        return list;
    }

    /**
     * saves the tasks into a .txt file.
     *
     * @param list a list of tasks
     */
    public void save(ArrayList<Task> list) throws DukeException {
        try {
            File myFile = new File(filePath);
            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }
            //FileWriter: the file's parent directory must exist
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (Task task : list) {
                if (!textToAdd.equals("")) {
                    textToAdd = textToAdd.concat("\n");
                }
                textToAdd = textToAdd.concat(task.writer());
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(outputNoSave);
        }
    }

    private PersonList parsePersonList(String[] parts) {
        PersonList list = new PersonList();
        for (String str : parts) {
            int startIndex = str.indexOf("(");
            int endIndex = str.lastIndexOf(")");
            if (startIndex == -1) {
                String name = str;
                list.addPerson(new Person(name));
            } else {
                assert startIndex >= 0 : "name doesn't exist";
                String name = str.substring(0, startIndex);
                assert endIndex >= startIndex + 1 : "title doesn't exist";
                String title = str.substring(startIndex + 1, endIndex);
                if (endIndex == str.length() - 1) {
                    list.addPerson(new Person(name, title));
                } else {
                    String contact = str.substring(endIndex + 1);
                    list.addPerson(new Person(name, title, contact));
                }
            }
        }
        return list;
    }

    private Task parseTodo(String[] parts) {
        PersonList list = parsePersonList(Arrays.copyOfRange(parts, 3, parts.length));
        return new Todo(parts[2], list);
    }

    private Task parseEvent(String[] parts) {
        PersonList list = parsePersonList(Arrays.copyOfRange(parts, 4, parts.length));
        return new Event(parts[2], parts[3], list);
    }

    private Task parseDeadline(String[] parts) {
        PersonList list = parsePersonList(Arrays.copyOfRange(parts, 4, parts.length));
        return new Deadline(parts[2], parts[3], list);
    }
}
