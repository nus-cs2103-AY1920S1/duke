package duke;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeUtilTest {
    @Test
    void concatEmptyArray() {
        assertEquals(
                "",
                DukeUtil.concatStrings(new String[0], " | "));
    }

    @Test
    void concatArray() {
        String[] testStringArray = new String[] { " a b c d | e f\\ g", " 1xf 0 9", "{[]}1|" };
        assertEquals(
                " a b c d | e f\\ g\\|*  1xf 0 9\\|* {[]}1|",
                DukeUtil.concatStrings(testStringArray, "\\|* "));
    }

    @Test
    void getIndexOfPatternInEmptyArray() {
        assertEquals(
                -1,
                DukeUtil.getIndexOfPattern(new String[0], "abc"));
    }

    @Test
    void getIndexOfPattern() {
        String[] testPrimeStringStream = IntStream
                .range(2, 30)
                .filter(x -> IntStream.range(2, x).noneMatch(y -> { return (double) x/y ==  x/y; }))
                .mapToObj(primeNum -> primeNum + "")
                .toArray(size -> new String[15]);

        //2, 3, 5, 7, 11...
        assertEquals(
                3,
                DukeUtil.getIndexOfPattern(testPrimeStringStream, "7"));
    }
}
