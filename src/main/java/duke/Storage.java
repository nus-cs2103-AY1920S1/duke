package duke;

import task.Deadline;
import task.Task;
import task.Todo;
import task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Storage class to manage hard drive data.
 */
public class Storage {
    private String filepath;
    private String vocabpath;

    /**
     * Storage constructor.
     *
     * @param filepath Path of data for the TaskList
     * @param vocabpath Path of data for VocabularyList
     */
    public Storage(String filepath, String vocabpath) throws DukeException {
        this.filepath = filepath;
        this.vocabpath = vocabpath;
    }

    /**
     * Updates the given vocabulary file.
     *
     * @param vocabularyList VocabularyList of Duke.
     * @throws DukeException Catches IOException.
     */
    public void updateVocabulary(VocabularyList vocabularyList) throws DukeException {
        try {
            String[] list = vocabularyList.getList();
            FileWriter fw = new FileWriter(this.vocabpath);
            for (String pair : list) {
                String data = String.format("%s\n", pair);
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Initially populates the Task List with the hard drive data (Input data file).
     *
     * @param list ArrayList to be populated.
     * @param line A given line in the input file.
     */
    private void initialPopulate(ArrayList<Task> list, String line) throws DukeException {
        String[] arr = line.split(" \\| ", 4);
        String cmd = arr[0];
        boolean isDone = Integer.parseInt(arr[1]) != 0;
        switch (cmd) {
        case "T":
            Task todo = new Todo(arr[2]);
            if (isDone) {
                todo.markAsDone();
            }
            list.add(todo);
            break;
        case "D":
            String[] datetime = arr[3].split(" ", 2);
            Task deadline = new Deadline(arr[2], datetime[0], datetime[1]);
            if (isDone) {
                deadline.markAsDone();
            }
            list.add(deadline);
            break;
        case "E":
            Task event = new Event(arr[2], arr[3]);
            if (isDone) {
                event.markAsDone();
            }
            list.add(event);
            break;
        default:
            throw new DukeException("Invalid Task Type");
        }
    }

    /**
     * Initial seeding of vocabulary from hard drive memory.
     *
     * @return HashMap mapping vocabulary to content.
     * @throws DukeException Catches various Exceptions that can possibly appear.
     */
    public HashMap<String, String> loadVocabulary() throws DukeException {
        HashMap<String, String> dictionary = new HashMap<>();
        try {
            File f = new File(vocabpath);
            if (!f.exists()) {
                DukeException.checkValidity(!f.createNewFile(), "Data file not created");
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] vocabularyArr = line.split(" ", 2);
                assert vocabularyArr.length == 2 : "Vocabulary file is corrupted.";
                dictionary.put(vocabularyArr[0], vocabularyArr[1]);
            }
            fileReader.close();
            return dictionary;
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Initial seeding of the data from hard drive memory.
     *
     * @return seeded ArrayList.
     * @throws DukeException duke.DukeException class.
     */
    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                DukeException.checkValidity(!f.createNewFile(), "Data file not created");
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                initialPopulate(list, line);
            }
            fileReader.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Method to override the hard drive memory with the given list.
     *
     * @param tasklist given tasklist.
     * @throws DukeException custom duke.DukeException.
     */
    public void updateData(TaskList tasklist) throws DukeException {
        try {
            ArrayList<Task> list = tasklist.getList();
            FileWriter fw = new FileWriter(this.filepath);
            for (Task task : list) {
                String data = String.format("%s\n", task.fileFormat());
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

}

