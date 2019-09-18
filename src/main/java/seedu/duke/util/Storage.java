package seedu.duke.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;
import seedu.duke.trivia.Trivia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle the saving and loading of data to a specified text file.
 */
public class Storage {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private File file;
    private File trivia;
    private Scanner fileReader;
    private FileReader triviaReader;
    private FileWriter writer;
    private Gson gson;

    /**
     * Constructor to handle saving and loading.
     */
    public Storage(String list, String trivia) {
        gson = new Gson();
        file = new File(list);
        this.trivia = new File(trivia);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            if (!this.trivia.exists()) {
                this.trivia.getParentFile().mkdirs();
                this.trivia.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Method to load from the text file.
     *
     * @return Returns the ArrayList for use in the TaskList class.
     * @throws DukeException Throws if there is no file.
     */
    public ArrayList<Task> loadList() throws DukeException {
        try {
            fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] keywords = line.split(" ");
                boolean isDone = isDone(keywords);
                if (keywords[0].equals("[T]")) {
                    taskList.add(toTodo(keywords, isDone));
                } else if (keywords[0].equals("[D]")) {
                    taskList.add(toDeadline(keywords, isDone));
                } else if (keywords[0].equals("[E]")) {
                    taskList.add(toEvent(keywords, isDone));
                } else {
                    throw new DukeException();
                }
            }
            fileReader.close();
            return taskList;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("YOoooUR fILe haS BeEN cORRupTEd.");
        } catch (FileNotFoundException ex) {
            throw new DukeException();
        }
    }

    public Trivia loadTrivia() throws TriviaException {
        try {
            triviaReader = new FileReader(trivia);
            Trivia returnTrivia = gson.fromJson(triviaReader, Trivia.class);
            if (returnTrivia == null) {
                return new Trivia();
            } else {
                return returnTrivia;
            }
        } catch (IOException ex) {
            throw new TriviaException();
        }
    }

    /**
     * Checks if the task read from the file is done or not.
     *
     * @param keywords The task in string array form where the entry in index 1 is the done indicator.
     * @return Returns true if the task is done and false if it is not.
     * @throws DukeException Throws if the entry is not "[+]" or "[-]".
     */
    public static boolean isDone(String[] keywords) throws DukeException {
        if (keywords[1].equals("[-]")) {
            return false;
        } else if (keywords[1].equals("[+]")) {
            return true;
        } else {
            throw new DukeException("YOoooUR fILe haS BeEN cORRupTEd.");
        }
    }

    /**
     * Converts the string array into a Todo command.
     *
     * @param keywords String array representing the string read from storage.
     * @param isDone Whether the task is done or not.
     * @return A Todo command with isDone status updated.
     * @throws DukeException Throws if the entry has no description.
     */
    public static Todo toTodo(String[] keywords, boolean isDone) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("YOoooUR fILe haS BeEN cORRupTEd.");
        } else {
            keywords[0] = "";
            keywords[1] = "";
            String desc = String.join(" ", keywords).strip();
            Todo temp = new Todo(desc);
            if (isDone) {
                temp.setDone();
            }
            return temp;
        }
    }

    /**
     * Converts the string array into a Deadline command.
     *
     * @param keywords String array representing the string read from storage.
     * @param isDone Whether the task is done or not.
     * @return A deadline command with isDone status updated.
     * @throws DukeException Throws if the entry violates the requirements for Deadline.
     */
    public static Deadline toDeadline(String[] keywords, boolean isDone) throws DukeException {
        String desc = "";
        String time = "";
        boolean flag = false;
        for (int i = 2; i < keywords.length; i++) {
            if (flag) {
                if (keywords[i].equals(")")) {
                    break;
                } else {
                    time = time + " " + keywords[i];
                }
            } else if (keywords[i].equals("(by:")) {
                flag = true;
            } else {
                desc = desc + " " + keywords[i];
            }
        }
        Deadline temp = new Deadline(desc.strip(), time.strip());
        if (isDone) {
            temp.setDone();
        }
        return temp;
    }

    /**
     * Converts the string array into a Event command.
     *
     * @param keywords String array representing the string read from storage.
     * @param isDone Whether the task is done or not.
     * @return A event command with isDone status updated.
     * @throws DukeException Throws if the entry violates the requirements for Event.
     */
    public static Event toEvent(String[] keywords, boolean isDone) throws DukeException {
        String desc = "";
        String time = "";
        boolean flag = false;
        for (int i = 2; i < keywords.length; i++) {
            if (flag) {
                if (keywords[i].equals(")")) {
                    break;
                } else {
                    time = time + " " + keywords[i];
                }
            } else if (keywords[i].equals("(at:")) {
                flag = true;
            } else {
                desc = desc + " " + keywords[i];
            }
        }
        Event temp = new Event(desc.strip(), time.strip());
        if (isDone) {
            temp.setDone();
        }
        return temp;
    }

    /**
     * Method to write to the text file.
     *
     * @param taskList The TaskList to write into the file.
     * @throws IOException Throws if FileWriter takes in an invalid file.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        writer = new FileWriter(file);
        ArrayList<Task> tempList = taskList.getTaskList();
        try {
            String temp = tempList.get(0).toString();
            for (int i = 1; i < tempList.size(); i++) {
                temp = temp + "\n" + tempList.get(i).toString();
            }
            writer.write(temp);
        } catch (IndexOutOfBoundsException ex) {
            throw new IOException();
        }
        writer.close();
    }

    public void writeTrivia(Trivia triviaToWrite) throws TriviaException {
        try {
            String json = gson.toJson(triviaToWrite);
            writer = new FileWriter(trivia);
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            throw new TriviaException();
        }
    }

}
