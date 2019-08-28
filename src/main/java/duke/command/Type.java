package duke.command;

import java.util.ArrayList;

/**
 * Enumeration of the type of commands which can be entered into the user interface to manipulate the task list
 */
public enum Type {
    EXIT(0),
    LIST(0),
    DELETE(1, "task number"),
    COMPLETE(1, "task number"),
    ADD_TODO(1, "description"),
    ADD_DEADLINE(2, "description", "time", "/by"),
    ADD_EVENT(2, "description", "time", "/at");

    Type(int parametersExpected, String... parameterNamesAndDelimiters) {
        this.parametersExpected = parametersExpected;
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

    /**
     * Returns the number of parameters expected for the command of the Type provided
     *
     * @param type The type of the command for which the number of parameters expected is needed
     * @return The number of parameters expected
     */
    public static int getNumberOfParametersExpectedFor(Type type) {
        return type.parametersExpected;
    }

    /**
     * Returns an ArrayList of the names of the parameters of the for the Type of command
     *
     * @param type The Type of command which the names of the parameters is required from
     * @return An ArrayList of the parameter names
     */
    public static ArrayList<String> getParametersFor(Type type) {
        return new ArrayList<String>(type.parameters);
    }

    /**
     * Returns the delimiters used to separate multiple arguments for commands of the Type provided
     *
     * @param type The Type of command
     * @return An ArrayList of the delimiters
     */
    public static ArrayList<String> getDelimitersFor(Type type) {
        return new ArrayList<String>(type.delimiters);
    }
}