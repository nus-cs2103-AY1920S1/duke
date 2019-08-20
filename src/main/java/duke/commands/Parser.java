package duke.commands;

import duke.DukeException;

import java.util.*;

public class Parser {
    protected Set<String> requiredSwitches = new TreeSet<>();
    protected Set<String> optionalSwitches = new TreeSet<>();
    public void register(String name, boolean required) {
        if(required) requiredSwitches.add(name);
        else optionalSwitches.add(name);
    }
    public Map<String, String[]> parse(String[] args) throws DukeException {
        Set<String> requiredSwitchesRemaining = new TreeSet<>(requiredSwitches);
        Set<String> optionalSwitchesRemaining = new TreeSet<>(optionalSwitches);
        Map<String, String[]> switchArgs = new HashMap<>();

        int switchStartIndex = 0;
        for(int i = 1; i < args.length; i++) { // command name is an implied switch
            boolean isSwitch = false;
            if(requiredSwitches.contains(args[i])) {
                if(!requiredSwitchesRemaining.remove(args[i])) throw new DukeException("Repeated switch " + args[i]);
                isSwitch = true;
            }
            else if(optionalSwitches.contains(args[i])) {
                if(!optionalSwitchesRemaining.remove(args[i])) throw new DukeException("Repeated switch " + args[i]);
                isSwitch = true;
            }
            if(isSwitch) {
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
        switchArgs.put(args[switchStartIndex], Arrays.copyOfRange(args, switchStartIndex+1, args.length));

        if(!requiredSwitchesRemaining.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(String s : requiredSwitchesRemaining) {
                sb.append(s);
                sb.append(" ");
            }
            sb.append(requiredSwitchesRemaining.size() == 1 ? "is a required switch" : "are required switches");
            throw new DukeException(sb.toString());
        }
        return switchArgs;
    }
    public static String concatenate(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if(i != strings.length-1) sb.append(" ");
        }
        return sb.toString();
    }
}
