public class Command {
    protected final String keyword;
    protected final String args;
    protected final String beforeSlashArgs;
    protected final String slashKeyword;
    protected final String slashArgs;

    /**
     * Parses a command into its keyword and its arguments both before and after the slash, given an input string.
     * Does not ensure that the resulting command is legal.
     * @param input Command input as a string
     */
    public Command(String input) {
        input = input.trim();

        this.keyword = input.split("\\s+", 2)[0];
        this.args = input.split("\\s+").length >= 2 ? input.split("\\s+", 2)[1] : null;

        String inputBeforeSlash = input.split("\\s+/", 2)[0];
        String inputAfterSlash = input.split("\\s+/").length >= 2 ? input.split("\\s+/", 2)[1] : null;

        this.beforeSlashArgs = inputBeforeSlash.split("\\s+").length >= 2 ? inputBeforeSlash.split("\\s+", 2)[1] : null;

        if (inputAfterSlash == null) {
            this.slashKeyword = null;
            this.slashArgs = null;
        } else {
            this.slashKeyword = inputAfterSlash.split("\\s+", 2)[0];
            this.slashArgs = inputAfterSlash.split("\\s+").length >= 2 ? inputAfterSlash.split("\\s+", 2)[1] : null;
        }
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getArgs() {
        return this.args;
    }

    public String getBeforeSlashArgs() {
        return this.beforeSlashArgs;
    }

    public String getSlashKeyword() {
        return this.slashKeyword;
    }

    public String getSlashArgs() {
        return this.slashArgs;
    }
}
