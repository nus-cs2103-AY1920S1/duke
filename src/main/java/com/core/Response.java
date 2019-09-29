package com.core;

import com.core.savedata.SaveFile;
import com.util.datetime.DateTime;
import com.util.datetime.DateTimeParse;
import java.util.stream.IntStream;

import com.util.Printer;
import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.Todo;
import java.util.stream.Stream;

public enum Response {
    BYE("(?i)^b(ye)?\\s*", (i, s) -> {
        Printer.printString("Bye. Hope to see you again soon!");
        s.toExit = true;
        s.lastError = false;
        return true;
    }),
    LIST("(?i)^l(ist)?\\s*", (j, s) -> {
        String finalString = listIndexStreamToString(IntStream.range(0, s.list.size()).boxed(), s);
        Printer.printString(finalString.equalsIgnoreCase("") ? "You have no tasks" : finalString);
        s.lastError = false;
        return true;
    }),
    LIST_SCHEDULE("(?i)^l(ist)? \\d{1,2}/\\d{1,2}/\\d{1,4}$", (i, s) -> {
        DateTime time = DateTime.parseString(i.split(" ")[1] + " 0000");
        DateTime time2 = time.clone();
        time2.add(new DateTimeParse("1/0/0 0000"));
        String finalString = listIndexStreamToString(IntStream.range(0,
                s.list.size()).boxed().filter((ti) -> {
                    DoableTask t = s.list.get(ti);
                    return (t instanceof Deadline
                            && ((Deadline)t).deadline.compareTo(time) >= 0
                            && ((Deadline)t).deadline.compareTo(time2) <= 0)
                            || (t instanceof Event
                            && ((((Event)t).startDate.compareTo(time) >= 0
                            && ((Event)t).startDate.compareTo(time2) <= 0)
                            || (((Event)t).endDate.compareTo(time) >= 0)
                            && ((Event)t).endDate.compareTo(time2) <= 0));

                }), s);
        Printer.printString(finalString.equalsIgnoreCase("") ? "No tasks for that day" : finalString);
        s.lastError = false;
        return true;
    }),
    LIST_FIND("(?i)^l(ist)? .+", (i, s) -> {
        String substr = i.split(" ", 2)[1];
        String finalString = listIndexStreamToString(IntStream.range(0,
                s.list.size()).boxed().filter((ti) -> s.list.get(ti).getName().contains(substr)), s);
        Printer.printString(finalString.equalsIgnoreCase("") ? "No results" : finalString);
        s.lastError = false;
        return true;
    }),
    SNOOZE_DATETIME("(?i)^snooze [0-9]+ \\d{1,2}/\\d{1,2}/\\d{1,4} \\d{4}$", (i, s) -> {
        int index = getNumber(i) - 1;
        System.out.println(index);
        if (checkValidIndex(index, s)) {
            DoableTask t = s.list.get(index);
            String[] parts = i.split(" ");
            DateTimeParse time = new DateTimeParse(parts[2] + " " + parts[3]);
            System.out.println(time.toString());
            if (t instanceof Deadline) {
                ((Deadline)t).deadline.add(time);
                Printer.printString("Deadline has been postponed\n " + t.toString());
                save(s);
            } else if (t instanceof Event) {
                ((Event)t).startDate.add(time);
                ((Event)t).endDate.add(time);
                Printer.printString("Event has been postponed\n " + t.toString());
                save(s);
            } else if (t instanceof Todo) {
                Printer.printString("Todo tasks have no datetime associated");
            }
            s.lastError = false;
        } else {
            s.lastError = true;
            return false;
        }
        return true;
    }),
    SNOOZE_WRONG("(?i)^snooze.*", (i, s) -> {
        Printer.printString("Snooze improper format / invalid index" + Printer.referHelp);
        s.lastError = true;
        return true;
    }),
    DONE("(?i)^done [0-9]+", (i, s) -> {
        int index = getNumber(i) - 1;
        if (checkValidIndex(index, s)) {
            s.list.get(index).markAsDone();
            Printer.printString("Nice! I've marked this task as done:\n  "
                    + s.list.get(index).toString());
            save(s);
            s.lastError = false;
            return true;
        }
        s.lastError = true;
        return false;
    }),
    DELETE("(?i)^delete [0-9]+", (i, s) -> {
        int index = getNumber(i) - 1;
        if (checkValidIndex(index, s)) {
            Printer.printString("Noted! I've removed this task:\n  "
                    + s.list.get(index).toString()
                    + "\nNow you have "
                    + (s.list.size() - 1)
                    + " tasks in the list.");
            s.list.remove(index);
            save(s);
            s.lastError = false;
            return true;
        }
        s.lastError = true;
        return false;
    }),
    TODO_NO_NAME("(?i)^t(odo)?\\s*", (i, s) -> {
        Printer.printHelp("The description of a todo cannot be empty");
        s.lastError = true;
        return true;
    }),
    TODO("(?i)^t(odo)? .+", (i, s) -> {
        addTask(new Todo(i.split("t(odo)? ", 2)[1]), s);
        assert s.list.size() >= 1 : "expecting non empty list";
        s.lastError = false;
        return true;
    }),
    EVENT_NO_NAME("(?i)^e(vent)?\\s*$", (i, s) -> {
        Printer.printHelp("The description of an event cannot be empty");
        s.lastError = true;
        return true;
    }),
    EVENT_NO_TIME("^(?i)e(vent)? (((?!/at).)+$)|(.+ /at\\s*$)", (i, s) -> {
        Printer.printHelp("The date range of an event cannot be empty");
        s.lastError = true;
        return true;
    }),
    EVENT("(?i)^e(vent)? .+ /at \\d{1,2}/\\d{1,2}/\\d{4} \\d{4} to \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$", (i, s) -> {
        String[] parts = splitTwoDelimiters(i, "(?i)^e(vent)? ", "(?i)/at ");

        String[] dates = parts[1].split(" to ", 2);
        addTask(new Event(parts[0], DateTime.parseString(dates[0]),
                DateTime.parseString(dates[1])), s);
        assert s.list.size() >= 1 : "expecting non empty list";
        s.lastError = false;
        return true;
    }),
    EVENT_WRONG_TIME("(?i)^e(vent)? .+ /at .+", (i, s) -> {
        Printer.printError("The date range must be in the format 'DD/MM/YYYY HHMM "
                + "to DD/MM/YYYY HHMM'\nsee 'help' command");
        s.lastError = true;
        return true;
    }),
    DEADLINE_NO_NAME("(?i)^d(eadline)?\\s*", (i, s) -> {
        Printer.printHelp("The description of a deadline cannot be empty");
        s.lastError = true;
        return true;
    }),
    DEADLINE_NO_TIME("(?i)^d(eadline)? (((?!/by).)+$)|(.+ /by\\s*$)", (i, s) -> {
        Printer.printHelp("The due date of a deadline cannot be empty");
        s.lastError = true;
        return true;
    }),
    DEADLINE("(?i)^d(eadline)? .+ /by \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$", (i, s) -> {
        String[] parts = splitTwoDelimiters(i, "(?i)^d(eadline)? ", "(?i)/by ");
        addTask(new Deadline(parts[0], DateTime.parseString(parts[1])), s);
        assert s.list.size() >= 1 : "expecting non empty list";
        s.lastError = false;
        return true;
    }),
    DEADLINE_WRONG_TIME("(?i)^d(eadline)? .+ /by .+", (i, s) -> {
        Printer.printHelp("The date must be in the format 'DD/MM/YYYY HHMM'");
        s.lastError = true;
        return true;
    }),
    HELP("(?i)^h(elp)?\\s*", (i, s) -> {
        Printer.printString("The following are legal commands:\n\n"
                + "bye\n"
                + " - quits the program\n"
                + "list\n"
                + " - lists all the tasks\n"
                + "list <date>\n"
                + " - lists schedule for the day\n"
                + "list <string>\n"
                + " - finds tasks name containing string\n"
                + "done <index>\n"
                + " - marks task at valid index as done\n"
                + "delete <index>\n"
                + " - deletes task at valid index\n"
                + "todo <name>\n"
                + " - adds a todo task\n"
                + "event <name> /at <datetime> to <datetime>\n"
                + " - adds an event task\n"
                + "deadline <name> /by <datetime>\n"
                + " - adds a deadline task\n"
                + "snooze <index> <datetime>\n"
                + " - postpones deadline or event by datetime amount\n"
                + "\ndatetime is of 'DD/MM/YY HHMM' format");
        s.lastError = false;
        return true;
    }),
    UNKNOWN(".*", (i, s) -> {
        Printer.printHelp("I'm sorry but I don't know what that means :-(");
        s.lastError = true;
        return true;
    });

    private String regex;
    private ResponseFunc func;

    Response(String r, ResponseFunc f) {
        regex = r;
        func = f;
    }

    /**
     * Given a string and program state, if string matches regex this enum will call its response
     * function.
     *
     * @param i input string
     * @param s state object
     * @return boolean if the string has matched
     */
    public boolean call(String i, State s) {
        if (i.matches(regex)) {
            return func.funcCall(i, s);
        }
        return false;
    }

    /**
     * Assuming input is ".+ [0-9]+", it splits at whitespace and returns an Integer of the second
     * part of the string.
     *
     * @param input input string
     * @return integer of second part of string
     */
    private static int getNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Given an index and state object, check if index in bounds of list in state object.
     *
     * @param index index to check
     * @param s     state object
     * @return False if out of bounds
     */
    private static boolean checkValidIndex(int index, State s) {
        if (index < 0 || index > s.list.size() - 1) {
            Printer.printError("That is not a valid task index");
            return false;
        }
        return true;
    }

    /**
     * Assuming input is "(head).*(mid).*" returns the two texts between them.
     *
     * @param input input string
     * @param head  head regex match
     * @param mid   mid regex match
     * @return array of two text parts
     */
    private static String[] splitTwoDelimiters(String input, String head, String mid) {
        String[] parts = input.split(mid, 2);
        parts[0] = parts[0].split(head)[1];
        return parts;
    }

    /**
     * Insert task into list and prints message string.
     *
     * @param t task to be added
     * @param s state object
     */
    private static void addTask(DoableTask t, State s) {
        s.list.add(t);
        Printer.printString("Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + s.list.size()
                + " tasks in the list.");
        save(s);
    }

    private static String listIndexStreamToString(Stream<Integer> indices, State s) {
        return indices.reduce("", (acc, ti) -> acc
                + ((acc.equalsIgnoreCase("") ? "" : "\n")
                + (ti + 1)
                + "."
                + s.list.get(ti).toString()), String::concat);
    }

    /**
     * Save state to JsonFile.
     * @param s state
     */
    private static void save(State s) {
        SaveFile.write(s.list);
    }
}