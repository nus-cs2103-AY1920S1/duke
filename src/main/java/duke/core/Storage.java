package duke.core;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File taskFile;
    private File dataFolder;
    private File archivesFolder;

    /**
     * Class constructor.
     * @param dataFolder File instance to be used for storage
     */
    public Storage(File dataFolder) {
        this.dataFolder = dataFolder;
        this.taskFile = new File(dataFolder, "tasks.txt");
        this.archivesFolder = new File(dataFolder, "archives");
    }

    /**
     * Create Storage File.
     */
    public void createFile() throws IOException {
        taskFile.createNewFile();
    }

    /**
     * Write input TaskList into local storage.
     * @param list TaskList to be written to local storage
     */
    public void writeTaskListToFile(TaskList list) {
        try {
            String textToAdd = "";
            FileWriter fw = new FileWriter(taskFile.getPath());
            for (Task t : list.getTasks()) {
                textToAdd += t.serialize();
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Append a Task into storage file.
     * @param taskToAppend Task to be appended
     */
    public void appendTaskToFile(Task taskToAppend) {
        try {
            FileWriter fw = new FileWriter(taskFile.getPath(), true); // create a FileWriter in append mode
            fw.write(taskToAppend.serialize());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Archive existing TaskList, .txt file will be created.
     * @param list Existing TaskList.
     */
    public boolean archiveTaskList(TaskList list, String archiveName) {
        try {
            String textToAdd = "";
            String archiveFilePath = "data/archives/" + archiveName + ".txt";
            new File(archiveFilePath).createNewFile();
            FileWriter fw = new FileWriter(archiveFilePath);
            for (Task t : list.getTasks()) {
                textToAdd += t.serialize();
            }
            fw.write(textToAdd);
            fw.close();
            if (taskFile.delete()) {
                createFile();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Return a list of existing archive names.
     */
    public List<String> getArchivedTaskLists() {
        File[] listOfArchives = archivesFolder.listFiles();
        List<String> archiveNames = new ArrayList<>();
        for (File archive : listOfArchives) {
            archiveNames.add(archive.getName());
        }
        return archiveNames;
    }

    /**
     * Initialise storage folders.
     */
    public boolean initialise() throws SecurityException {
        boolean hasBeenInitialised = true;
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
            hasBeenInitialised = false;
        }
        if (!archivesFolder.exists()) {
            archivesFolder.mkdir();
            hasBeenInitialised = false;
        }
        return hasBeenInitialised;
    }

    /**
     * Empty archives.
     */
    public boolean emptyArchives() {
        boolean isSuccessful = true;
        File[] listOfArchives = archivesFolder.listFiles();
        for (File archive : listOfArchives) {
            if (!archive.delete()) {
                isSuccessful = false;
                System.out.println(isSuccessful);
            }
        }
        return isSuccessful;
    }

    /**
     * Retrieve archive.
     */
    public List<Task> retrieveArchivedTaskList(String archiveName) throws DukeException, IOException {
        String archiveFilePath = "data/archives/" + archiveName + ".txt";
        List<Task> archive = getTaskList(new File(archiveFilePath));
        return archive;
    }

    /**
     * Retrieve and return lists of Tasks from storage file.
     * @throws FileNotFoundException File not found exception
     * @throws DukeException Duke exception
     */
    public List<Task> getTaskList(File file) throws FileNotFoundException, DukeException {
        List<Task> taskList = new ArrayList<Task>();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            Task t = Task.deserialize(s.nextLine());
            taskList.add(t);
        }
        s.close();
        return taskList;
    }

    /**
     * Returns current tasks.
     * @throws FileNotFoundException File not found exception
     * @throws DukeException Duke exception
     */
    public List<Task> getCurrentTasks() throws FileNotFoundException, DukeException {
        return getTaskList(taskFile);
    }
}
