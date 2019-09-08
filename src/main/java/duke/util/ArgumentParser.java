package duke.util;

import duke.DukeException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Parser for {@link duke.command.Command} argument lists. It can convert an entire raw argument list into a multimap
 * between switch names and switch argument lists. Both required and optional switches are supported.
 */
public class ArgumentParser {
    protected Set<String> requiredSwitches = new TreeSet<>();
    protected Set<String> optionalSwitches = new TreeSet<>();

    /**
     * Registers the specified String as a switch while specifying if it is required or optional.
     * Commands missing required switches will cause an exception upon parsing.
     *
     * @param name        the String that uniquely identifies this switch
     * @param isRequired  boolean specifying if the switch is required
     */
    public void register(String name, boolean isRequired) {
        if (isRequired) {
            requiredSwitches.add(name);
        } else {
            optionalSwitches.add(name);
        }
    }

    /**
     * Parses the argument list into a multimap between switch names and switch argument lists. The command name is
     * taken as the first key of the multimap. Switches must have been registered using
     * {@link #register(String, boolean)} before parsing.
     *
     * @param args            the command argument list as a String[]
     * @return                a map with switch names as keys and switch argument lists as values
     * @throws DukeException  if there are repeated switches or if required switches are missing
     */
    public Map<String, String[]> parse(String[] args) throws DukeException {
        Set<String> requiredSwitchesRemaining = new TreeSet<>(requiredSwitches);
        Set<String> optionalSwitchesRemaining = new TreeSet<>(optionalSwitches);
        Map<String, String[]> switchArgs = new HashMap<>();

        int switchStartIndex = 0;
        for (int i = 1; i < args.length; i++) { // command name is an implied switch
            boolean isSwitch = false;
            if (requiredSwitches.contains(args[i])) {
                if (!requiredSwitchesRemaining.remove(args[i])) {
                    throw new DukeException("Repeated switch " + args[i]);
                }
                isSwitch = true;
            } else if (optionalSwitches.contains(args[i])) {
                if (!optionalSwitchesRemaining.remove(args[i])) {
                    throw new DukeException("Repeated switch " + args[i]);
                }
                isSwitch = true;
            }
            if (isSwitch) {
                try {
                    // This allows for switches with no arguments
                    switchArgs.put(args[switchStartIndex], Arrays.copyOfRange(args, switchStartIndex + 1, i));
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Bad arguments for switch " + args[switchStartIndex]);
                }
                switchStartIndex = i;
            }
        }
        // Add last switch
        switchArgs.put(args[switchStartIndex], Arrays.copyOfRange(args, switchStartIndex + 1, args.length));

        if (!requiredSwitchesRemaining.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String s : requiredSwitchesRemaining) {
                sb.append(s);
                sb.append(" ");
            }
            sb.append(requiredSwitchesRemaining.size() == 1 ? "is a required switch" : "are required switches");
            throw new DukeException(sb.toString());
        }
        return switchArgs;
    }

    /**
     * Returns a String formed by concatenating each String in the specified array in sequence while inserting a space
     * (<code>" "</code>) between each.
     *
     * @param strings  the String[] to concatenate
     * @return         the concatenated String
     */
    public static String concatenate(String[] strings) {
        return concatenate(strings, 0, strings.length);
    }

    /**
     * Returns a String formed by concatenating the Strings in the array within the specified range in sequence while
     * inserting a space (<code>" "</code>) between each.
     *
     * @param strings    the String[] containing the range to concatenate
     * @param fromIndex  the start index, inclusive
     * @param toIndex    the end index, exclusive
     * @return           the concatenated String
     */
    public static String concatenate(String[] strings, int fromIndex, int toIndex) {
        assert fromIndex <= toIndex : "fromIndex cannot be greater than toIndex";
        StringBuilder sb = new StringBuilder();
        for (int i = fromIndex; i < toIndex; i++) {
            sb.append(strings[i]);
            if (i != toIndex - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
