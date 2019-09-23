package duke.task.creation;

/**
 * Container class for the regex used by the program to identify valid DateTime arguments in the user's input.
 */
class RecognizedDateTime {
    static final String DATE_TIME_REGEX = "("
            + "("
            + "([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})"
            + "(\\s([0-9]{4}))?"
            + ")"
            + "|"
            + "("
            + "(?i)"
            + "Today|Tomorrow|"
            + "Mon|Monday|Tue|Tues|Tuesday|Wed|Wednesday|Thu|Thursday|Fri|Friday|Sat|Saturday|Sun|Sunday"
            + ")"
            + "(\\s([0-9]{4}))?"
            + ")";
}
