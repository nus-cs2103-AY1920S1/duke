package com.core.savedata;

import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.Todo;
import com.util.Printer;
import com.util.datetime.DateTime;
import com.util.json.JsonFormatException;
import com.util.json.JsonParser;
import com.util.json.JsonValue;
import com.util.json.JsonWrongValueTypeException;
import com.util.json.ReadWriteFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SaveFile {
    private static final String SAVE_FILE = "./data/duke.json";

    public static void write(String content) {
        ReadWriteFiles.write(SAVE_FILE, content);
    }

    public static ArrayList<DoableTask> loadTasks() {
        try {
            return parseJsonFile(ReadWriteFiles.read(SAVE_FILE));
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
                for (JsonValue obj : JsonParser.parseJsonString(input).getArray()) {
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
