package com.core;

import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.TaskTypes;
import com.tasks.Todo;
import com.util.Printer;
import com.util.datetime.DateTime;
import com.util.json.JsonFormatException;
import com.util.json.JsonObject;
import com.util.json.JsonParser;
import com.util.json.JsonValue;
import com.util.json.JsonWrongValueTypeException;
import com.util.json.ReadWriteFiles;
import com.util.json.Schema;
import com.util.json.ValueTypes;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SaveFile {
    private static final String SAVE_FILE = "./data/duke.json";

    public static void write(String content) {
        ReadWriteFiles.write(SAVE_FILE, content);
    }

    public static ArrayList<DoableTask> loadTasks() {
        try {
            return ReadWriteFiles.read(SAVE_FILE);
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    /**
     * Given save file's input string, generate list. Empty list if erroneous or empty string does
     * not exist.
     *
     * @param input json file string
     * @return list
     */
    public static ArrayList<DoableTask> parseJsonFile(String input) {
        if (input.length() == 0) {
            return new ArrayList<>();
        }
        try {
            try {
                for (JsonValue obj : JsonParser.parseJsonString(input).getArray()) {
                    JsonValue attrObj;
                    if ((attrObj = obj.getObject().get(Schema.TASK_TODO)) != null) {

                    } else if ((attrObj = obj.getObject().get(Schema.TASK_EVENT)) != null) {

                    } else if ((attrObj = obj.getObject().get(Schema.TASK_DEADLINE)) != null) {

                    } else {

                    }
                }
            } catch (JsonWrongValueTypeException e) {
                Printer.printError(e.getMessage());
            }
        } catch (JsonFormatException e) {
            Printer.printError("Save File has errors\n" + e.getMessage());
        }



        try {
            ArrayList<DoableTask> arr = new ArrayList<>();
            for (JsonValue o : parseJsonArray(input.toCharArray(), 0).snd) {
                if (o.getType() != ValueTypes.OBJECT) {
                    throw new JsonFormatException(
                            "JSON array immediate elements must be tasks");
                } else {
                    JsonObject obj = o.getObject();
                    JsonValue task;
                    JsonObject attributes;
                    TaskTypes type;
                    if ((task = obj.get(Schema.TASK_TODO)) != null) {
                        type = TaskTypes.TODO;
                    } else if ((task = obj.get(Schema.TASK_EVENT)) != null) {
                        type = TaskTypes.EVENT;
                    } else if ((task = obj.get(Schema.TASK_DEADLINE)) != null) {
                        type = TaskTypes.DEADLINE;
                    } else {
                        throw new JsonFormatException("Unexpected task type");
                    }
                    if (task.getType() != ValueTypes.OBJECT) {
                        throw new JsonFormatException("Invalid task format");
                    }
                    attributes = task.getObject();
                    // parse task object

                    DoableTask t = null;
                    String name;
                    boolean isDone;
                    if (attributes.get(Schema.ATTR_NAME) != null
                            && attributes.get(Schema.ATTR_NAME).getType() == ValueTypes.STRING) {
                        name = attributes.get(Schema.ATTR_NAME).getString();
                    } else {
                        throw new JsonFormatException("No name field in task");
                    }
                    // parse name
                    if (attributes.get(Schema.ATTR_DONE) != null
                            && attributes.get(Schema.ATTR_DONE).getType() == ValueTypes.BOOLEAN) {
                        isDone = attributes.get(Schema.ATTR_DONE).getBoolean();
                    } else {
                        throw new JsonFormatException("No done field in task");
                    }
                    // parse done
                    switch (type) {
                    case TODO:
                        t = new Todo(name);
                        break;
                    case EVENT:
                        if (attributes.get(Schema.ATTR_EVENT_START) != null
                                && attributes.get(Schema.ATTR_EVENT_START).getType()
                                == ValueTypes.STRING
                                && attributes.get(Schema.ATTR_EVENT_END) != null
                                && attributes.get(Schema.ATTR_EVENT_END).getType()
                                == ValueTypes.STRING) {
                            t = new Event(name,
                                    DateTime.parseString(
                                            attributes.get(Schema.ATTR_EVENT_START).getString()),
                                    DateTime.parseString(
                                            attributes.get(Schema.ATTR_EVENT_END).getString()));
                        }
                        break;
                    case DEADLINE:
                        if (attributes.get(Schema.ATTR_DEADLINE_DUE) != null
                                && attributes.get(Schema.ATTR_DEADLINE_DUE).getType()
                                == ValueTypes.STRING) {
                            t = new Deadline(name, DateTime.parseString(
                                    attributes.get(Schema.ATTR_DEADLINE_DUE).getString()));
                        }
                        break;
                    default:
                        throw new JsonFormatException("Unexpected task type");
                    }

                    if (t == null) {
                        throw new JsonFormatException("Invalid task format");
                    } else {
                        if (isDone) {
                            t.markAsDone();
                        }
                        arr.add(t);
                    }
                    // parse task attributes object
                }
            }
            //TODO specify error content in error message
            return arr;
        } catch (JsonFormatException e) {
            Printer.printError(
                    "Your save file is not in the expected format\n error: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
