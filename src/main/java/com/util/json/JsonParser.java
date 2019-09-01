package com.util.json;

import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.TaskTypes;
import com.tasks.Todo;
import com.util.Printer;
import com.util.datetime.DateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonParser {

    public static ArrayList<DoableTask> parseJsonFile(String input) {
        if (input.length() == 0) {
            return new ArrayList<>();
        }
        try {
            ArrayList<DoableTask> arr = new ArrayList<>();
            for (DynamicValue o : parseJsonArray(input.toCharArray(), 0).snd) {
                if (o.getType() != ValueTypes.OBJECT) {
                    throw new SaveFileFormatException(
                            "JSON array immediate elements must be tasks");
                } else {
                    HashMap<String,DynamicValue> obj = o.getObject();
                    DynamicValue task;
                    HashMap<String,DynamicValue> attributes;
                    TaskTypes type;
                    if ((task = obj.get(Schema.TASK_TODO)) != null) {
                        type = TaskTypes.TODO;
                    } else if ((task = obj.get(Schema.TASK_EVENT)) != null) {
                        type = TaskTypes.EVENT;
                    } else if ((task = obj.get(Schema.TASK_DEADLINE)) != null) {
                        type = TaskTypes.DEADLINE;
                    } else {
                        throw new SaveFileFormatException("Unexpected task type");
                    }
                    if (task.getType() != ValueTypes.OBJECT) {
                        throw new SaveFileFormatException("Invalid task format");
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
                        throw new SaveFileFormatException("No name field in task");
                    }
                    // parse name
                    if (attributes.get(Schema.ATTR_DONE) != null
                            && attributes.get(Schema.ATTR_DONE).getType() == ValueTypes.BOOLEAN) {
                        isDone = attributes.get(Schema.ATTR_DONE).getBoolean();
                    } else {
                        throw new SaveFileFormatException("No done field in task");
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
                    }

                    if (t == null) {
                        throw new SaveFileFormatException("Invalid task format");
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
        } catch (SaveFileFormatException e) {
            Printer.printError(
                    "Your save file is not in the expected format\n error: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Parses from input[i] onwards as a json array.
     *
     * @param input input character array
     * @param i     index to start parsing
     * @return resulting index and objects array
     * @throws SaveFileFormatException file format error
     */
    private static Pair<Integer,ArrayList<DynamicValue>> parseJsonArray(char[] input, int i)
            throws SaveFileFormatException {
        ArrayList<DynamicValue> arr = new ArrayList<>();
        i = skipWhiteSpace(input, i);
        if (input[i] != '[') {
            throw new SaveFileFormatException("Expecting [ at " + i, 2);
        }
        // find '['

        i = skipWhiteSpace(input, i + 1);
        if (i >= input.length) {
            throw new SaveFileFormatException("Empty array did not close");
        }
        // search first value

        boolean isFirst = true;
        while (input[i] != ']') {
            if (!isFirst) {
                i = skipWhiteSpace(input, i);
                if (input[i] != ',') {
                    throw new SaveFileFormatException("Value pairs must be comma separated");
                }
                i = skipWhiteSpace(input, i + 1);
            }
            // advance ',' between value pairs

            Pair<Integer,DynamicValue> valuePair = processDynamicValue(input, i);
            i = skipWhiteSpace(input, valuePair.fst);
            arr.add(valuePair.snd);
            // parse value

            isFirst = false;
        }
        // process key value pairs

        i = skipWhiteSpace(input, i);
        if (i >= input.length || input[i] != ']') {
            throw new SaveFileFormatException("Array did not close");
        }
        // find ']'
        return new Pair<>(i + 1, arr);
    }

    private static Pair<Integer,HashMap<String,DynamicValue>> parseJsonObject(char[] input, int i)
            throws SaveFileFormatException {
        HashMap<String,DynamicValue> obj = new HashMap<>();
        i = skipWhiteSpace(input, i);
        if (input[i] != '{') {
            throw new SaveFileFormatException("Expecting { at " + i, 2);
        }
        // find '{'

        i = skipWhiteSpace(input, i + 1);
        if (i >= input.length) {
            throw new SaveFileFormatException("Empty object did not close");
        }
        // search first key

        boolean isFirst = true;
        while (input[i] != '}') {
            if (!isFirst) {
                i = skipWhiteSpace(input, i);
                if (input[i] != ',') {
                    throw new SaveFileFormatException("Key value pairs must be comma separated");
                }
                i = skipWhiteSpace(input, i + 1);
            }
            // advance ',' between key value pairs

            String key;
            try {
                Pair<Integer,String> keyPair = parseJsonString(input, i);
                i = keyPair.fst;
                key = keyPair.snd;
            } catch (SaveFileFormatException ignored) {
                throw new SaveFileFormatException("Object keys must be strings at " + i);
            }
            // parse key

            skipWhiteSpace(input, i);
            if (input[i] != ':') {
                throw new SaveFileFormatException("Expected : after key name at " + i);
            }
            i = skipWhiteSpace(input, i + 1);
            // find ':'

            Pair<Integer,DynamicValue> valuePair = processDynamicValue(input, i);
            i = skipWhiteSpace(input, valuePair.fst);
            obj.put(key, valuePair.snd);
            // parse value

            isFirst = false;
        }

        i = skipWhiteSpace(input, i);
        if (i >= input.length || input[i] != '}') {
            throw new SaveFileFormatException("Object did not close");
        }
        // find '}'
        return new Pair<>(i + 1, obj);
    }

    private static Pair<Integer,DynamicValue> processDynamicValue(char[] input, int i)
            throws SaveFileFormatException {
        Pair<Integer,DynamicValue> obj;
        try {
            Pair<Integer,Integer> res1 = parseJsonInt(input, i);
            obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
        } catch (SaveFileFormatException e1) {
            try {
                Pair<Integer,Double> res1 = parseJsonDouble(input, i);
                obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
            } catch (SaveFileFormatException e2) {
                try {
                    Pair<Integer,Boolean> res1 = parseJsonBoolean(input, i);
                    obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
                } catch (SaveFileFormatException e3) {
                    try {
                        Pair<Integer,String> res1 = parseJsonString(input, i);
                        obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
                    } catch (SaveFileFormatException e4) {
                        try {
                            Pair<Integer,HashMap<String,DynamicValue>> res1 = parseJsonObject(input,
                                    i);
                            obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
                        } catch (SaveFileFormatException e5) {
                            try {
                                Pair<Integer,ArrayList<DynamicValue>> res1 = parseJsonArray(input,
                                        i);
                                obj = new Pair<>(res1.fst, new DynamicValue(res1.snd));
                            } catch (SaveFileFormatException e6) {
                                if (e3.errorCode() == 2 && e4.errorCode() == 2
                                        && e5.errorCode() == 2 && e6.errorCode() == 2) {
                                    throw new SaveFileFormatException(
                                            "input at " + i + " is of unknown format");
                                } else if (e4.errorCode() == 2 && e5.errorCode() == 2
                                        && e6.errorCode() == 2) {
                                    throw e3;
                                } else if (e5.errorCode() == 2 && e6.errorCode() == 2) {
                                    throw e4;
                                } else if (e6.errorCode() == 2) {
                                    throw e5;
                                } else {
                                    throw e6;
                                }
                            }
                        }
                    }
                }
            }
        }
        return obj;
    }

    private static Pair<Integer,Integer> parseJsonInt(char[] input, int i)
            throws SaveFileFormatException {
        StringBuilder value = new StringBuilder();
        while (i < input.length && Character.isDigit(input[i]) || input[i] == '-') {
            value.append(input[i]);
            i++;
        }
        if (value.length() > 0 && checkIfLegalAfterValue(input[i])) {
            try {
                return new Pair<>(i, Integer.parseInt(value.toString()));
            } catch (NumberFormatException ignored) {
                throw new SaveFileFormatException("String ending at " + i + " is not an Integer");
            }
        }
        throw new SaveFileFormatException(
                "Expected Integer but encountered something else at " + i);
    }

    private static Pair<Integer,Double> parseJsonDouble(char[] input, int i)
            throws SaveFileFormatException {
        StringBuilder value = new StringBuilder();
        while (i < input.length && Character.isDigit(input[i]) || input[i] == 'e' || input[i] == '.'
                || input[i] == '-') {
            value.append(input[i]);
            i++;
        }
        if (value.length() > 0 && checkIfLegalAfterValue(input[i])) {
            try {
                return new Pair<>(i, Double.parseDouble(value.toString()));
            } catch (NumberFormatException ignored) {
                throw new SaveFileFormatException("String ending at " + i + " is not a Double");
            }
        }
        throw new SaveFileFormatException("Expected Double but encountered something else at " + i);
    }

    private static Pair<Integer,Boolean> parseJsonBoolean(char[] input, int i)
            throws SaveFileFormatException {
        boolean value;
        if (input[i] != 't' && input[i] != 'f') {
            throw new SaveFileFormatException(
                    "Expected Boolean but encountered something else at " + i, 2);
        }
        if (i + 3 < input.length && input[i] == 't' && input[i + 1] == 'r' &&
                input[i + 2] == 'u' && input[i + 3] == 'e') {
            value = true;
            i += 4;
        } else if (i + 4 < input.length && input[i] == 'f' && input[i + 1] == 'a'
                && input[i + 2] == 'l' && input[i + 3] == 's' && input[i + 4] == 'e') {
            value = false;
            i += 5;
        } else {
            throw new SaveFileFormatException(
                    "Expected Boolean but encountered something else at " + i);
        }

        if (!checkIfLegalAfterValue(input[i])) {
            throw new SaveFileFormatException(
                    "Expected Boolean but encountered something else at " + i);
        }

        return new Pair<>(i, value);
    }

    private static Pair<Integer,String> parseJsonString(char[] input, int i)
            throws SaveFileFormatException {
        StringBuilder value = new StringBuilder();
        boolean escape = false;
        i = skipWhiteSpace(input, i);
        if (input[i] != '"') {
            throw new SaveFileFormatException(
                    "Expected starting double quotes for string but encountered something else at "
                            + i, 2);
        }
        i++;
        while (i < input.length) {
            if (input[i] == '"' && !escape) {
                i++;
                break;
            } else if (input[i] == '\\' && !escape) {
                escape = true;
            } else if (escape && input[i] == '"' || input[i] == '\\') {
                value.append(input[i]);
                escape = false;
            } else {
                value.append(input[i]);
            }
            i++;
        }
        if (i >= input.length) {
            throw new SaveFileFormatException("String did not terminate with double quotes");
        }
        if (!checkIfLegalAfterValue(input[i])) {
            throw new SaveFileFormatException(
                    "Expected string but encountered something else at " + i);
        }
        return new Pair<>(i, value.toString());
    }

    private static boolean checkIfLegalAfterValue(char c) {
        return Character.isWhitespace(c) || c == ',' || c == ']' || c == '}' || c == ':';
    }

    private static int skipWhiteSpace(char[] input, int i) {
        while (i < input.length && Character.isWhitespace(input[i])) {
            i++;
        }
        return i;
    }
}
