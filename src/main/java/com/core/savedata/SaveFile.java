package com.core.savedata;

import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.Todo;
import com.util.Printer;
import com.util.datetime.DateTime;
import com.util.json.JsonArray;
import com.util.json.JsonFormatException;
import com.util.json.JsonParser;
import com.util.json.JsonValue;
import com.util.json.JsonWrongValueTypeException;
import com.util.json.ReadWriteFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveFile {

    private static boolean isRootResolved = false;

    private static final String SAVE_FILE = "./data/duke.json";
    private static String root;

    /**
     * Determine root directory of the application, duke for project, directory containing jar
     * for jar files.
     */
    private static void resolveRoot() {
        URL thisClassUrl = SaveFile.class.getResource("SaveFile.class");

        switch (thisClassUrl.getProtocol()) {
        case "file":
            root = Paths.get(thisClassUrl.getPath()).resolve("../../../../../../../../").normalize().toString();
            break;
        case "jar":
            try {
                root = Paths.get(new File(
                        SaveFile.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                        .getPath()).resolve("../").normalize().toString();
            } catch (URISyntaxException e) {
                System.out.println("jar is broken as unable to resolve path");
                System.exit(-1);
            }
            break;
        default:
            root = System.getProperty("user.dir");
        }

        isRootResolved = true;
    }

    /**
     * Given a list of tasks, convert it to json and write to save file.
     * @param list  list of tasks
     */
    public static void write(ArrayList<DoableTask> list) {
        if (!isRootResolved) {
            resolveRoot();
        }
        JsonArray arr = new JsonArray();
        for (DoableTask t : list) {
            arr.add(t.toJson());
        }
        ReadWriteFiles.write(Paths.get(root).resolve(SAVE_FILE).normalize().toString(), arr.toString());
    }

    /**
     * Reads the save file and if successful parses into tasks.
     * @return  list of tasks or empty if erroneous or file does not exist
     */
    public static ArrayList<DoableTask> loadTasks() {
        if (!isRootResolved) {
            resolveRoot();
        }
        try {
            return parseJsonFile(ReadWriteFiles.read(Paths.get(root).resolve(SAVE_FILE).normalize().toString()));
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Given save file's input string, generate list. Empty list if erroneous.
     *
     * @param input json file string
     * @return list
     */
    private static ArrayList<DoableTask> parseJsonFile(String input) {
        ArrayList<DoableTask> list = new ArrayList<>();

        if (input.length() == 0) {
            return list;
        }

        try {
            try {
                for (JsonValue obj : JsonParser.parseJsonInput(input).getArray()) {
                    DoableTask task;
                    JsonValue attrObj;
                    if ((attrObj = obj.getObject().get(Schema.TASK_TODO)) != null) {
                        task = new Todo(attrObj.getObject().get(Schema.ATTR_NAME).getString());
                    } else if ((attrObj = obj.getObject().get(Schema.TASK_EVENT)) != null) {
                        task = new Event(attrObj.getObject().get(Schema.ATTR_NAME).getString(),
                                DateTime.parseString(
                                        attrObj.getObject().get(Schema.ATTR_EVENT_START)
                                                .getString()),
                                DateTime.parseString(attrObj.getObject().get(Schema.ATTR_EVENT_END)
                                        .getString()));
                    } else if ((attrObj = obj.getObject().get(Schema.TASK_DEADLINE)) != null) {
                        task = new Deadline(attrObj.getObject().get(Schema.ATTR_NAME).getString(),
                                DateTime.parseString(
                                        attrObj.getObject().get(Schema.ATTR_DEADLINE_DUE)
                                                .getString()));
                    } else {
                        Printer.printError("Unable to load tasks as file does not match schema");
                        return new ArrayList<>();
                    }
                    if (attrObj.getObject().get(Schema.ATTR_DONE).getBoolean()) {
                        task.markAsDone();
                    }
                    list.add(task);
                }
            } catch (JsonWrongValueTypeException e) {
                Printer.printError(
                        "Unable to load tasks as file does not match schema\n" + e.getMessage());
            }
        } catch (JsonFormatException e) {
            Printer.printError("Save File has errors\n" + e.getMessage());
        }
        return list;
    }
}
