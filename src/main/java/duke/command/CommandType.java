package duke.command;

/**
 * Types of command.
 */
public enum CommandType {
    /**
     * Add command.
     * It includes todo, event and deadline command.
     */
    Add,
    /**
     * Delete command.
     */
    Delete,
    /**
     * Find command.
     */
    Find,
    /**
     * List command.
     */
    List,
    /**
     * Done command.
     */
    Done,
    /**
     * Exit command.
     */
    Exit,
    /**
     * Help command.
     */
    Help;

    public enum SubCommandType {
        /**
         * Todo command, subtype of Add.
         */
        Todo,
        /**
         * Event command, subtype of Add.
         */
        Event,
        /**
         * Deadline command, subtype of Add.
         */
        Deadline,
        /**
         * Command with a status filter, subtype of Delete, Done, List.
         */
        StatusFilter,
        /**
         * Command with a time filter, subtype of Delete, Done, List.
         */
        TimeFilter,
        /**
         * Command with a type filter, subtype of Delete, Done, List.
         */
        TypeFilter,
        /**
         * Command with a index filter, subtype of Delete, Done.
         */
        IndexFilter,
        /**
         * Filter.
         */
        Filter;
    }
}
