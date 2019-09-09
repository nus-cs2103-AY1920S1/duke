package duke.command;

import java.util.ArrayList;
import java.util.Collections;

public class CommandResult {
    // FIXME: should have only one string instead of a list
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> warnings = new ArrayList<>();
    private boolean exit = false;

    public CommandResult(String... messages) {
        this(messages, null);
    }

    /**
     * Constructs a CommandResult.
     *
     * @param messages the success messages
     * @param warnings the warnings
     */
    public CommandResult(String[] messages, String[] warnings) {
        if (messages != null) {
            Collections.addAll(this.messages, messages);
        }
        if (warnings != null) {
            Collections.addAll(this.warnings, warnings);
        }
    }

    public boolean isExit() {
        return this.exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean hasMessages() {
        return this.messages.size() != 0;
    }

    public boolean hasWarnings() {
        return this.warnings.size() != 0;
    }

    public void addMessages(String... messages) {
        Collections.addAll(this.messages, messages);
    }

    public void addWarnings(String... warnings) {
        Collections.addAll(this.warnings, warnings);
    }

    public ArrayList<String> getMessages() {
        return new ArrayList<>(this.messages);
    }

    public ArrayList<String> getWarnings() {
        return new ArrayList<>(this.warnings);
    }
}
