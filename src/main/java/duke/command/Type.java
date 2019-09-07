package duke.command;

import java.util.ArrayList;

/**
 * The enumeration of the type of Commands which can be given to Duke to manipulate/display
 * information about the TaskList.
 *
 * <p>The enumeration also contains details such as the number of parameters that are expected for the
 * Command, the names of the parameters, and the delimiters used to separate multiple the different
 * arguments, if any.</p>
 */
public enum Type {
    COMMAND_EXIT(0),
    COMMAND_SHOW_LIST(0),
    COMMAND_DELETE_TASK(1, "task number"),
    COMMAND_COMPLETE_TASK(1, "task number"),
    COMMAND_SEARCH(1, "keyword"),
    COMMAND_ADD_TODO(1, "description"),
    COMMAND_ADD_DEADLINE(2, "description", "time", "/by"),
    COMMAND_ADD_EVENT(2, "description", "time", "/at");

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
     * Returns the number of parameters expected for the Command of the Type provided.
     *
     * @param type The type of the Command for which the number of parameters expected is needed
     * @return The number of parameters expected for a Command of the Type.
     */
    public static int getNumberOfParametersExpectedFor(Type type) {
        assert type != null;
        return type.parametersExpected;
    }

    /**
     * Returns an ArrayList of the parameter names of the Command of the given Type.
     *
     * @param type The Type of command which the names of the parameters is required from
     * @return An ArrayList of the parameter names
     */
    public static ArrayList<String> getParametersFor(Type type) {
        assert type != null;
        return new ArrayList<String>(type.parameters);
    }

    /**
     * Returns an ArrayList of the delimiters used to separate multiple arguments for Commands of
     * the Type provided.
     *
     * @param type The Type of the Command
     * @return An ArrayList of the delimiters
     */
    public static ArrayList<String> getDelimitersFor(Type type) {
        assert type != null;
        return new ArrayList<String>(type.delimiters);
    }
}