package logic;

import contacts.Contact;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Deals with loading tasks and saving tasks in file.
 */

public class Storage {
    private File taskFile;
    private File contactFile;

    public Storage(File taskFile, File contactFile) {
        this.taskFile = taskFile;
        this.contactFile = contactFile;
    }

    /**
     * Reads and scans text file, convert them to Task Objs to be added.
     *
     * @return List of Tasks loaded from text file
     * @throws DukeException If encounter file creation/parsing problems
     */
    public List<Task> loadTasks() throws DukeException {
        List<Task> taskList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strArr = line.split(Pattern.quote(" | "));
                Task task = readAndCreateTask(strArr);
                taskList.add(task);
            }

            Ui.loadStr("Data is loaded from data/taskList.txt");
            return taskList;
        } catch (FileNotFoundException e) {
            try {
                taskFile.createNewFile(); //creates new text file
            } catch (IOException ioE) {
                Ui.loadStr(ioE.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Reads and scans text file, convert them to Contact Objs to be added.
     *
     * @return List of Contacts loaded from text file
     */
    public List<Contact> loadContacts() throws DukeException {
        List<Contact> contactList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(contactFile);

            while (sc.hasNext()) {
                String line = sc.nextLine();

                String[] contactArr = line.split(Pattern.quote(" | "));
                //Name, RS, Num, Email
                if (contactArr.length != 4) {
                    throw new DukeException("Error: File corrupted, cannot read");
                }
                Contact c = new Contact(contactArr); //creates contacts
                contactList.add(c);

                Ui.loadStr("Data is loaded from data/contactList.txt");
            }
        } catch (FileNotFoundException e) {
            try {
                contactFile.createNewFile(); //creates new text file
            } catch (IOException ioE) {
                Ui.loadStr(ioE.getMessage());
            }
        }

        return contactList;
    }

    /**
     * Creates task from reading the string from text file. Called from loadTasks().
     *
     * @param textArr String Array obtained after splitted
     * @return Task Object to be added to TaskList
     * @throws DukeException If unable to parse
     */
    private Task readAndCreateTask(String[] textArr) throws DukeException {
        String type = textArr[0];
        Task task = null;
        boolean isDone = textArr[1].equals("1"); //1 means done

        switch (type) {
        case "T": //todo
            task = new ToDo(isDone, textArr[2]);
            break;
        case "D": //deadline
            LocalDateTime deadline = Parser.parseDateTime(textArr[3]);
            task = new Deadlines(isDone, textArr[2], deadline);
            break;
        case "E": //event
            String[] startEndStr = textArr[3].split(" - ");
            LocalDateTime start = Parser.parseDateTime(startEndStr[0]);
            LocalDateTime end = Parser.parseDateTime(startEndStr[1]);
            task = new Events(isDone, textArr[2], start, end);
            break;
        default:
            break;
        }

        return task;
    }

    /**
     * Updates taskList text file after command execution.
     *
     * @param taskList List of Tasks in logic.TaskList
     */
    public void updateTaskFile(DukeList taskList) {
        List<Task> list = taskList.getList();
        assert list != null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            sb.append(t.toFileString());
            if (i != list.size() - 1) { //final item, dont add new line
                sb.append("\n");
            }
        }

        try {
            FileWriter fw = new FileWriter(taskFile);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            Ui.loadStr("Cannot write to file: " + e.getMessage());
        }
    }

    /**
     * Updates contactList tcext file after command execution.
     *
     * @param contactList List of contacts in logic.ContactList
     */
    public void updateContactFile(DukeList contactList) {
        List<Contact> list = contactList.getList();
        assert list != null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Contact t = list.get(i);
            sb.append(t.toFileString());
            if (i != list.size() - 1) { //final item, dont add new line
                sb.append("\n");
            }
        }

        try {
            FileWriter fw = new FileWriter(contactFile);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            Ui.loadStr("Cannot write to file: " + e.getMessage());
        }
    }
}
