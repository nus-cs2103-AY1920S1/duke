package com.core;

import java.util.stream.IntStream;

import com.util.Printer;
import com.tasks.Deadline;
import com.tasks.DoableTask;
import com.tasks.Event;
import com.tasks.Todo;

public enum Response {
    BYE("(?i)^bye\\s*", (i, s) -> {
        Printer.printString("Bye. Hope to see you again soon!");
        s.toExit = true;
    }),
    LIST("(?i)^list\\s*", (j, s) -> {
        String finalString = IntStream.range(0, s.list.size()).boxed().reduce("",
                (acc, ti) -> acc + ((acc.equalsIgnoreCase("") ? "" : "\n")
                        + (ti + 1) + "." + s.list.get(ti).toString()),
                String::concat
        );
        Printer.printString(finalString.equalsIgnoreCase("") ? "You have no tasks" : finalString);
    }),
    DONE("(?i)^done [0-9]+", (i, s) -> {
        int index = getNumber(i) - 1;
        if (checkValidIndex(index, s)) {
            s.list.get(index).markAsDone();
            Printer.printString("Nice! I've marked this task as done:\n  "
                    + s.list.get(index).toString());
        }
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
        }
    }),
    TODO_NO_NAME("(?i)^todo\\s*", (i, s) -> {
        Printer.printError("The description of a todo cannot be empty");
    }),
    TODO("(?i)^todo .+", (i, s) -> {
        addTask(new Todo(i.split("todo ", 2)[1]), s);
    }),
    EVENT_NO_NAME("(?i)^event\\s*", (i, s) -> {
        Printer.printError("The description of an event cannot be empty");
    }),
    EVENT_NO_TIME("^(?i)event (((?!/at).)+$)|(.+ /at\\s*$)", (i, s) -> {
        Printer.printError("The date range of an event cannot be empty");
    }),
    EVENT("(?i)^event .+ /at .+", (i, s) -> {
        String[] parts = splitTwoDelimiters(i, "(?i)^event ", "(?i)/at ");
        addTask(new Event(parts[0], parts[1]), s);
    }),
    DEADLINE_NO_NAME("(?i)^deadline\\s*", (i, s) -> {
        Printer.printError("The description of a deadline cannot be empty");
    }),
    DEADLINE_NO_TIME("(?i)^deadline (((?!/by).)+$)|(.+ /by\\s*$)", (i, s) -> {
        Printer.printError("The due date of a deadline cannot be empty");
    }),
    DEADLINE("(?i)^deadline .+ /by .+", (i, s) -> {
        String[] parts = splitTwoDelimiters(i, "(?i)^deadline ", "(?i)/by ");
        addTask(new Deadline(parts[0], parts[1]), s);
    }),
    UNKNOWN(".*", (i, s) -> {
        Printer.printError("I'm sorry but I don't know what that means :-(");
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
            func.f(i, s);
            return true;
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
        return Integer.parseInt(input.split(" ", 2)[1]);
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
    }
}