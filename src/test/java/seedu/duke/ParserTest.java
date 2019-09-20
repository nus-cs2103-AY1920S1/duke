package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test1() {
        try {
            Command actual = Parser.parse("done 1");
            assertEquals("done", actual.type);
        } catch (DukeException d) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void test2() {
        try {
            Command actual = Parser.parse("delete 1");
            assertEquals("delete", actual.type);
        } catch (DukeException d) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void test3() {
        try {
            Command actual = Parser.parse("todo whatever");
            assertEquals("add", actual.type);
        } catch (DukeException d) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void test4() {
        try {
            Command actual = Parser.parse("bye");
            assertEquals("exit", actual.type);
        } catch (DukeException d) {
            System.out.println("Something went wrong");
        }
    }
}
