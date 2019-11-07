package duke.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Aliases implements Serializable {
    public static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "bye", "list", "done", "delete", "todo", "deadline", "event", "find", "alias add", "alias delete",
            "alias view", "alias list", "alias all"));

    private HashMap<String, String> aliases = new HashMap<>() {{
            put("b","bye");
            put("exit","bye");
            put("l","list");
            put("dn","done");
            put("de","delete");
            put("t","todo");
            put("dd","deadline");
            put("e","event");
            put("f","find");
            put("aa","alias add");
            put("ad","alias delete");
            put("av","alias view");
            put("al","alias list");
            put("aall","alias all");
        }};

    /**
     * Add a new alias for a keyword.
     * @param alias alias of the keyword
     * @param keyword the full keyword mapped by the alias
     */
    public void addAlias(String alias, String keyword) {
        aliases.put(alias, keyword);
    }

    /**
     * Check whether the alias exit in the application.
     * @param alias the alias to be checked
     * @return a boolean indicating whether the alias exists
     */
    public boolean containsAlias(String alias) {
        return aliases.containsKey(alias);
    }

    /**
     * Get a keyword using its alias.
     * @param alias alias of the keyword
     * @return full keyword mapped by the alias
     */
    public String getKeyword(String alias) {
        return aliases.get(alias);
    }

    /**
     * Remove an alias from the application.
     * @param alias the alias to be removed
     */
    public void removeAlias(String alias) {
        aliases.remove(alias);
    }

    /**
     * Get all aliases available in the project.
     * @return a set containing all aliases
     */
    public Set<String> getAllAliases() {
        return aliases.keySet();
    }

    /**
     * Get all aliases mapped to the given keyword.
     * @param keyword keyword used to map alias
     * @return a List of String containing all aliases mapped to the keyword
     */
    public List<String> getAliases(String keyword) {
        List<String> matchingAliases = new ArrayList<String>();
        for (String alias: aliases.keySet()) {
            if (aliases.get(alias).equals(keyword)) {
                matchingAliases.add(alias);
            }
        }
        return matchingAliases;
    }
}
