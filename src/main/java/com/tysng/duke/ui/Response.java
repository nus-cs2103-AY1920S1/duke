package com.tysng.duke.ui;

import com.tysng.duke.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class formats the output of the Application. It wraps the lines of texts inside the class, and provides a
 * convenient print() method to print to stdout.
 */
public class Response {
    private static final String INDENT = "  ";
    private List<String> content;

    private Response() {
        this.content = new ArrayList<>();
    }

    private Response(List<String> content) {
        this.content = content;
    }

    /**
     * Generates a Greeting type response.
     *
     * @return a response
     */
    public static Response newGreetings() {
        return new Response(List.of(
                "Hello! I'm Duke",
                "What can I do for you?"
        ));
    }

    public static Response newSplash() {
        return new Response(List.of(
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n"
        ));
    }

    /**
     * Generates a Bye type response.
     *
     * @return a response
     */
    public static Response newFarewell() {
        return new Response(List.of(
                "Bye. Hope to see you again soon!"
        ));
    }

    /**
     * Generates a Exception response.
     *
     * @param e the exception to be printed
     * @return a response
     */
    public static Response newException(Exception e) {
        return new Response(List.of(e.getMessage()));
    }

    /**
     * Generates a Add type response.
     *
     * @param item      the item to added
     * @param totalItem count of the total item in the list
     * @return a response
     */
    public static Response newAdded(Task item, int totalItem) {
        return new Response(List.of(
                "Got it. I've added this task: ",
                Response.INDENT + item.toString(),
                String.format("Now you have %d tasks in the list.", totalItem)
        ));
    }

    /**
     * Generates a Done type response.
     *
     * @param item the item marked as done
     * @return a response
     */
    public static Response newDone(Task item) {
        return new Response(List.of(
                "Nice! I've marked this task as done:",
                Response.INDENT + item.toString()
        ));
    }


    /**
     * Generates a Done type response.
     *
     * @param item the item marked as done
     * @return a response
     */
    public static Response newArchive(Task item) {
        return new Response(List.of(
                "This task is archive:",
                Response.INDENT + item.toString()
        ));
    }
    /**
     * Generates a Delete type response.
     *
     * @param item      the item deleted
     * @param totalItem count of the items left
     * @return a response
     */
    public static Response newDelete(Task item, int totalItem) {
        return new Response(List.of(
                "Noted. I've removed this task: ",
                Response.INDENT + item.toString(),
                String.format("Now you have %d tasks in the list.", totalItem)
        ));
    }

    private static Response listing(List<Task> items, String prompt) {
        List<String> content = IntStream.range(0, items.size())
                .mapToObj(i -> (i + 1) + "." + items.get(i).toString())
                .collect(Collectors.toList());
        content.add(0, prompt);

        return new Response(content);
    }

    /**
     * Generates a List type response.
     *
     * @param items the list of items to be listed
     * @return a response
     */
    public static Response newListing(List<Task> items) {
        return Response.listing(items, "Here are the tasks:");
    }

    /**
     * Generates a Find type response.
     *
     * @param items the list of items to be listed
     * @return a response
     */
    public static Response newMatch(List<Task> items) {
        return Response.listing(items, "Here are the matching tasks in your list:");
    }

    /**
     * Generates a Echo type response.
     *
     * @return a response
     */
    public static Response newEcho(String input) {
        return new Response(List.of(input));
    }

    /**
     * Prints the Response to stdout. This methods handles the indentation and dividers.
     */
    public void print() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        String border = "    ";
        String divider = "____________________________________________________________";
        String contentIndent = "     ";

        StringBuilder builder = new StringBuilder()
                .append(border).append(divider).append(System.lineSeparator());

        for (String line : content) {
            builder.append(contentIndent).append(line).append(System.lineSeparator());
        }

        builder.append(border).append(divider).append(System.lineSeparator());
        return builder.toString();
    }

    public String toDialogue() {
        StringBuilder builder = new StringBuilder();
        return this.content.stream()
                .reduce(builder, (sb, line) -> sb.append(line).append(System.lineSeparator()), (x, y) -> y)
                .toString();
    }
}
