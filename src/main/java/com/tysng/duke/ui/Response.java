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

    /**
     * Generates a List type response.
     *
     * @param items the list of items to be listed
     * @return a response
     */
    public static Response newListing(List<Task> items) {
        List<String> content = IntStream.range(0, items.size())
                .mapToObj(i -> (i + 1) + "." + items.get(i).toString())
                .collect(Collectors.toList());
        content.add(0, "Here are the tasks in your list:");

        return new Response(content);
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
        String border = "    ";
        String contentIndent = "     ";
        String divider = "____________________________________________________________";
        // output
        System.out.println(border + divider);
        for (String line : content) {
            System.out.print(contentIndent);
            System.out.println(line);
        }
        System.out.println(border + divider);
    }

}
