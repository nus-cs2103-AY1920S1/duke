package util;

import error.ConfigurationException;
import task.tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/***
 * Singleton storage class used to handle reading and writing to storage file in system memory.
 */
public class DukeStorage {
    private static DukeStorage singletonDukeStorage;
    private String configFilePath;
    private String storageFilePath;
    private List<Task> tasks;

    /***
     * Gets DukeStorage singleton instance.
     * @return DukeStorage singleton.
     * @throws ConfigurationException if DukeStorage was not initialized.
     */
    public static DukeStorage getInstance() throws ConfigurationException {
        if (singletonDukeStorage == null) {
            throw new ConfigurationException("DukeStorage not initialized");
        } else {
            return singletonDukeStorage;
        }
    }

    /***
     * Initializes DukeStorrage based on default options if configuration file is not found.
     * @param configFilePath path to optional configuration file.
     * @throws ConfigurationException if configuration and storage file cannot be read.
     */
    public static void initializeDukeStorage(String configFilePath) throws ConfigurationException {
        singletonDukeStorage = new DukeStorage();
        singletonDukeStorage.configFilePath = configFilePath;

        singletonDukeStorage.readConfigFile();
        singletonDukeStorage.loadTaskData();
    }

    /***
     * Returns current list of tasks stored in the system.
     * @return list of Tasks.
     */
    public List<Task> getTaskData() {
        return tasks;
    }

    private void readConfigFile() throws ConfigurationException {
        try {
            InputStream configFileInputStream;

            if (new File(configFilePath).exists()) {
                DukeMessage configFileFoundMessage = new DukeMessage("Config file found...");
                DukeOutput.printMessage(configFileFoundMessage);

                configFileInputStream = new FileInputStream(configFilePath);
                Properties configFileProperties = new Properties();
                configFileProperties.load(configFileInputStream);
                storageFilePath = configFileProperties.getProperty("storage_path");
            } else {
                DukeMessage configFileNotFoundMessage = new DukeMessage("Config file not found")
                        .newLine()
                        .append("Using default storage path at ")
                        .append(getDefaultStoragePath());
                DukeOutput.printMessage(configFileNotFoundMessage);

                storageFilePath = getDefaultStoragePath();
            }
        } catch (IOException e) {
            throw new ConfigurationException("Unable to read config file");
        }
    }

    private String getDefaultStoragePath() {
        return System.getProperty("user.home") + "/bin/duke";
    }

    private void loadTaskData() throws ConfigurationException {
        if (!new File(storageFilePath).exists()) {
            DukeMessage storageFileNotFoundMessage = new DukeMessage("Storage file not found at ")
                    .append(storageFilePath)
                    .newLine()
                    .append("Creating file...");
            DukeOutput.printMessage(storageFileNotFoundMessage);

            writeTaskData(new ArrayList<>());
        }

        try {
            FileInputStream taskDataInputStream = new FileInputStream(storageFilePath);
            ObjectInputStream taskListInputStream = new ObjectInputStream(taskDataInputStream);

            DukeMessage readingTaskDataMessage = new DukeMessage("Storage file found")
                    .newLine()
                    .append("Reading task data...");
            DukeOutput.printMessage(readingTaskDataMessage);

            tasks = (List) taskListInputStream.readObject();

            DukeMessage taskDataReadSuccessMessage = new DukeMessage("Successfully read task data");
            DukeOutput.printMessage(taskDataReadSuccessMessage);

            taskListInputStream.close();
            taskDataInputStream.close();
        } catch (FileNotFoundException e) {
            throw new ConfigurationException("Unable to access storage file");
        } catch (IOException e) {
            throw new ConfigurationException("Unable to read storage file");
        } catch (ClassNotFoundException e) {
            throw new ConfigurationException("Storage file corrupted");
        }
    }

    /***
     * Write tasks to system memory.
     * @param tasks list of tasks to be written.
     * @throws ConfigurationException if DukeStorage was not initialized or storage file
     * cannot be accessed and written to.
     */
    public void writeTaskData(List<Task> tasks) throws ConfigurationException {
        if (singletonDukeStorage == null) {
            throw new ConfigurationException("DukeStorage not initialized");
        }

        try {
            FileOutputStream taskDataOutputStream = new FileOutputStream(storageFilePath);
            ObjectOutputStream taskListOutputStream = new ObjectOutputStream(taskDataOutputStream);

            taskListOutputStream.writeObject(tasks);

            taskDataOutputStream.close();
            taskListOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new ConfigurationException("Unable to access storage file");
        } catch (IOException e) {
            throw new ConfigurationException("Unable to write to storage file");
        }
    }
}
