package storage;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import tasklist.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JsonParser {

    private String filepath;
    private TaskList taskList;

    /**
     * Constructor that checks if the file exists and creates a new json formatted file if it.
     * @param filepath for the location of the storage file
     * @throws IOException in the case of a missing file
     */
    public JsonParser(String filepath) throws IOException {
        this.filepath = filepath;
        File file = new File(filepath);
        if (file.createNewFile()) {
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write("{}");
            fileWriter.close();
        }

    }

    /**
     * Method to store data in the Json format.
     * @param tasks contains the tasklist to be stored.
     * @throws IOException in the case of a missing file
     */
    public void storeData(TaskList tasks) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(filepath),tasks);
    }

    /**
     * Method to load data from the Json formatted file.
     * @return returns the tasklist class with all the saveddata loaded
     * @throws IOException in the case of missing file
     */
    public TaskList readData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        taskList = mapper.readValue(new FileInputStream(filepath), TaskList.class);
        return taskList;
    }

}
