import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Handles response formatting
 */
public class Response {
    private List<String> content;

    private Response() {
        this.content = new ArrayList<>();
    }

    private Response(List<String> content) {
        this.content = content;
    }

    public static Response NewGreetings() {
        return new Response(List.of(
                "Hello! I'm Duke",
                "What can I do for you?"
        ));
    }

    public static Response NewFarewell() {
        return new Response(List.of(
                "Bye. Hope to see you again soon!"
        ));
    }
    public static Response NewAdded(String item) {
        String added = "added: ";
        return new Response(List.of(added + item));
    }

    public static Response NewListing(List<String> items) {
        List<String> content = IntStream.range(0, items.size())
                .mapToObj(i -> (i + 1) +". "+items.get(i))
                .collect(Collectors.toList());

        return new Response(content);
    }

    public static Response NewEcho(String input) {
        return new Response(List.of(input));
    }

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
