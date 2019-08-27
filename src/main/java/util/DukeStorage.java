package util;

import error.ConfigurationException;
import task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private void loadTaskData() {
        tasks = new ArrayList<>();
    }
}
