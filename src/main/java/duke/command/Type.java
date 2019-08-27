package duke.command;

import java.util.ArrayList;

public enum Type {
    UNUSED(0),
    EXIT(0),
    LIST(0),
    DELETE(1, "task number"),
    COMPLETE(1, "task number"),
    ADD_TODO(1, "description"),
    ADD_DEADLINE(2, "description", "time", "/by"),
    ADD_EVENT(2, "description", "time", "/at");

    Type(int parametersExpected, String... parameterNamesAndDelimiters) {
        parameters = new ArrayList<String>();
        delimiters = new ArrayList<String>();
        for (int i = 0; i < parameterNamesAndDelimiters.length; i++) {
            if (i < parametersExpected) {
                parameters.add(parameterNamesAndDelimiters[i]);
            } else {
                delimiters.add(parameterNamesAndDelimiters[i]);
            }
        }
    }

    private int parametersExpected;
    private ArrayList<String> parameters;
    private ArrayList<String> delimiters;

    public static int getNumberOfParametersExpectedFor(Type type) {
        return type.parametersExpected;
    }

    public static ArrayList<String> getParametersFor(Type type) {
        return new ArrayList<String>(type.parameters);
    }

    public static ArrayList<String> getDelimitersFor(Type type) {
        return new ArrayList<String>(type.delimiters);
    }
}