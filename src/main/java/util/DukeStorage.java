package util;

import error.ConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DukeStorage {
    private Properties properties;
    private String storageFilePath;

    public DukeStorage(String configFilePath) throws ConfigurationException {
        properties = new Properties();

        try {
            InputStream input;

            if (new File(configFilePath).exists()) {
                input = new FileInputStream(configFilePath);
            } else {
                input = DukeStorage.class.getClassLoader()
                        .getResourceAsStream("config/duke.properties");
            }

            if (input == null) {
                    throw new ConfigurationException("Unable to locate config file");
            }

            properties.load(input);
            storageFilePath = properties.getProperty("duke.storage");

            DukeMessage storageInitMessage = new DukeMessage("Storage file path found at: ")
                    .append(storageFilePath);

            DukeOutput.printMessage(storageInitMessage);

        } catch (IOException e) {
            throw new ConfigurationException("Unable to read config file");
        }
    }
}
