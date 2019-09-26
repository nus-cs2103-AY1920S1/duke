package duke.errors;

/**
 * Represents an helper class to assert null checks for any number of objects
 */
public class DukeAssertions {

    /**
     * Asserts that each object of any given number is not null
     */
    public static void assertNotNull(Object ... items) {
        for (Object item : items) {
            assert item != null;
        }
    }

    /**
     * Asserts that each String of any given number is not null
     */
    public static void assertArrayNotEmpty(String [] items) {
        for (String item : items) {
            assert item != null;
        }
    }
}
