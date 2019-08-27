package util;

import error.ConfigurationException;
import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DukeStorage {
    private static DukeStorage singletonDukeStorage;
    private String configFilePath;
    private String storageFilePath;
    private List<Task> tasks;

    public static DukeStorage getInstance() throws ConfigurationException {
        if (singletonDukeStorage == null) {
            throw new ConfigurationException("DukeStorage not initialized");
        } else {
            return singletonDukeStorage;
        }
    }

    public static void initializeDukeStorage(String configFilePath) throws ConfigurationException {
        singletonDukeStorage = new DukeStorage();
        singletonDukeStorage.configFilePath = configFilePath;

        singletonDukeStorage.readConfigFile();
        singletonDukeStorage.loadTaskData();
    }

    public List<Task> getTaskData() {
        return tasks;
    }

    private void readConfigFile() throws ConfigurationException {
        try {
            InputStream configFileInputStream;

            if (new File(configFilePath).exists()) {
                configFileInputStream = new FileInputStream(configFilePath);
            } else {
                configFileInputStream = DukeStorage.class.getClassLoader()
                        .getResourceAsStream("config/duke.properties");
            }

            DukeMessage foundConfigFileMessage = new DukeMessage("Config file found...");
            DukeOutput.printMessage(foundConfigFileMessage);

            Properties configFileProperties = new Properties();
            configFileProperties.load(configFileInputStream);
            storageFilePath = configFileProperties.getProperty("duke.storage");

        } catch (IOException e) {
            throw new ConfigurationException("Unable to read config file");
        }
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
