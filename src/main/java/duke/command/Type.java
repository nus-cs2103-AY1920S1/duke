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
        this.parametersExpected = parametersExpected;
        this.parameters = new ArrayList<String>();
        this.delimiters = new ArrayList<String>();
        for (int i = 0; i < parameterNamesAndDelimiters.length; i++) {
            if (i < parametersExpected) {
                this.parameters.add(parameterNamesAndDelimiters[i]);
            } else {
                this.delimiters.add(parameterNamesAndDelimiters[i]);
            }
        }
    }

    public final int parametersExpected;
    public ArrayList<String> parameters;
    public ArrayList<String> delimiters;

}