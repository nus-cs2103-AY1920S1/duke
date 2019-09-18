package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.DeadlinesTask;
import duke.tasks.EventsTask;
import duke.tasks.ToDoTask;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
/**
 * Storage class deals with the storing of the tasks in memory.
 */
public class Storage {

    private String filepath;
    private TaskList taskList;


    public Storage(String filepath) {
        this.filepath = filepath;
        taskList = new TaskList();
    }

    /**
     * The loadStorage method reads the txt file for information about the tasks.
     * It then processes the tasks and stores it in the taskList class.
     *
     * @return a taslist contaning the stored information.
     */
    public TaskList loadStorage() throws DukeException {

        File data = new File(filepath);

        try{
            if(data.createNewFile()){
                throw new DukeException("Couldn't find file, so created new one");
            }else{
                BufferedReader br = new BufferedReader(new FileReader(data));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] currentTaskArray = line.split("\\|");
                    String taskCategory = currentTaskArray[0].replace(" ", "");
                    String currentStatus = currentTaskArray[1];
                    Boolean currentstatusBoolean;
                    currentstatusBoolean = !currentStatus.equals("0");
                    String taskName = currentTaskArray[2];
                    if (taskCategory.equals("T")) {
                        ToDoTask newTask = new ToDoTask(currentstatusBoolean, taskName);
                        taskList.fileAdd(newTask);
                    } else if (taskCategory.equals("D")) {
                        String deadline = currentTaskArray[3];
                        LocalDateTime endingDateTime = LocalDateTime.parse(deadline);
                        DeadlinesTask newTask = new DeadlinesTask(currentstatusBoolean, taskName, endingDateTime);
                        taskList.fileAdd(newTask);
                    } else {
                        String startingDateTimesString = currentTaskArray[3];
                        String endingDateTimesString = currentTaskArray[4];

                        LocalDateTime startingDateTime = LocalDateTime.parse(startingDateTimesString);
                        LocalDateTime endingDateTime = LocalDateTime.parse(endingDateTimesString);

                        EventsTask newTask = new EventsTask(currentstatusBoolean, taskName, startingDateTime, endingDateTime);
                        taskList.fileAdd(newTask);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return taskList;
    }

    /**
     * Saves the current tasklist to a txt file.
     */
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
                for (int i = 0; i < taskList.size(); i++) {
                    writer.write(taskList.getTask(i).toFileFormat());
                }
                writer.close();
            } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = this.getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}