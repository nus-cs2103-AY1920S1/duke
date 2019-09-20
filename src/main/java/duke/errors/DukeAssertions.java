package duke.errors;

public class DukeAssertions {
    public static void assertNotNull(Object ... items) {
        for (Object item : items) {
            assert item != null;
        }
    }

    public static void assertArrayNotEmpty(String [] items) {
        for (String item : items) {
            assert item != null;
        }
    }
}
